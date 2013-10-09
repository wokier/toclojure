(ns mybusiness.service
  "a docstring would be nice here to know what this namespace represents."
  (:require [monger.core :as mg]
            [monger.collection :as mgc]
            [clojure.data.json :as json]
            [monger.json]
            [cheshire.core :refer :all :as cs])
  (:import [com.mongodb MongoOptions ServerAddress]
           [java.util.regex Matcher]
           [org.bson.types ObjectId])
  (:use clojure.test
        monger.conversion))

(def db-connection-name "todb")

(defn connect-to-db! "Connect to the db (create connection if needed)." [uri db-name]
  (if (nil? uri)
    (comp (mg/connect!) (mg/set-db! (monger.core/get-db db-name)))
    (mg/connect-via-uri! uri)))

(defn clearAllTodos "docstring?" [uri]
  (connect-to-db! uri db-connection-name)
  (mgc/remove "todos")
  (mg/disconnect!)
  )

(defn trace-map! "Display a debug trace of the map entry-map and return it as is." [label entry-map]
  (println (format "%s - %s" label (->> entry-map
                                        (into [])))) ;; why do you convert it into vector?
  entry-map)

(comment
  (trace-map! "some-use-sample" {:test :data
                                 :do?  :this-will-print-the-map-as-vector-and-return-it-untouched}))

;; good conventions:
;; - use a dosctring to explain what the function or the namespace do
;; - name a side-effect function with suffix ! so save-todos! here
;; - use dash instead of camel case (you are not in java world here, this can show better if you use java then as the convention differs)
;; - use let instead of def form inside your function
;; - avoid muting data... (here, mongo lib seems to force you but in general, use functional way instead)

(defn saveTodos [todos uri]
  (connect-to-db! uri db-connection-name)
  (let [todosMap (cs/parse-string (clojure.string/replace todos #"\$\$hashKey" "hashKey") true)
        newObjectId #(ObjectId. %)]
    (trace-map! "saveTodosIds"
                (map #(mgc/save-and-return "todos"
                                           (if (contains? % :_id ) (update-in % [:_id ] newObjectId) %))
                     (trace-map! "saveTodosMap" todosMap)))
    (mg/disconnect!))
  )

(defn findTodos [uri]
  (connect-to-db! uri db-connection-name)
  (def todosMap (into [] (mgc/find-maps "todos")))
  (mg/disconnect!)
  (println (str "--findTodosMap" todosMap))
  (def clearKey (comp #(clojure.string/replace % #"hashKey" (Matcher/quoteReplacement "$$hashKey")) str #(clojure.string/replace % #":" "")))
  (def clearVal (comp #(if (or (true? %) (false? %)) % (str %) )) )
  (def todos (cs/generate-string (map #(zipmap (map clearKey (keys %)) (map clearVal (vals %))) todosMap)))
  ; (println (str "--findTodos---" todos))
  todos
  )

(defn deleteTodo [id uri]
  (connect-to-db! uri db-connection-name)
  (println (str "deleteTodo " id))
  (mgc/remove-by-id "todos" (ObjectId. id))
  (mg/disconnect!)
  )

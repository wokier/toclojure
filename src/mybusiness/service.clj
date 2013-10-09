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

(defn clearAllTodos [uri]
  (if (nil? uri)
    (comp (mg/connect!) (mg/set-db! (monger.core/get-db "todb")))
    (mg/connect-via-uri! uri))
  (mgc/remove "todos")
  (mg/disconnect!)
  )

(defn saveTodos [todos uri]
  (if (nil? uri)
    (comp (mg/connect!) (mg/set-db! (monger.core/get-db "todb")))
    (mg/connect-via-uri! uri))
  ;   (println (str "saveTodos---" todos))
  (def todosMap (cs/parse-string (clojure.string/replace todos #"\$\$hashKey" "hashKey") true))
  (println (str "saveTodosMap" (into [] todosMap)))
  (def newObjectId #(ObjectId. %))
  (println (str "saveTodosIds" (into [] (map #(mgc/save-and-return "todos" (if (contains? % :_id ) (update-in % [:_id ] newObjectId) %)) todosMap))))
  (mg/disconnect!)
  )

(defn findTodos [uri]
  (if (nil? uri)
    (comp (mg/connect!) (mg/set-db! (monger.core/get-db "todb")))
    (mg/connect-via-uri! uri))
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
  (if (nil? uri)
    (comp (mg/connect!) (mg/set-db! (monger.core/get-db "todb")))
    (mg/connect-via-uri! uri))
  (println (str "deleteTodo " id))
  (mgc/remove-by-id "todos" (ObjectId. id))
  (mg/disconnect!)
  )

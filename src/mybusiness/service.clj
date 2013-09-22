(ns mybusiness.service
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
    (mg/connect!)
    (mg/connect-via-uri! uri))
  (mg/set-db! (monger.core/get-db "todb"))
  (mgc/remove "todos")
  (mg/disconnect!)
  )

(defn saveTodos [todos uri]
  (if (nil? uri)
    (mg/connect!)
    (mg/connect-via-uri! uri))
  (mg/set-db! (monger.core/get-db "todb"))
;   (println (str "saveTodos---" todos))
  (def todosMap (cs/parse-string (clojure.string/replace todos #"\$\$hashKey" "hashKey") true))
  (println (str "saveTodosMap" (into [] todosMap)))
  (def newObjectId #(ObjectId. %))
  (println (str "saveTodosIds" (into [] (map #(mgc/save-and-return "todos" (if (contains? % :_id ) (update-in % [:_id ] newObjectId) %)) todosMap))))
  (mg/disconnect!)
  )

(defn findTodos [uri]
  (if (nil? uri)
    (mg/connect!)
    (mg/connect-via-uri! uri))
  (mg/set-db! (monger.core/get-db "todb"))
  (def todosMap (into [] (mgc/find-maps "todos")))
  (mg/disconnect!)
  (println (str "--findTodosMap" todosMap))
  (def clearKey (comp #(clojure.string/replace % #"hashKey" (Matcher/quoteReplacement "$$hashKey")) str #(clojure.string/replace % #":" "")))
  (def clearVal (comp str))
  (def todos (cs/generate-string (map #(zipmap (map clearKey (keys %)) (map clearVal (vals %))) todosMap)))
; (println (str "--findTodos---" todos))
  todos
  )


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

(defn saveTodos [todos & uri]
  (if (nil? uri)
    (mg/connect!)
    (mg/connect-via-uri! uri))
  (mg/set-db! (monger.core/get-db "todb"))
  (println (str "saveTodos---" todos))
  (def todosMap (cs/parse-string (clojure.string/replace todos #"\$\$hashKey" "hashKey") true))
  (println (str "saveTodosMap" (into [] todosMap)))
  (println (str "saveTodosIds" (into [] (map #(mgc/save-and-return "todos" %) todosMap))))
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
  (def todos (cs/generate-string todosMap {:key-fn (comp #(clojure.string/replace % #"hashKey" (Matcher/quoteReplacement "$$hashKey")) str #(clojure.string/replace % #":" ""))}))
  (println (str "--findTodos---" todos))
  (def todosok (cs/generate-string (distinct (cs/parse-string todos))))
  (println (str "--findTodosok-" todosok))
  todosok
  )
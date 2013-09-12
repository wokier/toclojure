(ns mybusiness.service
  (:require [monger.core :as mg]
            [monger.collection :as mgc]
            [clojure.data.json :as json]
            [monger.json]
            [cheshire.core :refer :all :as cs])
  (:import [com.mongodb MongoOptions ServerAddress]
           [java.util.regex Matcher])
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

(defn insertTodos [todos & uri]
  (if (nil? uri)
    (mg/connect!)
    (mg/connect-via-uri! uri))
  (mg/set-db! (monger.core/get-db "todb"))
  (mgc/insert-batch "todos" (json/read-str todos :key-fn #(clojure.string/replace % #"\$\$hashKey" "hashKey")))
  (mg/disconnect!)
  (println todos)
  )

(defn findTodos [uri]
  (if (nil? uri)
    (mg/connect!)
    (mg/connect-via-uri! uri))
  (mg/set-db! (monger.core/get-db "todb"))
  (def todos (into #{} (mgc/find-maps "todos")))
  (mg/disconnect!)
  (println todos)
  (cs/generate-string todos {:key-fn #(clojure.string/replace % #"hashKey"  (Matcher/quoteReplacement "$$hashKey"))})
  )
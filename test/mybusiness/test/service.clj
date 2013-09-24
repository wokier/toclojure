(ns mybusiness.test.service
  (:require [clojure.data.json :as json]
            [monger.json]
            [cheshire.core :refer :all :as cs]
            [clojure.contrib.string :as strings])
  (:use clojure.test
        mybusiness.service
        ))

(deftest service-test
  (testing "save todos"
    (clearAllTodos (System/getenv "MONGOHQ_URL"))
    (is (= 0 (count (json/read-str (findTodos (System/getenv "MONGOHQ_URL")) :key-fn #(clojure.string/replace % #"\$\$hashKey" "hashKey") ))))
    (saveTodos "[{\"title\":\"things\",\"completed\":true,\"$$hashKey\":\"004\"},{\"title\":\"That\",\"completed\":false,\"$$hashKey\":\"007\"},{\"title\":\"others things\",\"completed\":false,\"$$hashKey\":\"00B\"}]" (System/getenv "MONGOHQ_URL"))
    (is (= 3 (count (json/read-str (findTodos (System/getenv "MONGOHQ_URL")) :key-fn #(clojure.string/replace % #"\$\$hashKey" "hashKey") ))))
    )

  (testing "re-save same todos"
    (clearAllTodos nil)
    (saveTodos "[{\"title\":\"things\",\"completed\":true,\"$$hashKey\":\"004\"},{\"title\":\"That\",\"completed\":false,\"$$hashKey\":\"007\"},{\"title\":\"others things\",\"completed\":false,\"$$hashKey\":\"00B\"}]" (System/getenv "MONGOHQ_URL"))
    (saveTodos (findTodos (System/getenv "MONGOHQ_URL")) (System/getenv "MONGOHQ_URL"))
    (is (= 3 (count (json/read-str (findTodos (System/getenv "MONGOHQ_URL")) :key-fn #(clojure.string/replace % #"\$\$hashKey" "hashKey") ))))
    )

  (testing "use boolean well"
    (clearAllTodos nil)
    (saveTodos "[{\"title\":\"things\",\"completed\":true,\"$$hashKey\":\"004\"},{\"title\":\"That\",\"completed\":false,\"$$hashKey\":\"007\"}]" (System/getenv "MONGOHQ_URL"))
    (is (not (strings/substring? "\"true\"" (findTodos (System/getenv "MONGOHQ_URL")) )))
    )
  )





(ns mybusiness.test.service
  (:require [clojure.data.json :as json]
            [monger.json]
            [cheshire.core :refer :all :as cs])
  (:use clojure.test
        mybusiness.service
        ))

(deftest service-test
  (testing "save todos"
    (clearAllTodos nil)
    (is (= 0 (count (json/read-str (findTodos nil) :key-fn #(clojure.string/replace % #"\$\$hashKey" "hashKey") ))))
    (saveTodos "[{\"title\":\"things\",\"completed\":true,\"$$hashKey\":\"004\"},{\"title\":\"That\",\"completed\":false,\"$$hashKey\":\"007\"},{\"title\":\"others things\",\"completed\":false,\"$$hashKey\":\"00B\"}]")
    (is (= 3 (count (json/read-str (findTodos nil) :key-fn #(clojure.string/replace % #"\$\$hashKey" "hashKey") ))))
    )

  (testing "re-save same todos"
    (clearAllTodos nil)
    (saveTodos "[{\"title\":\"things\",\"completed\":true,\"$$hashKey\":\"004\"},{\"title\":\"That\",\"completed\":false,\"$$hashKey\":\"007\"},{\"title\":\"others things\",\"completed\":false,\"$$hashKey\":\"00B\"}]")
    (saveTodos (findTodos nil) )
    (is (= 3 (count (json/read-str (findTodos nil) :key-fn #(clojure.string/replace % #"\$\$hashKey" "hashKey") ))))
    )
  )





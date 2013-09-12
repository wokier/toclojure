(ns mybusiness.test.service
  (:require [clojure.data.json :as json]
            [monger.json]
            [cheshire.core :refer :all :as cs])
  (:use clojure.test
        mybusiness.service
        ))

(deftest service-test
  (testing "insert todos"
    (clearAllTodos nil)
    (insertTodos "[{\"title\":\"things\",\"completed\":true,\"$$hashKey\":\"004\"},{\"title\":\"That\",\"completed\":false,\"$$hashKey\":\"007\"},{\"title\":\"others things\",\"completed\":false,\"$$hashKey\":\"00B\"}]")
    (is (= 3 (count (cs/parse-string (findTodos nil) {:key-fn #(clojure.string/replace % #"\$\$hashKey" "hashKey")} ))))
    )

  (testing "re-insert same todos"
    (clearAllTodos nil)
    (insertTodos "[{\"title\":\"things\",\"completed\":true,\"$$hashKey\":\"004\"},{\"title\":\"That\",\"completed\":false,\"$$hashKey\":\"007\"},{\"title\":\"others things\",\"completed\":false,\"$$hashKey\":\"00B\"}]")
    (insertTodos (findTodos nil) )
    )
  )





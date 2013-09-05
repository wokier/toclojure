(ns mybusiness.test.service
  (:use clojure.test
        mybusiness.service
        ))

(deftest service-test
  (testing "insert todos"
    (clearAllTodos nil)
    (insertTodos "[{\"title\":\"things\",\"completed\":true,\"$$hashKey\":\"004\"},{\"title\":\"That\",\"completed\":false,\"$$hashKey\":\"007\"},{\"title\":\"others things\",\"completed\":false,\"$$hashKey\":\"00B\"}]")
    (is (= 3 (count (findTodos nil))))
    )

  (testing "empty"
    ())
  )





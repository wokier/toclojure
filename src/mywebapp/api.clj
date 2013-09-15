(ns mywebapp.api
  (:use [hiccup core page]
        [mybusiness.service]))

(defn test []
  "OK"
  )

(defn getTodos []
  (findTodos (System/getenv "MONGOHQ_URL")))

(defn updateTodos []
  (saveTodos (System/getenv "MONGOHQ_URL")))
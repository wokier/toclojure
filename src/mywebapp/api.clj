(ns mywebapp.api
  (:use [hiccup core page]
        [mybusiness.service]))

(defn api-test []
  "OK"
  )

(defn hello [who]
  (str "Hello " who)
  )

(defn getTodos []
  (findTodos (System/getenv "MONGOHQ_URL"))
  )

(defn updateTodos [todos]
  (saveTodos todos (System/getenv "MONGOHQ_URL"))
  (findTodos (System/getenv "MONGOHQ_URL"))
  )
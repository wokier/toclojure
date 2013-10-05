(ns mywebapp.api
  (:use [hiccup core page]
        [mybusiness.service :as service]))

(defn api-test []
  "OK"
  )

(defn hello [who]
  (str "Hello " who)
  )

(defn getTodos []
  (service/findTodos (System/getenv "MONGOHQ_URL"))
  )

(defn updateTodos [todos]
  (service/saveTodos todos (System/getenv "MONGOHQ_URL"))
  (service/findTodos (System/getenv "MONGOHQ_URL"))
  )

(defn deleteTodoById [id]
  (service/deleteTodo id (System/getenv "MONGOHQ_URL"))
  )
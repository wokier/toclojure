(ns mywebapp.routes
  (:use compojure.core
        mywebapp.views
        mywebapp.api)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))


(defroutes app-routes
  (GET "/" [] (index-page))
  (GET "/todemo" [] (demo-page))
  (GET "/about" [] (about-page))
  (GET "/api/test" [] (test))
  (GET "/api/hello/:who" [who] (hello who))
  (POST "/api/miror" {body :body} (slurp body))
  (GET "/api/todos" [] (getTodos))
  (POST "/api/todos" {body :body} (updateTodos (slurp body)))
  (route/resources "/")
  (route/not-found "No Route Found. Are you lost ?"))


(def app
  (handler/site app-routes))

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
  (GET "/api" [] (getTodos))
  (PUT "/api" [] (updateTodos))
  (route/resources "/")
  (route/not-found "No page"))


(def app
  (handler/site app-routes))

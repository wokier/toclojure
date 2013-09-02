(ns mywebapp.views
  (:use [hiccup core page]))

(defn header []
  [:div.navbar.navbar-inverse.navbar-fixed-top
   [:div.navbar-inner
    [:div.container
     [:a.brand {:href "/"} "Mon Cloj DB"]
     [:div.nav-collapse.collapse
      [:ul.nav
       [:li.active [:a {:href "/"} "Intro"]]
       [:li.active [:a {:href "#todos"} "Demo"]]
       [:li.active [:a {:href "/about"} "About"]]]]]]])

(defn template [& body]
  (html5
   [:head
    [:title "Mon Cloj DB"]
    [:meta {:name "viewport"
            :content "width=device-width, initial-scale=1.0"}]
    (include-css "/css/bootstrap.min.css")
    (include-css "/css/base.css")
    (include-css "/css/monclojure.css")]
   [:body {:style "padding-top:60px;"}
    (header)
    [:div.container
     body]]))

(defn index-page []
  (template

   [:div {:class "hero-unit"}
    [:h1.center "Mon Cloj DB"]
    [:p.row.center
      [:img.span3.offset1 {:src "img/angularjs.png"}]
      [:img.span3 {:src "img/clojure.png"}]
      [:img.span3 {:src "img/mongodb.png"}]
     ]]

    [:div {:class "well"}
      [:p {:ng-controller "TodoCtrl"}
        [:h3#todos "Todos"]
        [:div
          [:form
            [:input#new-todo {:placeholder "What needs to be done?" :autofocus "" }]
           ]
          ]
        ]
      ]))

(defn about-page []
  (template
   [:div {:class "well"}
    [:h1 "About This:"]
    [:p "This Clojure clickstart was developed by members of the "
     [:a {:href "http://www.meetup.com/Austin-Clojure-Meetup/"} "Austin Clojure Meetup"]
     ".  You can find us as "
     [:a {:href "https://github.com/AustinClojure"} "AustinClojure on github"]
     "."]
     [:h1 "About ClickStarts:"]
      "Read about what ClickStarts are "
     [:a {:href "https://developer.cloudbees.com/bin/view/RUN/ClickStart"} "at CloudBees"]

     ]))

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
       [:li.active [:a {:href "/todemo"} "To Demo"]]
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
   [:body
    (header)
    [:div.container
     body]]))

(defn index-page []
  (template
   [:div.hero-unit
      [:h1.center "Mon Cloj DB"]
      [:p.row.center
        [:img.span3.offset1 {:src "img/angularjs.png"}]
        [:img.span3 {:src "img/clojure.png"}]
        [:img.span3 {:src "img/mongodb.png"}]
       ]
      [:p.center "Angular, Clojure & MongoDB"]
    ]

    ))



(defn demo-page []
  (template
    [:div.well {:ng-app "todomvc" :data-framework "angularjs"}
     [:section#todoapp {:ng-controller "TodoCtrl"}
      [:header#header
       [:h3#todos "Todos"]
       [:form#todo-form {:ng-submit "addTodo()"}
        [:input#new-todo {:placeholder "What needs to be done?" :ng-model "newTodo" :autofocus "" }]
        ]
       ]
      [:section#main {:ng-show "todos.length" :ng-cloak ""}
       [:input#toggle-all {:type "checkbox" :ng-model "allChecked" :ng-click "markAll(allChecked)"}]
       [:label {:for "toggle-all"} "Mark all as complete" ]
       [:ul#todo-list
        [:li {:ng-repeat "todo in todos | filter:statusFilter" :ng-class "{completed: todo.completed, editing: todo == editedTodo}"}
         [:div.view
          [:input.toggle {:type "checkbox" :ng-model "todo.completed"}]
          [:label {:ng-dblclick "editTodo(todo)"} "{{todo.title}}" ]
          [:button.destroy {:ng-click "removeTodo(todo)"}]
          ]
         [:form {:ng-submit "doneEditing(todo)"}
          [:input.edit {:ng-model "todo.title" :todo-escape "revertEditing(todo)" :todo-blur "doneEditing(todo)" :todo-focus "todo == editedTodo" }]
          ]
         ]
        ]
       ]
      [:footer#footer {:ng-show "todos.length" :ng-cloak ""}
       [:span#todo-count
        [:strong "{{remainingCount}}"]
        [:ng-pluralize {:count "remainingCount" :when "{ one: ' item left', other: ' items left' }"}]
        ]
       [:ul#filters
        [:li
         [:a {:ng-class "{selected: location.path() == '/'} " :href "#/"} "All"]
         ]
        [:li
         [:a {:ng-class "{selected: location.path() == '/active'}" :href "#/active"} "Active"]
         ]
        [:li
         [:a {:ng-class "{selected: location.path() == '/completed'}" :href "#/completed"} "Completed"]
         ]
        ]
       [:button#clear-completed {:ng-click "clearCompletedTodos()" :ng-show "completedCount"} "Clear completed ({{completedCount}})"]
       ]
      ]
     ]
    (include-js "js/lib/todomvc-common/base.js")
    (include-js "js/lib/angular/angular.min.js")
    (include-js "js/lib/angular-resource/angular-resource.min.js")
    (include-js "js/app.js")
    (include-js "js/controllers/todoCtrl.js")
    (include-js "js/services/todoStorage.js")
    (include-js "js/services/todoResource.js")
    (include-js "js/directives/todoFocus.js")
    (include-js "js/directives/todoBlur.js")
    (include-js "js/directives/todoEscape.js")

    ))



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

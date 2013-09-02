(defproject mywebapp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.1"]
                 [hiccup "1.0.1"]
                 [org.clojure/tools.trace "0.7.5"]]
  :plugins [[lein-ring "0.7.3"]]
  :ring {:handler mywebapp.routes/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]
                        [lein-eclipse "1.0.0"]]}}
  :repl-options {:prompt (fn [ns]
                           (str "\033[0;31  m"
                             ns "$"
                             "\033[0m "))}
  )

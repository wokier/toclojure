(defproject mywebapp "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.1"]
                 [hiccup "1.0.1"]
                 [org.clojure/tools.trace "0.7.5"]
                 [com.novemberain/monger "1.4.2"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [org.clojure/data.json "0.2.3"]
                 [cheshire "5.1.1"]]
  :plugins [[lein-ring "0.7.3"]
            [lein-pprint "1.1.1"]
            [org.clojars.wokier/lein-bower "0.3.0"]]
  :ring {:handler mywebapp.routes/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]
                        [leiningen-run "0.2"]]}}
  :repl-options {:prompt (fn [ns]
                           (str "\033[0;31  m"
                             ns "$"
                             "\033[0m "))}
  :prep-tasks ["javac" "compile" "bower"]
  :min-lein-version "2.0.0"
  )

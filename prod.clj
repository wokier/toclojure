{:compile-path
 "/Users/wokier/Documents/code/intellijwork/toclojure/target/classes",
 :group "toclojure",
 :global-vars {},
 :repl-options {:prompt (fn [ns] (str "" ns "$" " "))},
 :dependencies
 ([org.clojure/clojure "1.5.1"]
   [compojure/compojure "1.1.5"]
   [hiccup/hiccup "1.0.4"]
   [org.clojure/tools.trace "0.7.5"]
   [com.novemberain/monger "1.6.0"]
   [org.clojure/clojure-contrib "1.2.0"]
   [org.clojure/data.json "0.2.3"]
   [cheshire/cheshire "5.2.0"]),
 :plugin-repositories
 [["central" {:snapshots false, :url "http://repo1.maven.org/maven2/"}]
  ["clojars" {:url "https://clojars.org/repo/"}]],
 :target-path
 "/Users/wokier/Documents/code/intellijwork/toclojure/target/production",
 :name "toclojure",
 :deploy-repositories
 [["clojars"
   {:username :gpg,
    :url "https://clojars.org/repo/",
    :password :gpg}]],
 :root "/Users/wokier/Documents/code/intellijwork/toclojure",
 :offline? false,
 :source-paths
 ("/Users/wokier/Documents/code/intellijwork/toclojure/src"),
 :certificates ["clojars.pem"],
 :ring {:handler mywebapp.routes/app},
 :version "0.1.0-SNAPSHOT",
 :jar-exclusions [#"^\."],
 :profiles
 {:dev {:dependencies [[ring-mock "0.1.3"] [leiningen-run "0.2"]]}},
 :prep-tasks ["javac" "compile" "bower"],
 :url "http://example.com/FIXME",
 :repositories
 [["central" {:snapshots false, :url "http://repo1.maven.org/maven2/"}]
  ["clojars" {:url "https://clojars.org/repo/"}]],
 :resource-paths
 ("/Users/wokier/Documents/code/intellijwork/toclojure/resources"),
 :uberjar-exclusions [#"(?i)^META-INF/[^/]*\.(SF|RSA|DSA)$"],
 :min-lein-version "2.0.0",
 :jvm-opts nil,
 :eval-in :subprocess,
 :plugins
 ([lein-ring/lein-ring "0.7.3"]
   [lein-pprint/lein-pprint "1.1.1"]
   [org.clojars.wokier/lein-bower "0.5.0"]),
 :native-path
 "/Users/wokier/Documents/code/intellijwork/toclojure/target/native",
 :description "FIXME: write description",
 :test-paths
 ("/Users/wokier/Documents/code/intellijwork/toclojure/test"),
 :aliases nil}
(defproject strava-fun "0.1.0-SNAPSHOT"
  :description "Strava tools in functional style"
  :url ""
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.cli "0.3.1"]
                 [org.clojure/data.json "0.2.6"]
                 [clj-http "1.1.2"]
                 [org.clojure/tools.namespace "0.2.10"]]
  :main ^:skip-aot strava-fun.core
  :target-path "target/%s"
  :plugins [[lein-gorilla "0.3.4"]]
  :profiles {:uberjar {:aot :all}})

(defproject strava-fun "0.1.0-SNAPSHOT"
  :description "Strava tools in functional style"
  :url ""
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                   [org.clojure/data.json "0.2.6"]
                   [clj-http "1.1.2"]
                   [http-kit "2.1.18"]]
  :main ^:skip-aot strava-fun.core
  :target-path "target/%s"
  :plugins [[lein-gorilla "0.3.4"]]
  :profiles {:uberjar {:aot :all}})
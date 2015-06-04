(set-env!
  :source-paths #{"src"}
  :dependencies '[[org.clojure/clojure "1.6.0"]
                  [org.clojure/data.json "0.2.6"]
                  [clj-http "1.1.2"]])

(task-options!
  pom {:project 'strava-fun
       :version "0.1.0-SNAPSHOT"})

(deftask build "Build my project." []
  (comp (pom) (jar)))

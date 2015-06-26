(ns strava-fun.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [strava-fun.strava :as strava])
  (:gen-class))

(def cli-options
  [["-t" "--token TOKEN" "Strava Api Auth Token"]])

(defn exit [status msg]
    (println msg)
    (System/exit status))

(defn do-it [options action]
  (println (str "Doing " action))
  (swap! strava/api-key (fn [old] (:token options)))
  (println (strava/sprints)))

(defn -main [& args]
  (let [{:keys [options arguments errors]} (parse-opts args cli-options)]
    (cond
      errors (exit 1 errors))
    (do-it options (first arguments))))


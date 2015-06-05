(ns strava-fun.api
  (:require [clojure.data.json :as json]
            [clj-http.client :as client]))

(def opts {:headers {"Authorization" "Bearer secret"}})
(def activities 
  (json/read-str 
    (:body (client/get "https://www.strava.com/api/v3/athlete/activities" opts)) 
    :key-fn keyword))

(defn retrieve-activity [id] 
  (json/read-str 
    (:body (client/get (str "https://www.strava.com/api/v3/activities/" id) opts)) 
    :key-fn keyword))
(defn retrieve-activity-laps [id] 
  (json/read-str 
    (:body (client/get (str "https://www.strava.com/api/v3/activities/" id "/laps") opts)) 
    :key-fn keyword))

(defn write-activity [id] 
  (spit (str "activity-" id ".json") 
        (json/write-str (retrieve-activity id))))
(defn write-activity-laps [id] 
  (spit (str "activity-" id "-laps.json") 
        (json/write-str (retrieve-activity-laps id))))

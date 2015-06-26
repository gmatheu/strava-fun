(ns strava-fun.strava
  (:require [clojure.java.io :as io]
            [clojure.data.json :as json]
            [clj-http.client :as client]))

(def api-key (atom ""))
(defn opts [] {:headers {"Authorization" (str "Bearer " @api-key)}})
(defn api-resource [path]
  (str "https://www.strava.com/api/v3/" path))
(defn retrieve [resource]
  (let [url (api-resource resource)]
    (json/read-str
      (:body (client/get url (opts)))
      :key-fn keyword)))

(defn write-resource [json name kind]
  (let [dir (io/as-file (str "." kind))
        target (io/as-file (str dir "/" name ".json"))]
    (spit target (json/write-str json)))
  json)
;; (defn write-activity [id]
;;   (write-resource (activity id) id "activity"))
;; (defn write-laps [id]
;;   (write-resource (laps id) id "laps"))
;; (defn write-all [activities]
;;     (map (fn [a] 
;;           (let [id (:id a)]
;;             (write-activity id) 
;;             (write-laps id)))
;;         activities))

(defn activities []
  (println "Retrieving activities")
  (retrieve "athlete/activities"))
(defn activity [id]
  (println (str "Retrieving activity: " id))
  (let [kind "activities" 
        filename (str "." kind "/" id ".json")]
    (if (-> filename io/as-file .exists)
      (json/read-str (slurp filename))
      (write-resource (retrieve (str "activities/" id))
                                id kind))))
(defn laps [id]
  (println (str "Retrieving activity laps: " id))
  (let [kind "laps" 
        filename (str "." kind "/" id ".json")]
    (if (-> filename io/as-file .exists)
      (json/read-str (slurp filename))
      (write-resource (retrieve (str "activities/" id "/laps"))
                                id kind))))

(def average (:average_speed activity))
(defn round-to-hundreds [value] (* (Math/round (/ (double value) 100)) 100))
(def sprints (filter #(< (* average 1.05) (:average_speed %)) laps))
(group-by #(round-to-hundreds (:distance %)) sprints)

(defn sprints []
  (let [laps (map (fn [a] (laps (:id a))) (activities))]
    laps))

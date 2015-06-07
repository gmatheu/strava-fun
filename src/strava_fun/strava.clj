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

(defn activities []
  (retrieve "athlete/activities"))
(defn activity [id]
  (retrieve (str "activities/" id)))
(defn laps [id]
   (retrieve (str "activities/" id "/laps")))

(defn write-resource [json name kind]
  (let [dir (io/as-file (str "." kind))
        target (io/as-file (str dir "/" name ".json"))]
    (.mkdir dir)
    (if (.exists target)
      ()
      (spit target (json/write-str json)))))
(defn write-activity [id]
  (write-resource (activity id) id "activity"))
(defn write-laps [id]
  (write-resource (laps id) id "laps"))
(defn write-all [activities]
    (map (fn [a] 
          (let [id (:id a)]
            (write-activity id) 
            (write-laps id)))
        activities))

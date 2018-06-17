(ns efp-clojure-tracking-inventory.db.core
    (:require [monger.core :as mg]
              [monger.collection :as mc]
              [monger.operators :refer :all]
              [monger.conversion :refer [from-db-object]]
              [mount.core :refer [defstate]]
              [efp-clojure-tracking-inventory.config :refer [env]])
  (:import org.bson.types.ObjectId))

(defstate db*
  :start (-> env :database-url mg/connect-via-uri)
  :stop (-> db* :conn mg/disconnect))

(defstate db
  :start (:db db*))

(def coll "inventory")

(defn format-item [item]
  (-> item
      (assoc :id (.toString (:_id item)))
      (dissoc :_id)))

(defn create-item [item]
  (mc/insert db coll item))

(defn update-item [id item]
  (mc/update db coll {:_id (ObjectId. id)} {$set item}))

(defn delete-item [id]
  (->> (ObjectId. id)
       (mc/remove-by-id db coll)))

(defn get-items []
  (map format-item (mc/find-maps db coll {})))

(defn get-item [id]
  (format-item (mc/find-one-as-map db coll {:_id (ObjectId. id)})))

;
;(defn create-user [user]
;  (mc/insert db "users" user))
;
;(defn update-user [id first-name last-name email]
;  (mc/update db "users" {:_id id}
;             {$set {:first_name first-name
;                    :last_name last-name
;                    :email email}}));
;
;(defn get-user [id]
;  (mc/find-one-as-map db "users" {:_id id}))

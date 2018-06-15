(ns efp-clojure-tracking-inventory.routes.home
  (:require [efp-clojure-tracking-inventory.db.core :as db]
            [efp-clojure-tracking-inventory.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]))

(defn home-page [{:keys [flash]}]
  (let [items (db/get-items)]
    (println items)
    (layout/render
     "home.html" (hash-map :items items :flash flash))))

(defn item-page []
  (layout/render "form.html"))

(defn add-item [{:keys [params]}]
  (db/create-item (dissoc params :__anti-forgery-token))
  (-> (response/found "/")
      (assoc :flash "Item added!")))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" request (home-page request))
  (GET "/item" [] (item-page))
  (POST "/add-item" request (add-item request))
  (GET "/about" [] (about-page)))


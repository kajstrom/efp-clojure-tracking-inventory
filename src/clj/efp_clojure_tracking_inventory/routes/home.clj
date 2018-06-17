(ns efp-clojure-tracking-inventory.routes.home
  (:require [efp-clojure-tracking-inventory.db.core :as db]
            [efp-clojure-tracking-inventory.layout :as layout]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [ring.util.response :refer [header content-type]]
            [clojure.java.io :as io]
            [efp-clojure-tracking-inventory.export :as export]))

(defn home-page [{:keys [flash]}]
  (let [items (db/get-items)]
    (layout/render
     "home.html" (hash-map :items items :flash flash))))

(defn item-page [id]
  (if id
    (let [item (db/get-item id)]
      (layout/render "form.html" item))
    (layout/render "form.html")))

(defn add-item [{:keys [params]}]
  (db/create-item (dissoc params :__anti-forgery-token))
  (-> (response/found "/")
      (assoc :flash "Item added!")))

(defn edit-item [id {:keys [params]}]
  (->> (dissoc params :id :__anti-forgery-token)
       (db/update-item id))
  (-> (response/found "/")
      (assoc :flash "Item updated")))

(defn delete-item [id]
  (db/delete-item id)
  (-> (response/found "/")
      (assoc :flash "Item deleted")))

(defn csv-export []
  (let [items (db/get-items)]
    (with-open [writer (java.io.StringWriter.)]
      (export/item-csv items writer)
      (let [csv-string (.toString writer)]
        (-> (response/ok csv-string)
          (header "Content-Disposition" "filename=items.csv")
          (header "Content-Length" (count csv-string))
          (header "Content-Type" "application/csv"))))))

(defroutes home-routes
  (GET "/" request (home-page request))
  (GET "/item" [] (item-page))
  (GET "/item/:id" [id request] (item-page id))
  (POST "/add-item" request (add-item request))
  (POST "/edit-item/:id" [id :as request] (edit-item id request))
  (GET "/delete-item/:id" [id] (delete-item id))
  (GET "/csv" [] (csv-export)))


(ns efp-clojure-tracking-inventory.routes.home
  (:require [efp-clojure-tracking-inventory.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn item-page []
  (layout/render "form.html"))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/item" [] (item-page))
  (GET "/about" [] (about-page)))


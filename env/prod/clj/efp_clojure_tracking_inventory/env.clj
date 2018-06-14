(ns efp-clojure-tracking-inventory.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[efp-clojure-tracking-inventory started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[efp-clojure-tracking-inventory has shut down successfully]=-"))
   :middleware identity})

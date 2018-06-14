(ns efp-clojure-tracking-inventory.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [efp-clojure-tracking-inventory.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[efp-clojure-tracking-inventory started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[efp-clojure-tracking-inventory has shut down successfully]=-"))
   :middleware wrap-dev})

(ns user
  (:require [efp-clojure-tracking-inventory.config :refer [env]]
            [clojure.spec.alpha :as s]
            [expound.alpha :as expound]
            [mount.core :as mount]
            [efp-clojure-tracking-inventory.core :refer [start-app]]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(defn start []
  (mount/start-without #'efp-clojure-tracking-inventory.core/repl-server))

(defn stop []
  (mount/stop-except #'efp-clojure-tracking-inventory.core/repl-server))

(defn restart []
  (stop)
  (start))



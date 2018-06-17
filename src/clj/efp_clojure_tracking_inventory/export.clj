(ns efp-clojure-tracking-inventory.export
  (:require [clojure.data.csv :as csv]))

(defn item-csv [items out]
  (csv/write-csv out [["Name" "Serial Number" "Value"]])
  (->> (map #(dissoc % :id) items)
   (map vals)
       (csv/write-csv out)))
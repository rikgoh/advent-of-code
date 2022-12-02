(ns advent-of-code.utils.utils
  (:import (java.util ArrayList)))

(use 'clojure.java.io)
(defn read-input-file-integers [input-file]
  (let [store (ArrayList.)]
    (with-open [rdr (reader input-file)]
      (doseq [line (line-seq rdr)]
        (.add store (read-string line))))
    (->> store
         (map #(merge %)))))

(use 'clojure.java.io)
(defn read-input-file-strings [input-file]
  (let [store (ArrayList.)]
    (with-open [rdr (reader input-file)]
      (doseq [line (line-seq rdr)]
        (.add store line)))
    (->> store
         (map #(merge %)))))

(defn get-key-from-map [input-map name]
  (->> input-map
       (map #(select-keys % [name]))
       (remove empty?)))
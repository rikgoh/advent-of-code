(ns advent-of-code.2021.day-2-part-1
  (:require [advent-of-code.utils.utils :as utils]
            [clojure.string :as str]))

(defonce day-2-input-file "./resources/2021/day-2.txt")
(defonce day-2-part-1-input (utils/read-input-file-strings day-2-input-file))

(defn set-navigation-map [line]
  (let [line-split (str/split line #" ")
        pos (first line-split)
        val (second line-split)]
    {(keyword pos) (read-string val)}))

(defn get-navigations [input-map name]
  (->> input-map
       (map #(select-keys % [name]))
       (remove empty?)))

(defn sum-map-vals [input-map]
  (->> input-map
       (map #(vals %))
       (map #(first %))
       (reduce +)))

(defn navigation [input-file]
  (let [input input-file
        input-map (->> input
                       (map #(set-navigation-map %)))
        forward (get-navigations input-map (keyword "forward"))
        down (get-navigations input-map (keyword "down"))
        up (get-navigations input-map (keyword "up"))
        horizontal (sum-map-vals forward)
        depth (- (sum-map-vals down) (sum-map-vals up))]
    (println (* horizontal depth))))
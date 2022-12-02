(ns advent-of-code.2022.day-1-part-1-2022
  (:require
    [advent-of-code.utils.utils :as utils])
  (:import (java.util ArrayList)))

(defonce day-1-input-file "./resources/2022/day-1.txt")
(defonce day-1-part-1-input (utils/read-input-file-strings day-1-input-file))

(defonce num1 (atom 1))

(defn swap-val []
  (swap! num1 inc)
  {}
  )

(defn is-blank-line [line]
  (let [reindeer (ArrayList.)]
    ;(if (= 6 @num1)
      ;(reset! num1 1))

    (if (empty? line)
      (swap-val)
      {(keyword (str "reindeer-" @num1)) (read-string line)}
      )
    )
  )

(defn all-reindeers []
  (let [_ (reset! num1 1)]
    (->> (utils/read-input-file-strings day-1-input-file)
         (map #(is-blank-line %))
         (remove empty?)
         )))

(defn all-reindeers-2 []
  (let [_ (reset! num1 1)]
    (->> (utils/read-input-file-strings day-1-input-file)
         (map #(is-blank-line %))
         )))

(defn sum-map-vals [input-map]
  (->> input-map
       (map #(vals %))
       (map #(first %))
       (reduce +)))

(defn which-reindeer-has-most-calories []
  (let [input (all-reindeers)
        group-reindeers (group-by keys input)
        ]
    (->> (map #(second %) group-reindeers)
         (map #(sum-map-vals %))
         sort
         reverse
         (take 3)
         (reduce +)
         )))
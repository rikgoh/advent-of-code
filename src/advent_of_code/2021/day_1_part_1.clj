(ns advent-of-code.2021.day-1-part-1
  (:require
    [advent-of-code.utils.utils :as utils]))

(defonce day-1-input-file "./resources/2021/day-1.txt")
(defonce day-1-part-1-input (utils/read-input-file-integers day-1-input-file))

(defn get-increment-count [input-of-data]
  (let [input input-of-data
        first-input-val (first input)
        second-input-val (second input)
        is-higher (when (some? second-input-val)
                    (> second-input-val first-input-val))]
    (when (and (not-empty input) (some? second-input-val))
      (conj [] {:first first-input-val :second second-input-val :result is-higher} (get-increment-count (drop 3 input))))))

(defn how-many-increments []
  (let [data (remove empty? (flatten (get-increment-count day-1-part-1-input)))
        results (->> data
                     (map #(select-keys % [:result]))
                     flatten)
        how-many (->> results
                      (map #(vals %))
                      flatten)
        true-vals (filter true? how-many)]
    (count true-vals)))
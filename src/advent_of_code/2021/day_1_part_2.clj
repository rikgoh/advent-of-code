(ns advent-of-code.2021.day-1-part-2
  (:require
    [advent-of-code.utils.utils :as utils])
  )

(defonce day-1-input-file "./resources/2021/day-1.txt")
(defonce day-1-part-2-input (utils/read-input-file-integers day-1-input-file))

(defn get-sum-count [input-of-data]
  (let [input input-of-data
        first-six-items (take 6 input)
        first-three-items (take 3 first-six-items)
        last-three-items (take-last 3 first-six-items)
        first-three (reduce + (take 3 first-six-items))
        last-three (reduce + (take-last 3 first-six-items))
        is-higher (when (= 6 (count first-six-items))
                    (> last-three first-three))]
    (when (and (not-empty input) (= 6 (count first-six-items)))
      (conj [] {:first first-three :first-items first-three-items :second last-three :second-items last-three-items :result is-higher} (get-sum-count (drop 3 input)))
      )))

(defn number-of-sums []
  (let [data (remove empty? (flatten (get-sum-count day-1-part-2-input)))
        results (->> data
                     (map #(select-keys % [:result]))
                     flatten)
        how-many (->> results
                      (map #(vals %))
                      flatten)
        true-vals (filter true? how-many)]
    data))
(ns advent-of-code.2022.day-2-part-2-2022
  (:require
    [advent-of-code.utils.utils :as utils])
  (:import (java.util ArrayList)))

(defonce day-2-input-file "./resources/2022/day-2.txt")
(defonce day-2-part-2-input (utils/read-input-file-strings day-2-input-file))

(defonce rock #{"A" "X"})
(defonce paper #{"B" "Y"})
(defonce scissors #{"C" "Z"})

(defonce rock-point 1)
(defonce paper-point 2)
(defonce scissors-point 3)
(defonce draw 3)
(defonce win 6)
(defonce lost 0)

(defn is-a-shape [shape-list shape]
  (contains? shape-list shape))

(defn find-shape-to-lose [player1]
  (cond
    (is-a-shape rock player1) scissors-point
    (is-a-shape paper player1) rock-point
    (is-a-shape scissors player1) paper-point
    :else 0)
  )

(defn find-shape-to-draw [player1]
  (cond
    (is-a-shape rock player1) rock-point
    (is-a-shape paper player1) paper-point
    (is-a-shape scissors player1) scissors-point
    :else 0)
  )

(defn find-shape-to-win [player1]
  (cond
    (is-a-shape rock player1) paper-point
    (is-a-shape paper player1) scissors-point
    (is-a-shape scissors player1) rock-point
    :else 0)
  )

(defn round-outcome [player1 player2]
  (cond
    (= "X" player2) (+ lost (find-shape-to-lose player1))
    (= "Y" player2) (+ draw (find-shape-to-draw player1))
    (= "Z" player2) (+ win (find-shape-to-win player1))
    :else 0))

(defn which-shape-and-outcome [input-file]
  (let [input input-file
        round (into [] (first input))
        first-player-shape (str (first round))
        second-player-shape (str (last round))]
    (when (and (not-empty input) (and (some? first-player-shape) (some? second-player-shape)))
      (conj [] (round-outcome first-player-shape second-player-shape) (which-shape-and-outcome (drop 1 input))))))

(defn player-2-score []
  (->> (which-shape-and-outcome day-2-part-2-input)
       flatten
       (remove nil?)
       (reduce +)))


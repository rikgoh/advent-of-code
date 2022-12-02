(ns advent-of-code.2022.day-2-part-1-2022
  (:require
    [advent-of-code.utils.utils :as utils]))

(defonce day-2-input-file "./resources/2022/day-2.txt")
(defonce day-2-part-1-input (utils/read-input-file-strings day-2-input-file))

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

(defn get-shape-point [player2]
  (cond
    (is-a-shape rock player2) rock-point
    (is-a-shape paper player2) paper-point
    (is-a-shape scissors player2) scissors-point
    :else 0))

(defn round-outcome [player1 player2]
  (cond
    (and (is-a-shape rock player1) (is-a-shape rock player2)) (+ rock-point draw)
    (and (is-a-shape paper player1) (is-a-shape paper player2)) (+ paper-point draw)
    (and (is-a-shape scissors player1) (is-a-shape scissors player2)) (+ scissors-point draw)
    (and (is-a-shape paper player1) (is-a-shape scissors player2)) (+ win scissors-point)
    (and (is-a-shape rock player1) (is-a-shape paper player2)) (+ win paper-point)
    (and (is-a-shape scissors player1) (is-a-shape rock player2)) (+ win rock-point)
    :else (+ lost (get-shape-point player2))))

(defn which-shape-and-outcome [input-file]
  (let [input input-file
        round (into [] (first input))
        first-player-shape (str (first round))
        second-player-shape (str (last round))]
    (when (and (not-empty input) (and (some? first-player-shape) (some? second-player-shape)))
      (conj [] (round-outcome first-player-shape second-player-shape) (which-shape-and-outcome (drop 1 input))))))

(defn player-2-score []
  (->> (which-shape-and-outcome day-2-part-1-input)
       flatten
       (remove nil?)
       (reduce +)))


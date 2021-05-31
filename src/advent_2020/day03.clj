;; author: Mark W. Naylor
;; file:  day03.clj
;; date:  2021-May-28
(ns advent-2020.day03
  (:require [advent-2020.core :refer [input] ]))

(def test-data ["..##......."
                "#...#...#.."
                ".#....#..#."
                "..#.#...#.#"
                ".#...##..#."
                "..#.##....."
                ".#.#.#....#"
                ".#........#"
                "#.##...#..."
                "#...##....#"
                ".#..#...#.#"])

(def right-amount 3)                    ; Right step amount for Part 1.
(def down-amount 1)                     ; Down step amount for Part 1.
(def tree-char \#)
(def input-file "day03.txt")

(defn columns
  "How many \"openings\" and \"trees\" are in a given row of the input
  data. Assumes all the rows are the same size."
  [input]
  (-> input count))

(defn rows
  "How many rows are in the input data."
  [input]
  (count input))

(defn wrap-around
  "Handles the \"wrap-around\" for repeated rowitems, without actually duplicating the data."
  [step row-items]
  (mod step (count row-items)))


;; Function only used for Part 1.
(defn col-inc
  "Slide right the appropriate amount."
  [n]
  (+ n right-amount))

;; Function only used for Part 1.
(defn row-inc
  "Slide right the appropriate amount."
  ([]
   down-amount)
  ([n]
   (+ n down-amount)))

(defn tree?
  "Is the row item at `n' a tree?"
  [n row-items]
  (= tree-char (get row-items (wrap-around n row-items))))

(defn tree-count
  "Follow the slope through the forest grid, return the number of trees
  encounterd on the trip top to bottom."
  [forest row-inc col-inc]
  (letfn
      [
       (-tree-count
         [acc idx forest]
         (cond
           (empty? forest) acc
           :else (let [head (first forest)
                       tail (drop (row-inc) forest)
                       spot-count (if (tree? idx head) 1 0)]
                   (recur (+ acc spot-count) (col-inc idx) tail))))
       ]
    (-tree-count 0 0 forest)))

(defn part1-count
  ""
  []
  (tree-count (input input-file) row-inc col-inc))


;; ------------------------------------------------------------------------------
;; BSD 3-Clause License

;; Copyright © 2021, Mark W. Naylor
;; All rights reserved.

;; Redistribution and use in source and binary forms, with or without
;; modification, are permitted provided that the following conditions are met:

;; 1. Redistributions of source code must retain the above copyright notice, this
;;    list of conditions and the following disclaimer.

;; 2. Redistributions in binary form must reproduce the above copyright notice,
;;    this list of conditions and the following disclaimer in the documentation
;;    and/or other materials provided with the distribution.

;; 3. Neither the name of the copyright holder nor the names of its
;;    contributors may be used to endorse or promote products derived from
;;    this software without specific prior written permission.

;; THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
;; AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
;; IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
;; DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
;; FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
;; DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
;; SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
;; CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
;; OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
;; OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

;; author: Mark W. Naylor
;; file:  day06.clj
;; date:  2021-Jun-02
(ns advent-2020.day06
  (:require [advent-2020.core :refer [input group-lines]]
            [clojure.string :refer [join]]
            [clojure.set :refer [intersection]]))

(def input-file "day06.txt")

(defn group-uniques
  "Get the unique items from a group."
  [lines]
  (-> lines join set))

(defn group-uniques-count
  "Mash together the lines of the group, provide the number of unique characters."
  [lines]
  (-> lines group-uniques count))

(defn group-uniques-counts
  "Return the counts for each of the groups."
  [groups]
  (map group-uniques-count groups))

(defn group-uniques-counts-sum
  "Return the sum of all the unique group counts."
  [groups]
  (->> groups group-uniques-counts (reduce +)))

(defn group-exists-in-each
  "Return the characters found in every member line of the group."
  [lines]
  (->> lines (map set) (reduce intersection)))

(defn group-exists-in-each-count
  "Return the count of characters found in every member line of the group."
  [lines]
  (-> lines group-exists-in-each count))

(defn group-exists-in-each-counts
  "Return a collection the count of characters found in every member line of all the groups."
  [groups]
  (map group-exists-in-each-count groups))

(defn group-exists-in-each-counts-sum
  "Return the sum of all the exists-in-each group counts."
  [groups]
  (->> groups group-exists-in-each-counts (reduce +)))

(defn sum-part1
  "Part 1 exercise answer."
  []
  (-> input-file input group-lines group-uniques-counts-sum))

(defn sum-part2
  "Part 2 exercise answer."
  []
  (-> input-file input group-lines group-exists-in-each-counts-sum))



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

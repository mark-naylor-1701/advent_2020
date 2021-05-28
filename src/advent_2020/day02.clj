;; author: Mark W. Naylor
;; file:  day02.clj
;; date:  2021-May-28
(ns advent-2020.day02
  (:require [clojure.string :refer [split]]
            [advent-2020.core :refer [input]]))

(def file-name "day02.txt")
(def test-data ["1-3 a: abcde"
                "1-3 b: cdefg"
                "2-9 c: ccccccccc"])

(defn xor
  "Returns true if one true and one false. false otherwise."
  [bool1 bool2]
  (->> [bool1 bool2] (filter identity) (count) (= 1)))

(defn in-range?
  "Is the password described by the structure a valid one?"
  [pass-struct]
  (let [password (:password pass-struct)
        min (:criterion1 pass-struct)
        max (:criterion2 pass-struct)
        test-char (:char pass-struct)]
    (->> (split password #"")
         (filter #(= (first %) test-char))
         (count)
         (#(<= min % max)))))

(defn one-index-match?
  "Is the password valid by only matching one char in each of the two given indexes?"
  [pass-struct]
  (let [idx1 (dec (:criterion1 pass-struct))
        idx2 (dec (:criterion2 pass-struct))
        password (:password pass-struct)
        char (:char pass-struct)
        char1 (nth password idx1)
        char2 (nth password idx2)]
    (xor (= char char1) (= char char2))))

(defn line-parts
  "Parse the line to pull out key elements"
  [line]
  (let [[a b c] (split line #" ")
        [min max] (->> (split a #"-") (map #(Integer/parseInt %)))
        char (first b)]
    {:criterion1 min :criterion2 max :char char :password c}))

(defn valid-count
  "Number of valid passwords in the input lines."
  [valid-fn input]
  (->> input (map line-parts) (map valid-fn) (filter identity) (count)))

(defn valid-count-1
  "Number of valid passwords in the part 1 exercise."
  []
  (valid-count in-range? (input file-name)))

(defn valid-count-2
  "Number of valid passwords in the part 2 exercise."
  []
  (valid-count one-index-match? (input file-name)))



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

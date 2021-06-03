;; author: Mark W. Naylor
;; file:  day05.clj
;; date:  2021-Jun-01
(ns advent-2020.day05
  (:require [advent-2020.core :refer [input]]
            [clojure.string :as string]
            [clojure.set :refer [difference]]))

(def input-file "day05.txt")

(defn binary-code
  "Converts the seat code to a binary string."
  [seat-code]
  (->> seat-code
       (#(string/replace % #"[BR]" "1"))
       (#(string/replace % #"[FL]" "0"))))

(defn binary-str->int
  "Converts a binary string to an decimal value."
  [bin-str]
  (->> bin-str
       (#(string/replace % #"[BR]" "1"))
       (#(string/replace % #"[FL]" "0"))
       (#(Integer/parseInt % 2))))

(defn seat-code->int
  "Converts the seat code to the equivalent decimal value."
  [seat-code]
  (-> seat-code binary-code binary-str->int))

(defn largest-seat-code
  [decimal-seat-codes]
  (apply max decimal-seat-codes))

(defn file->int-seat-codes
  "Transform from source file to usable decimal codes"
  []
  (->> input-file input (map seat-code->int)))

(defn largest-code-part1
  "Find the largest decimal seat code in the data set."
  []
  (->> (file->int-seat-codes) largest-seat-code))

(defn missing-seat
  "Find the only seat that does not have a boarding pass."
  []
  (let [occupied-seats (set (file->int-seat-codes))
        lowest (apply min occupied-seats)
        highest (apply max occupied-seats)
        all-seats (set (range lowest (inc highest)))]
    (first (difference all-seats occupied-seats))))

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

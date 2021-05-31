;; author: Mark W. Naylor
;; file:  day04.clj
;; date:  2021-May-31
(ns advent-2020.day04
  (:require [advent-2020.core :refer [input]]
            [clojure.string :refer [join split]]
            [clojure.set :refer [intersection]]))

;; Values needed by this moduele.

(def test-file
  "The name of the file containing the test data."
  "day04.txt")

(def validations
  "A look up table that links passport fields to their validation
  functions."
  {"byr" byr-valid?
   "hgt" hgt-valid?
   "hcl" hcl-valid?
   "ecl" ecl-valid?
   "iyr" iyr-valid?
   "eyr" eyr-valid?
   "cid" cid-valid?
   "pid" pid-valid?})

(def passport-keys
  "Set of currently known passport field keys."
  #{
    "byr" ; Birth Year
    "iyr" ; Issue Year
    "eyr" ; Expiration Year
    "hgt" ; Height
    "hcl" ; Hair Color
    "ecl" ; Eye Color
    "pid" ; Passport ID
    "cid" ; Country ID
    })

(def required-keys
  "Keys that a passport needs in order to be valid in Part 1."
  (disj passport-keys "cid"))

;; Test data section ;;;;;;;;;;;;;;;;;;;

(def test-data
  "Raw passport lines for Part 1."
  ["ecl:gry pid:860033327 eyr:2020 hcl:#fffffd"
   "byr:1937 iyr:2017 cid:147 hgt:183cm"
   ""
   "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884"
   "hcl:#cfa07d byr:1929"
   ""
   "hcl:#ae17e1 iyr:2013"
   "eyr:2024"
   "ecl:brn pid:760753108 byr:1931"
   "hgt:179cm"
   ""
   "hcl:#cfa07d eyr:2025 pid:166559648"
   "iyr:2011 ecl:brn hgt:59in"])

(def field-test-data
  "Mix of valid and invalid fields taken (mostly) from the Part 2 task.
  Additional entries created upon discoveries of cases not listed in
  description."
  [
   ["byr" true "2002"]
   ["byr" false "2003"]
   ["byr" false "999"]
   ["iyr" false "2009"]
   ["iyr" true "2010"]
   ["iyr" true "2020"]
   ["iyr" false "2021"]
   ["eyr" false "2019"]
   ["eyr" true "2020"]
   ["eyr" true "2030"]
   ["eyr" false "2031"]
   ["hgt" true "60in"]
   ["hgt" true "190cm"]
   ["hgt" false "190in"]
   ["hgt" false "190"]
   ["hcl" true "#123abc"]
   ["hcl" false "#123abz"]
   ["hcl" false "123abc"]
   ["ecl" true "brn"]
   ["ecl" false "wat"]
   ["pid" true "000000001"]
   ["pid" false "0123456789"]
   ["cid" true ""]
   ["cid" true "1234"]
   ["cid" true "Anything goes."]])

(def invalid-passports
  "As per Part 2 exercise."
  [
   "eyr:1972 cid:100"
   "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926"
   ""
   "iyr:2019"
   "hcl:#602927 eyr:1967 hgt:170cm"
   "ecl:grn pid:012533040 byr:1946"
   ""
   "hcl:dab227 iyr:2012"
   "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277"
   ""
   "hgt:59cm ecl:zzz"
   "eyr:2038 hcl:74454a iyr:2023"
   "pid:3556412378 byr:2007"])

(def valid-passports
  "As per Part 2 exercise."
  [
   "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980"
   "hcl:#623a2f"
   ""
   "eyr:2029 ecl:blu cid:129 byr:1989"
   "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm"
   ""
   "hcl:#888785"
   "hgt:164cm byr:2001 iyr:2015 cid:88"
   "pid:545766238 ecl:hzl"
   "eyr:2022"
   ""
   "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719"
   ])

;; End of test data ;;;;;;;;;;;;;;;;;;;;

(defn passport-lines
  "Breaks the batch file up into raw strings representing passpor data. A collection of collections."
  [input-lines]
  (letfn [(-passport-lines
            [lines-acc passport-acc lines]
            (let [head (first lines)
                  tail (rest lines)]
              (cond
                (empty? lines) (if (empty? lines-acc) passport-acc (conj passport-acc lines-acc))
                (empty? head) (recur [] (conj passport-acc lines-acc) tail)
                :else (recur (conj lines-acc head) passport-acc tail)
                )))]
    (-passport-lines [] [] input-lines)))

(defn lines->passport
  "From raw lines of passport data, contruct a passport."
  [lines]
  (->> lines
       (join " ")
       (#(split % #" +"))
       (map #(split % #":"))
       (into {})))

(defn valid-passport-keys?
  "Does the passport have the required keys?"
  [passport]
  (let [keyset (set (keys passport))]
    (= required-keys (intersection keyset required-keys))))

(defn valid-keyed-passport-count
  "How many passports in the collection are valid?"
  [passports]
  (->> passports (map valid-passport-keys?) (filter identity) count))

(defn passport-count-part1
  "\"Main\" function to solve the Part 1 exercise."
  []
  (->> test-file
       input
       passport-lines
       (map lines->passport)
       valid-keyed-passport-count))

;; Validation rules
(defn default-invalid
  "Both a temp value for the validations table and a validation function
  for non existing keys."
  [str-val]
  false)

(defn middle
  "Sort the three items and return the second one. Core for validation
  functions dealing with ranges."
  [test low high]
  (second (sort [test low high])))

(def year-re
  "Pattern for the year string."
  #"[0-9]{4}")

(def not-empty?
  "Does the string/collection have something in it? A function composition."
  (comp not empty?))

(defn valid-year-pattern
  "Is this a four digit string?"
  [str-val]
  (not-empty? (re-matches year-re str-val)))

(defn byr-valid?
  "Birth year must be four digits and between 1920 and 2002, inclusive."
  [byr]
  (and
   (valid-year-pattern byr)
   (= byr (middle byr "1920" "2002"))))

(defn iyr-valid?
  "Issue year must be four digits and between 2010 and 2020, inclusive"
  [iyr]
  (and
   (valid-year-pattern iyr)
   (= iyr (middle iyr "2010" "2020"))))

(defn eyr-valid?
  "Expiration year must be four digits and beween 2020 and 2030, inclusive."
  [eyr]
  (and
   (valid-year-pattern eyr)
   (= eyr (middle eyr "2020" "2030"))))

(def hgt-re #"([0-9]+)(in|cm)")

(defn hgt-valid?
  "Does the height fall in correct range?
  Units must be either \"cm\" or \"in\".
  The in range is 59 to 76 inclusive.
  The cm range is 150 to 193 inclusive."
  [hgt]
  (let [result (re-matches hgt-re hgt)
        number (second result)
        units (nth result 2)]
    (cond
      (empty? result) false
      (= units "cm") (= number (middle number "150" "193"))
      (= units "in") (= number (middle number "59" "76"))
      :else false)))

(def hcl-re #"#[0-9a-f]{6}")

(defn hcl-valid?
  "Does the hair color string math the pattern \"#[0-9a-f]{6}\"?"
  [hcl]
  (not-empty? (re-matches hcl-re hcl)))

(defn ecl-valid?
  "Is the eye color string in the collection of legal values?"
  [ecl]
  (contains? #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"} ecl))

(def pid-re #"[0-9]{9}")

(defn pid-valid?
  "Is the passport id a sequence of nine diits?"
  [pid]
  (not-empty? (re-matches pid-re pid)))

(defn cid-valid?
  "Any string is valid."
  [cid]
  true)

(defn field-valid?
  "Does the field have a value that matches the associated rule?"
  [field]
  (let [validate (validations (first field) default-invalid)
        value (second field)]
    (validate value)))

(defn passport-valid?
  "Are all the fields in a given passport valid?"
  [passport]
  (->> passport (map field-valid?) (reduce #(and %1 %2))))

(defn valid-checked-passport-count
  "How many passports in the collection are valid?"
  [passports]
  (->> passports
       (filter valid-passport-keys?)
       (map passport-valid?)
       (filter identity)
       count))

(defn passport-count-part2
  "\"Main\" function to solve the Part 2 exercise."
  []
  (->> test-file
       input
       passport-lines
       (map lines->passport)
       valid-checked-passport-count))
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

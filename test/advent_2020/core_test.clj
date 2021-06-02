(ns advent-2020.core-test
  (:require [clojure.test :refer :all]
            [advent-2020.core :refer :all]
            [clojure.string :refer [join]]))

(deftest file-name-test
  (testing
      "join now working to build file spec."
    (is (= (join "/" ["tasks" "day01.txt"]) "tasks/day01.txt"))))

(defn- quote-item
  "Wraps a string with single quotes"
  [str-val]
  (str "'" str-val "'"))

(defn- not-empty-msg
  "Wrap the item in quotes, append not empty suffix"
  [item]
  (str (quote-item item) " is not empty."))

(defn- empty-msg
  "Wrap the item in quotes, append empty suffix"
  [item]
  (str (quote-item item) " is empty."))


(deftest not-empty-test
  "Various scenarios for the not-empty? function."
  (let [empty-str ""
        full-str "Has characters"]
    (testing
        "Strings"
      (is (= true (not-empty? full-str)) (not-empty-msg full-str))
      (is (= false (not-empty? empty-str)) (empty-msg empty-str))))

  (let [empty-vec []
        full-vec [1]]
    (testing
        "Vectors"
      (is (= true (not-empty? full-vec)) (not-empty-msg full-vec))
      (is (= false (not-empty? empty-vec)) (empty-msg empty-vec))))

  (let [empty-map {}
        full-map {1 "one"}]
    (testing
        "Hashmaps"
      (is (= true (not-empty? full-map)) (not-empty-msg full-map))
      (is (= false (not-empty? empty-map)) (empty-msg empty-map))))

  (let [empty-set #{}
        full-set #{1 "one"}]
    (testing
        "Sets"
      (is (= true (not-empty? full-set)) (not-empty-msg full-set))
      (is (= false (not-empty? empty-set)) (empty-msg empty-set))))

  (let [empty-list '()
        full-list '(1 "one")]
    (testing
        "Lists"
      (is (= true (not-empty? full-list)) (not-empty-msg full-list))
      (is (= false (not-empty? empty-list)) (empty-msg empty-list)))))

(def passport-test-data
  "Raw passport lines."
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

(def question-test-data
  "Raw question response lines."
  ["abc"
   ""
   "a"
   "b"
   "c"
   ""
   "ab"
   "ac"
   ""
   "a"
   "a"
   "a"
   "a"
   ""
   "b"])

(deftest group-lines-test
  "Testing the batch lines to group lines."
  (let [raw-groups (group-lines passport-test-data)]
    (testing
        "The number of passports."
      (is (= 4 (count raw-groups))))
    (testing
        "The number lines per passport."
      (is (= [2 2 4 2] (map count raw-groups)))))

  (let [raw-groups (group-lines question-test-data)]
    (testing
        "The number of passports."
      (is (= 5 (count raw-groups))))
    (testing
        "The number lines per passport."
      (is (= [1 3 2 4 1] (map count raw-groups))))))

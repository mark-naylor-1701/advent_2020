(ns advent-2020.day06-test
  (:require [clojure.test :refer :all]
            [advent-2020.test-support :refer [fmt]]
            [advent-2020.day06 :refer :all]
            [advent-2020.core :refer [group-lines]]
            [clojure.string :refer [join]]))

(def group-test-data
  "First data sample from Day 6."
  ["abcx"
   "abcy"
   "abcz"])

(def five-group-test-data
  "Second data sample from Day 6."
  ["abc"
   ""
   "a" "b" "c"
   ""
   "ab" "ac"
   ""
   "a" "a" "a" "a"
   ""
   "b"])

(deftest group-uniques-count-test
  "Validate the count for the first (single) group sample."
  (let [expected 6
        actual (group-uniques-count group-test-data)]
    (testing
        (is (= expected actual) (fmt "Answer count" expected actual)))))

(deftest five-group-test
  "Make sure the group and line counts are correct for the data set."
  (let [groups (group-lines five-group-test-data)
        group-count-expected 5
        group-count-actual (count groups)
        line-count-expected [1 3 2 4 1]
        line-count-actual (map count groups)]
    (testing
        "Number of groups"
      (is (= group-count-expected
             group-count-actual)
          (str "Count should be " group-count-expected ", not " group-count-actual)))
    (testing
        "Number of lines"
      (is (= line-count-expected line-count-actual)
          (str "Line counts should be " line-count-expected ", not " line-count-actual)))))

(deftest group-uniques-counts-test
  "The unique numbers from each group"
  (let [expected [3 3 3 1 1]
        actual (group-uniques-counts (group-lines five-group-test-data))]
    (testing
        "Five group test data"
      (is (= expected actual)) (fmt "Counts" expected actual))))

(deftest group-uniques-counts-sum-test
  "The sum of the counts."
  (let [expected 11
        actual (group-uniques-counts-sum (group-lines five-group-test-data))]
    (is (= actual expected) (fmt "Sum" expected actual))))

(deftest group-uniques-test
  "Test unique item selection."
  (let [expected #{\a \b \c \y \z \x}
        actual (group-uniques group-test-data)]
    (testing
        (is (= expected actual)
            (fmt "Unique items" expected actual)))))

(deftest group-exists-in-each-test
  "Test for characters found in each group."
  (let [expected #{\a \b \c}
        actual (group-exists-in-each group-test-data)]
    (testing
        "Test against group-test-data"
      (is (= expected actual) (fmt "Exists in all" expected actual)))))

(deftest group-exists-in-each-count-test
  "Test for characters found in each group."
  (let [expected 3
        actual (group-exists-in-each-count group-test-data)]
    (testing
        (str "Test against group-test-data: " group-test-data)
      (is (= expected actual) (fmt "Exists in each count" expected actual)))))

(deftest group-exists-in-each-counts-test
  "Test for existance of all, for all groups"
  (let [expected [3 0 1 1 1]
        actual (group-exists-in-each-counts (group-lines five-group-test-data))]
    (testing
        (str "Testing for " group-test-data)
      (is (= expected actual) (fmt "Exit all counts " expected actual)))))

(deftest group-exists-in-each-counts-sum-test
  "Test for existance of all, for all groups"
  (let [expected 6
        actual (group-exists-in-each-counts-sum (group-lines five-group-test-data))]
    (testing
        (str "Testing for " group-test-data)
      (is (= expected actual) (fmt "Exit all counts " expected actual)))))

(ns advent-2020.day02-test
  (:require [clojure.test :refer :all]
            [advent-2020.day02 :refer :all]))

(deftest valid-count-1-test []
  "Test for part 1 valid count"
  (testing
      "Should be 2 valid passwords."
    (is (= 2 (valid-count in-range? test-data)))))

(deftest valid-count-2-test []
  "Test for part 1 valid count"
  (testing
      "Should be 1 valid password."
    (is (= 1 (valid-count one-index-match? test-data)))))

(deftest xor-test
  "Test all outcomes of the xor function"
  (testing
      "One, and only one, argument should be true.")
  (is (= false (xor false false)))
  (is (= false (xor true true)))
  (is (= true (xor true false)))
  (is (= true (xor false true))))

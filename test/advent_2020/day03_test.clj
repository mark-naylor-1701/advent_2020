(ns advent-2020.day03-test
  (:require [clojure.test :refer :all]
            [advent-2020.day03 :refer :all]))

(deftest dimenion-test []
  "Test for part 1 valid count"
  (testing
      "Rows and columns should both be 11 in size."
    (is (= 11 (rows test-data)))
    (is (= 11 (columns test-data)))))

(deftest )

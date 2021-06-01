(ns advent-2020.day05-test
  (:require [clojure.test :refer :all]
            [advent-2020.day05 :refer :all]))

(defn code
  [coll]
  (first coll))

(defn binary
  [coll]
  (second coll))

(defn decimal
  [coll]
  (nth coll 2))

(deftest seat-decode-test
  "Testing conversion of seat to binary string, and then to decimal."
  (testing
      "Seat code to binary string."
    (is (= (map binary test-data) (map #(-> % code binary-code) test-data)) ))

  (testing
      "Binary string to integer"
    (is (= (map decimal test-data) (map #(-> % binary binary-str->int) test-data))))

  (testing
      "Seat code to decimal value."
    (is (= (map decimal test-data) (map #(-> % binary seat-code->int) test-data))))

  (testing
      "Find the largest seat code, as a decimal value."
    (is (= 820 (largest-seat-code (map binary test-data))))))

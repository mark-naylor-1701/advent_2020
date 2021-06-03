(ns advent-2020.day05-test
  (:require [clojure.test :refer :all]
            [advent-2020.day05 :refer :all]
            [advent-2020.test-support :refer [fmt]]))

(def test-data [["BFFFBBFRRR" "1000110111" 567]
                ["FFFBBBFRRR" "0001110111" 119]
                ["BBFFBBFRLL" "1100110100" 820]])

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
  (let [expected 820
        actual (largest-seat-code (map decimal test-data))]

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
      (is (= expected actual)
          (fmt "Largest seat" expected actual)))))

(ns advent-2020.day03-test
  (:require [clojure.test :refer :all]
            [advent-2020.day03 :refer :all]))

(deftest dimenion-test
  "Test for part 1 valid count"
  (testing
      "Rows and columns should both be 11 in size."
    (is (= 11 (rows test-data)))
    (is (= 11 (columns test-data)))))

(deftest wrap-test
  "Test that indexes incremented beyond the upper bound of a row map
  to the correct index in the actual data sample."
  (testing
      "Proper wrap outcomes."
    (let [items (first test-data)]
      (is (= 0 (wrap-around 0 items)))   ; left edge
      (is (= 5 (wrap-around 5 items)))   ; middle value
      (is (= 10 (wrap-around 10 items))) ; right edge
      (is (= 0 (wrap-around 11 items)))  ; one past the right edge
      (is (= 1 (wrap-around 12 items)))  ; two past the right edge
      )))


(deftest increment-test
  "Test the row and column increments."
  (testing
      "Column increase should be 3."
    (is (= 3 (col-inc 0))))
  (testing
      "Row increase should be 1."
    (is (= 8 (row-inc 7)))))

(deftest tree-test
  "Test whether the indexed item is a tree."
  (let [row-items (first test-data)]
    (testing
        "These should be trees."
      (is (= true (tree? 2 row-items)))  ; first actual tree
      (is (= true (tree? 3 row-items)))  ; second actual tree
      (is (= true (tree? 13 row-items))) ; first wrap-around tree
      )
    (testing
        "These should not be trees."
      (is (= false (tree? 0 row-items)))  ; first open space
      (is (= false (tree? 1 row-items)))  ; second open space
      (is (= false (tree? 12 row-items)))) ; second wrap-around open space
    ))

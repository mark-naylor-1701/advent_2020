

(ns advent-2020.day01-test
  (:require [clojure.test :refer :all]
            [advent-2020.day01 :refer :all]))

(def test-data [1721 979 366 299 675 1456])

(deftest solve-test []
  "Test for defn `solve'."
  (testing
      "FIXME, I fail."
    (is (= 514579 (solve-pairs test-data)))))

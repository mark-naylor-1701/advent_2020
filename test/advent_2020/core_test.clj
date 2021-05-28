(ns advent-2020.core-test
  (:require [clojure.test :refer :all]
            [advent-2020.core :refer :all]
            [clojure.string :refer [join]]))

(deftest file-name-test
  (testing
      "join now working to build file spec."
    (is (= (join "/" ["tasks" "day01.txt"]) "tasks/day01.txt"))))

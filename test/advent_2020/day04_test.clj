(ns advent-2020.day04-test
  (:require [clojure.test :refer :all]
            [advent-2020.day04 :refer :all]))

(deftest passport-lines-test
  "Testing the batch data to passport lines."
  (let [raw-passport (passport-lines test-data)]
    (testing
        "The number of passports."
      (is (= 4 (count raw-passport))))
    (testing
        "The number lines per passport."
      (is (= [2 2 4 2] (map count raw-passport))))))

(deftest lines->passport-test
  "Testing generation of passport."
  (testing
      "First passport of the batch."
    (is (= {"ecl" "gry" "pid" "860033327" "eyr" "2020" "hcl" "#fffffd"
            "byr" "1937" "iyr" "2017" "cid" "147" "hgt" "183cm"}
           (lines->passport (first (passport-lines test-data)))))))

(deftest valid-passport-test
  "Testing the valid-passport function"
  (testing
      "First and third should be true."
    (is (= [true false true false]
           (map valid-passport? (->> test-data passport-lines (map lines->passport)))))))

(deftest valid-passport-count-test
  "Testing the passport counter."
  (testing
      "Should by 2 valid pasports in the test data."
    (is (= 2 (valid-keyed-passport-count
              (->> test-data passport-lines (map lines->passport)))))))

;; Part 2 support functions.

(defn field-test-items
  "Filters by key, returns validation result and test value."
  [key-str]
  (map rest
       (filter #(-> % first (= key-str))
               field-test-data)))

(deftest field-validation
  "Test the passport field validation functions."
  (testing "byr (birth year validations."
    (is (= (map first (field-test-items "byr"))
           (map byr-valid? (map second (field-test-items "byr"))))))

  (testing "iyr (issue year) validations."
    (is (= (map first (field-test-items "iyr"))
           (map iyr-valid? (map second (field-test-items "iyr"))))))

  (testing "eyr (expiration year) validations."
    (is (= (map first (field-test-items "eyr"))
           (map eyr-valid? (map second (field-test-items "eyr"))))))

  (testing "hgt (height) validations."
    (is (= (map first (field-test-items "hgt"))
           (map hgt-valid? (map second (field-test-items "hgt"))))))

  (testing "hcl (hair color) validations."
    (is (= (map first (field-test-items "hcl"))
           (map hcl-valid? (map second (field-test-items "hcl"))))))

  (testing "ecl (eye color) validations."
    (is (= (map first (field-test-items "ecl"))
           (map ecl-valid? (map second (field-test-items "ecl"))))))

  (testing "pid (passport id) validations."
    (is (= (map first (field-test-items "pid"))
           (map pid-valid? (map second (field-test-items "pid"))))))

  (testing "cid (country id) validations."
    (is (= (map first (field-test-items "cid"))
           (map cid-valid? (map second (field-test-items "cid")))))))

(deftest field-valid-test
  (testing "One valid field."
    (is (= true (field-valid? ["ecl" "gry"]))))
  (testing "One invalid field."
    (is (= false (field-valid? ["ecl" "red"])))))

(deftest passport-valid-test
  "Testing passport validation."
  (let [passports (->> invalid-passports
                       passport-lines
                       (map lines->passport))]
    (testing "Invalid passports."
      (is (= (repeat (count passports) false)
             (map passport-valid? passports)))))

  (let [passports (->> valid-passports
                       passport-lines
                       (map lines->passport))]

    (testing "Valid passports."
      (is (= (repeat (count passports) true)
             (map passport-valid? passports))))))

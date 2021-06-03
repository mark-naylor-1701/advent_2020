(ns advent-2020.day04-test
  (:require [clojure.test :refer :all]
            [advent-2020.day04 :refer :all]))

(def test-data
  "Raw passport lines for Part 1."
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

(def field-test-data
  "Mix of valid and invalid fields taken (mostly) from the Part 2 task.
  Additional entries created upon discoveries of cases not listed in
  description."
  [
   ["byr" true "2002"]
   ["byr" false "2003"]
   ["byr" false "999"]
   ["iyr" false "2009"]
   ["iyr" true "2010"]
   ["iyr" true "2020"]
   ["iyr" false "2021"]
   ["eyr" false "2019"]
   ["eyr" true "2020"]
   ["eyr" true "2030"]
   ["eyr" false "2031"]
   ["hgt" true "60in"]
   ["hgt" true "190cm"]
   ["hgt" false "190in"]
   ["hgt" false "190"]
   ["hcl" true "#123abc"]
   ["hcl" false "#123abz"]
   ["hcl" false "123abc"]
   ["ecl" true "brn"]
   ["ecl" false "wat"]
   ["pid" true "000000001"]
   ["pid" false "0123456789"]
   ["cid" true ""]
   ["cid" true "1234"]
   ["cid" true "Anything goes."]])

(def invalid-passports
  "As per Part 2 exercise."
  [
   "eyr:1972 cid:100"
   "hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926"
   ""
   "iyr:2019"
   "hcl:#602927 eyr:1967 hgt:170cm"
   "ecl:grn pid:012533040 byr:1946"
   ""
   "hcl:dab227 iyr:2012"
   "ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277"
   ""
   "hgt:59cm ecl:zzz"
   "eyr:2038 hcl:74454a iyr:2023"
   "pid:3556412378 byr:2007"])

(def valid-passports
  "As per Part 2 exercise."
  [
   "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980"
   "hcl:#623a2f"
   ""
   "eyr:2029 ecl:blu cid:129 byr:1989"
   "iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm"
   ""
   "hcl:#888785"
   "hgt:164cm byr:2001 iyr:2015 cid:88"
   "pid:545766238 ecl:hzl"
   "eyr:2022"
   ""
   "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719"
   ])

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
           (map valid-passport-keys? (->> test-data passport-lines (map lines->passport)))))))

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

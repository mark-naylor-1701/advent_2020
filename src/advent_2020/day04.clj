;; author: Mark W. Naylor
;; file:  day04.clj
;; date:  2021-May-31
(ns advent-2020.day04
  (:require [advent-2020.core :refer [input]]))

(def test-data [
                "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd"
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

;; ------------------------------------------------------------------------------
;; BSD 3-Clause License

;; Copyright © 2021, Mark W. Naylor
;; All rights reserved.

;; Redistribution and use in source and binary forms, with or without
;; modification, are permitted provided that the following conditions are met:

;; 1. Redistributions of source code must retain the above copyright notice, this
;;    list of conditions and the following disclaimer.

;; 2. Redistributions in binary form must reproduce the above copyright notice,
;;    this list of conditions and the following disclaimer in the documentation
;;    and/or other materials provided with the distribution.

;; 3. Neither the name of the copyright holder nor the names of its
;;    contributors may be used to endorse or promote products derived from
;;    this software without specific prior written permission.

;; THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
;; AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
;; IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
;; DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
;; FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
;; DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
;; SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
;; CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
;; OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
;; OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

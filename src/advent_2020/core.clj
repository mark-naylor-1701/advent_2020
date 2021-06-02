(ns advent-2020.core
  (:require [clojure.string :refer [split join]]))

(def directory "tasks")
(def separator "/")
(def eoln #"\n")

(defn input
  "Open the file name, in the tasks directory, read it in, and break into separate lines."
  [file-name]
  (->> file-name
       (list directory)
       ((partial join separator))
       (slurp)
       (#(split % eoln))))

(def not-empty?
  "Does the string/collection have something in it? A function composition."
  (comp not empty?))

(defn group-lines
  "Breaks the batch file up into raw strings representing passpor data. A collection of collections."
  [input-lines]
  (letfn [(-group-lines
            [lines-acc group-acc lines]
            (let [head (first lines)
                  tail (rest lines)]
              (cond
                (empty? lines) (if (empty? lines-acc) group-acc (conj group-acc lines-acc))
                (empty? head) (recur [] (conj group-acc lines-acc) tail)
                :else (recur (conj lines-acc head) group-acc tail)
                )))]
    (-group-lines [] [] input-lines)))

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

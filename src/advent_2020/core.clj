(ns advent-2020.core
  (:require [clojure.string :refer [split]]))

(defn input
  "Open the file name, in the tasks directory, read it in, and break into separate lines."
  [file-name]
  (->> file-name (str "tasks/") (slurp) (#(split % #"\n"))))

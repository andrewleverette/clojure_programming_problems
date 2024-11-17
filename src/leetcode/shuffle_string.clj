(ns leetcode.shuffle-string)

(defn shuffle-string
  "Given a string s and an integer array indices of the same length,
  return the characters of s in the order specified by indices."
  [s indices]
  (if (seq indices)
    (->> s
         (map vector indices)
         (sort-by first)
         (map second)
         (apply str))
    s))

(ns leetcode.reverse-string)

(defn reverse-string
  "Given a sequence of characters, reverse it"
  [s]
  (loop [[c & cs] (seq s)
         reversed '()]
    (if (nil? c)
      reversed
      (recur cs (cons c reversed)))))

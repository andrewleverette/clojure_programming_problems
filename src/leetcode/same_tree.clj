(ns leetcode.same-tree)

(defn is-same-tree
  "Given the roots of two binary trees p and q, 
  write a function to check if they are the same or not."
  [p q]
  (cond
    (and (nil? p) (nil? q) true)
    (or (nil? p) (nil? q) false)
    (not= (:value p) (:value q)) false
    :else (and (is-same-tree (:left p) (:left q))
               (is-same-tree (:right p) (:right q)))))

(is-same-tree {:value 1} {:value 1})

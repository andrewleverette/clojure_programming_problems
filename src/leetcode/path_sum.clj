(ns leetcode.path-sum)

(defn has-path-sum?
  "Given the root of a binary tree and an integer target,
  return true if the tree has a root-to-leaf path whose values 
  sum to the given target."
  [root targetSum]
  (loop [stack (if (nil? root) [] [[root targetSum]])]
    (if (empty? stack)
      false
      (let [[node sum] (peek stack)
            {:keys [value left right]} node
            new-sum (- sum value)]
        (cond
          (and (nil? left) (nil? right) (= value sum)) true
          :else (recur (-> stack
                           pop
                           (cond-> left (conj [left new-sum]))
                           (cond-> right (conj [right new-sum])))))))))

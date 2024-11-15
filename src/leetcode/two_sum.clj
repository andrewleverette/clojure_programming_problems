(ns leetcode.two-sum)

(defn two-sum
  "Returns the indices of the two numbers that sum to the target"
  [nums target]
  (->> nums
       (map-indexed vector)
       (reduce
        (fn [complements [i x]]
          (if-let [k (get-in complements [:complements x])]
            (reduced {:result [k i]})
            (assoc-in complements [:complements (- target x)] i)))
        {:complements {}})
       :result))

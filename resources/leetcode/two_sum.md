# Two Sum

**Source:** [https://leetcode.com/problems/two-sum/](https://leetcode.com/problems/two-sum/)
**Code:** [https://github.com/andrewleverette/clojure_programming_problems/blob/main/leetcode/two_sum.clj](https://github.com/andrewleverette/clojure_programming_problems/blob/main/leetcode/two_sum.clj)

## Description

Given an array of integers `nums` and an integer `target`, return indices of the two numbers such that they add up to `target`.
If a solution does not exist, return nil.

**Constraints:**

A number with the same index must not be used twice.

Only one valid solution exists.

- `2 <= nums.length <= 10^4`
- `-10^9 <= nums[i] <= 10^9`
- `-10^9 <= target <= 10^9`

## Solutions

### Brute Force

The brute force approach is to inspect every pair of numbers, skipping pairs that have the same index,
and check if the sum of the pair equals the target.

```clojure
(defn two-sum
  "Returns the indices of the two numbers that sum to the target"
  [nums target]
  (when (> (count nums) 1)
    (let [indexed (map-indexed vector nums)]
      (first (for [[i x] indexed
                   [j y] indexed
                   :when (and (> j i) (= target (+ x y)))]
               [i j])))))
```

**Complexity:**
- Time: O(n^2)
- Space: O(1), effectively constant

### Hash Map

The hash map approach is to use a hash map to store the difference between the target and a number along with the number's index. 
This approach allows for short-circuiting the process by checking to see if a number exists in the hash map. If it does, then
the indices of the two numbers are returned. Otherwise, `nil` is returned.

```clojure
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
```

**Complexity:**
- Time: O(n)
- Space: O(n)

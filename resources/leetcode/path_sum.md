# Path Sum

**Source:** [https://leetcode.com/problems/path-sum/](https://leetcode.com/problems/path-sum/)

**Code:** [path_sum.clj](https://github.com/andrewleverette/clojure_programming_problems/blob/main/leetcode/path_sum.clj)

## Description

Given the `root` of a binary tree and an integer `targetSum`, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals `targetSum`.

A leaf is a node with no children.

### Examples



### Constraints



## Analysis

### Problem Components

- Binary Tree
- Depth-first search
- Summation
- Root-to-leaf path

### Solution Components

- Tree representation
- Resursive vs iterative traversal
  - Recursive approach -> DFS, potential stack overflow
  - Iterative approach -> Stack, more verbose
- Path sum calculation


## Solutions

### Recursive Solution

```clojure
(defn has-path-sum?
  "Given the root of a binary tree and an integer target,
  return true if the tree has a root-to-leaf path whose values 
  sum to the given target."
  [root target]
  (if (nil? root)
    false
    (let [{:keys [value left right]} root]
      (if (and (nil? left) (nil? right))
        (= target value)
        (or (has-path-sum? left (- target value))
            (has-path-sum? right (- target value)))))))
```

### Iterative Solution

```clojure
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
```

# Same Tree

**Source:** [Same Tree](https://leetcode.com/problems/same-tree/)

**Code:** [same_tree.clj](https://github.com/andrewleverette/clojure_programming_problems/blob/main/leetcode/same_tree.clj)

## Description

Given the roots of two binary trees p and q, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

### Examples

**Example 1:**

Input: p = [1,2,3], q = [1,2,3]
Output: true

**Example 2:**

Input: p = [1,2], q = [1,null,2]
Output: false

**Example 3:**

Input: p = [1,2,1], q = [1,1,2]
Output: false
  
### Constraints

- The number of nodes in both trees is in the range `[0, 100]`.
- `-104 <= Node.val <= 104`

## Analysis

### Problem Components

- Binary Tree
- Depth-first search
- Tree comparison
- Pre-order traversal -> [root, left, right]

### Solution Components

- Tree representation
- Recursive vs. iterative traversal
  - Iterative -> Stack to hold node pairs
- Short-circuit evaluation


## Solutions

### Recursive Solution

```clojure
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
```

### Iterative Solution

```clojure
(defn is-same-tree
  "Given the roots of two binary trees p and q, 
  write a function to check if they are the same or not."
  [p q]
  (loop [stack [[p q]]]
    (if (empty? stack)
      true
      (let [[node1 node2] (peek stack)]
        (cond
          (and (nil? node1) (nil? node2)) (recur (pop stack))
          (or (nil? node1) (nil? node2)) false
          (not= (:value node1) (:value node2)) false
          :else (recur (-> stack
                           (conj [(:left node1) (:left node2)])
                           (conj [(:right node1) (:right node2)]))))))))
```

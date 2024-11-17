# Binary Tree Inorder Traversal

**Source:** [Binary Tree Inorder Traversal](https://leetcode.com/problems/binary-tree-inorder-traversal/)

**Code:** [binary_tree_inorder_traversal.clj](https://github.com/andrewleverette/clojure_programming_problems/blob/main/leetcode/binary_tree_inorder_traversal.clj)

## Description

Given the root of a binary tree, return the inorder traversal of its nodes' values.

### Examples

**Example 1:**

Input: root = [1,null,2,3]
Output: [1,3,2]

**Example 2:**

Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
Output: [4,2,6,5,7,1,3,9,8]

**Example 3:**

Input: root = []
Output: []

**Example 4:**

Input: root = [1]
Output: [1]

### Constraints

- The number of nodes in the tree is in the range `[0, 100]`.
- `-100 <= Node.val <= 100`

## Analysis

### Problem Components

- Binary Tree
- In-order traversal -> [left, root, right]
- Depth-first search

### Solution Components

- Tree representation
- Recursive vs. iterative traversal
- Sequence aggregation
  - Recursive -> concatenation
  - Iterative -> stack

## Solutions

### Standard Recursive Solution

This solution recursively concatenates the nodes using inorder traversal.

```clojure
(defn inorder-traversal
  "Given a binary tree, return the inorder traversal of its nodes' values."
  [root]
  (if root
    (concat
     (inorder-traversal (:left root))
     [(:value root)]
     (inorder-traversal (:right root)))
    []))
```

### Standard Iterative Solution

```clojure
(defn inorder-traversal
  "Given a binary tree, return the inorder traversal of its nodes' values."
  [root]
  (loop [stack []
         current root
         result []]
    (cond
      (and (empty? stack) (nil? current)) result
      current (recur (conj stack current) (:left current) result)
      :else (let [node (peek stack)]
              (recur (pop stack)
                     (:right node)
                     (conj result (:value node)))))))
```

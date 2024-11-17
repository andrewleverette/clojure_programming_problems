(ns leetcode.binary-tree-inorder-traversal)

(defn inorder-traversal
  "Given a binary tree, return the inorder traversal of its nodes' values."
  [root]
  (if root
    (concat
     (inorder-traversal (:left root))
     [(:value root)]
     (inorder-traversal (:right root)))
    []))

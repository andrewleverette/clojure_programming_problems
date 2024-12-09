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

(defn preorder-traversal
  [root]
  (if root
    (concat
     [(:value root)]
     (preorder-traversal (:left root))
     (preorder-traversal (:right root)))))

(defn postorder-traversal
  [root]
  (if root
    (concat
     (postorder-traversal (:left root))
     (postorder-traversal (:right root))
     [(:value root)])))

(ns leetcode-test.binary-tree-inorder-traversal-test
  (:require [clojure.test :refer [deftest is testing]]
            [leetcode.binary-tree-inorder-traversal :refer [inorder-traversal]]))

(deftest leetcode-binary-tree-inorder-traversal-tests
  (testing "An empty tree returns an empty list"
    (is (empty? (inorder-traversal nil))))
  (testing "A tree with one node returns a list containing that node"
    (is (= [1] (inorder-traversal {:value 1}))))
  (testing "A balanaced tree of three nodes"
    (is (= [1 2 3] (inorder-traversal {:value 2
                                       :left {:value 1}
                                       :right {:value 3}}))))
  (testing "A tree with all left nodes"
    (is (= [1 2 3] (inorder-traversal {:value 3
                                       :left {:value 2
                                              :left {:value 1}}}))))
  (testing "A tree with all right nodes"
    (is (= [1 2 3] (inorder-traversal {:value 1
                                       :right {:value 2
                                               :right {:value 3}}})))))

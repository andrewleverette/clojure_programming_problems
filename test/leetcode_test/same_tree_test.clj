(ns leetcode-test.same-tree-test
  (:require [clojure.test :refer [deftest is testing]]
            [leetcode.same-tree :refer [is-same-tree]]))

(deftest leetcode-same-tree-tests
  (testing "Both trees are empty"
    (is (true? (is-same-tree nil nil))))
  (testing "One tree is empty"
    (is (false? (is-same-tree nil {:value 1})))
    (is (false? (is-same-tree {:value 1} nil)))))

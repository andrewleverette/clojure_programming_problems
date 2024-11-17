(ns leetcode-test.path-sum-test
  (:require [clojure.test :refer [deftest is testing]]
            [leetcode.path-sum :refer [has-path-sum?]]))

(deftest leetcode-path-sum-tests
  (testing "An empty tree returns false"
    (is (false? (has-path-sum? nil 0))))
  (testing "A tree with one node"
    (is (false? (has-path-sum? {:value 1} 0)))
    (is (true? (has-path-sum? {:value 1} 1))))
  (testing "A balanced tree"
    (is (true? (has-path-sum? {:value 1
                               :left {:value 2}
                               :right {:value 3}} 3)))
    (is (true? (has-path-sum? {:value 1
                               :left {:value 2}
                               :right {:value 3}} 4)))
    (is (false? (has-path-sum? {:value 1
                                :left {:value 2}
                                :right {:value 3}} 5))))
  (testing "A tree with all left nodes"
    (is (true? (has-path-sum? {:value 1
                               :left {:value 2
                                      :left {:value 3}}} 6)))
    (is (false? (has-path-sum? {:value 1
                                :left {:value 2
                                       :left {:value 3}}} 7))))
  (testing "A tree with all right nodes"
    (is (true? (has-path-sum? {:value 1
                               :right {:value 2
                                       :right {:value 3}}} 6)))
    (is (false? (has-path-sum? {:value 1
                                :right {:value 2
                                        :right {:value 3}}} 7))))
  (testing "An unbalanaced tree"
    (is (true? (has-path-sum? {:value 1
                               :left {:value 2
                                      :left {:value 3}}
                               :right {:value 4}} 6)))))


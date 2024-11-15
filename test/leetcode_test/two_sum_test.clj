(ns leetcode-test.two-sum-test
  (:require [clojure.test :refer [deftest is testing]]
            [leetcode.two-sum :refer [two-sum]]))

(deftest leetcode-two-sum-tests
  (testing "Integer array contains less than two elements"
    (is (nil? (two-sum [] 0)))
    (is (nil? (two-sum [0] 0))))
  (testing "Integer array contains two elements that sum to the target"
    (is (= [0 1] (two-sum [1 2] 3)))
    (is (= [0 1] (two-sum [-1 2] 1)))
    (is (= [0 1] (two-sum [0 1] 1))))
  (testing "Integer array contains two element that do not sum to the target"
    (is (nil? (two-sum [1 2] 4)))
    (is (nil? (two-sum [-1 2] 0)))
    (is (nil? (two-sum [0 1] 2))))
  (testing "Integer array contains three elements"
    (is (= [0 1] (two-sum [1 2 3] 3)))
    (is (= [0 2] (two-sum [1 2 3] 4)))
    (is (= [1 2] (two-sum [1 2 3] 5)))
    (is (nil? (two-sum [1 2 3] 6))))
  (testing "Standard test cases"
    (is (= [0 1] (two-sum [2 7 11 15] 9)))
    (is (= [1 2] (two-sum [3 2 4] 6)))
    (is (= [0 1] (two-sum [3 3] 6)))
    (is (nil? (two-sum [2 5 4 11] 10)))))



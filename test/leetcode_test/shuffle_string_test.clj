(ns leetcode-test.shuffle-string-test
  (:require [clojure.test :refer [deftest is testing]]
            [leetcode.shuffle-string :refer [shuffle-string]]))

(deftest leetcoded-shuffle-string-tests
  (testing "An empty string returns an empty string"
    (is (empty? (shuffle-string "" []))))
  (testing "An empty index array returns the original string"
    (is (= "hello" (shuffle-string "hello" []))))
  (testing "A string of length one returns the original string"
    (is (= "a" (shuffle-string "a" [0])))
    (is (= "b" (shuffle-string "b" [0]))))
  (testing "An ascending ordered index array returns the original string"
    (is (= "hello" (shuffle-string "hello" [0 1 2 3 4]))))
  (testing "A descending ordered index array reverse the origiinal string"
    (is (= "olleh" (shuffle-string "hello" [4 3 2 1 0]))))
  (testing "Standard test cases"
    (is (= "hello" (shuffle-string "elhlo" [1 2 0 3 4])))
    (is (= "leetcode" (shuffle-string "codeleet" [4 5 6 7 0 2 1 3])))))



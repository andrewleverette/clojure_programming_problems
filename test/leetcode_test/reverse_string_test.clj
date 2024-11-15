(ns leetcode-test.reverse-string-test
  (:require [clojure.test :refer [deftest is testing]]
            [leetcode.reverse-string :refer [reverse-string]]))

(deftest leetcode-reverse-string-tests
  (testing "An empty string returns an empty string"
    (is (empty? (reverse-string "")))
    (is (empty? (reverse-string nil))))
  (testing "A string of one character returns that character"
    (is (= [\a] (reverse-string "a")))
    (is (= [\b] (reverse-string "b"))))
  (testing "A string of two characters returns those characters swapped positions"
    (is (= [\b \a] (reverse-string "ab")))
    (is (= [\a \b] (reverse-string "ba"))))
  (testing "A string of three characters returns those characters swapped positions"
    (is (= [\c \b \a] (reverse-string "abc")))
    (is (= [\a \b \c] (reverse-string "cba"))))
  (testing "Standard test cases"
    (is (= [\o \l \l \e \h] (reverse-string "hello")))
    (is (= [\h \e \l \l \o] (reverse-string "olleh")))))


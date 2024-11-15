# Reverse String

**Source:** [Reverse String](https://leetcode.com/problems/reverse-string/)

## Description

Write a function that reverses a string. The input string is given as an array of characters `s`.

Since Clojure does not support in-place mutation or sequences, the returned solution will return a new sequence.

### Examples

**Example 1:**

Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]

**Example 2:**

Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]

### Constraints

- `1 <= s.length <= 10^5`
- `s[i]` is a printable ascii character.

## Solutions

### Standard Recursive Solution

Must use a vector in this case since characters are added
to the collection in reverse order.

```clojure
(defn reverse-string
  "given a sequence of characters, reverse it"
  [s]
  (if (empty? s)
    []
    (let [[c & cs] s]
      (conj (reverse-string cs) c))))
```

**Complexity:**

- Time: O(n)
- Space: O(n)

### Loop/Recur Solution

This solution uses a list instead of a vector since
the reversed string is built as characters visited.

```clojure
(defn reverse-string
  "Given a sequence of characters, reverse it"
  [s]
  (loop [[c & cs] (seq s)
         reversed '()]
    (if (nil? c)
      reversed
      (recur cs (cons c reversed)))))
```

### Divide and Conquer Solution

This was just a fun way to implement the solution. Basically,
this implementation splits the string in half and recursively
concatenates the right half to the left half.

```clojure
(defn reverse-string
  "Given a sequence of characters, reverse it"
  (if (<= (count s) 1)
    s
    (let [mid (quot (count s) 2)
          [left right] (split-at mid s)]
      (concat
       (reverse-string-split right)
       (reverse-string-split left)))))
```

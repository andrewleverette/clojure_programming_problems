# Shuffle String

**Source:** [Shuffle String](https://leetcode.com/problems/shuffle-string/)

**Code:** [suffle_string.clj](https://github.com/andrewleverette/clojure_programming_problems/blob/main/leetcode/suffle_string.clj)

## Description

You are given a string `s` and an integer array `indices` of the same length. The string `s` will be shuffled such that the character at the `ith` position moves to `indices[i]` in the shuffled string.

Return the shuffled string.

### Examples

**Example 1:**

Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
Output: "leetcode"
Explanation: As shown, "codeleet" becomes "leetcode" after shuffling.

**Example 2:**

Input: s = "abc", indices = [0,1,2]
Output: "abc"
Explanation: After shuffling, each character remains in its position.

### Constraints

- `s.length == indices.length == n`
- `1 <= n <= 100`
- `s` consists of only lowercase English letters.
- `0 <= indices[i] < n`
- All values of indices are unique.

## Solutions

### Sorting

This solution creates a tuple of each character and its corresponding index.
Then it sorts the tuples based on the index, and returns the characters in the sorted order as a string.

```clojure
(defn shuffle-string
  "Given a string s and an integer array indices of the same length,
  return the characters of s in the order specified by indices."
  [s indices]
  (if (seq indices)
    (->> s
         (map vector indices)
         (sort-by first)
         (map second)
         (apply str))
    s))
```

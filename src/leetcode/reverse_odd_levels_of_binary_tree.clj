(ns leetcode.reverse-odd-levels-of-binary-tree
  (:require [clojure.test :refer [deftest is testing]]))

(defn reverse-odd-level-nodes
  [left right level]
  (if (every? nil? [left right])
    [nil nil]
    (let [[u v] (if (odd? level) [(:value right) (:value left)] [(:value left) (:value right)])
          [left-left right-right] (reverse-odd-level-nodes (:left left) (:right right) (inc level))
          [left-right right-left] (reverse-odd-level-nodes (:right left) (:left right) (inc level))]
      [{:value u
        :left left-left
        :right left-right}
       {:value v
        :left right-left
        :right right-right}])))

(defn reverse_odd_levels
  [root]
  (let [[left right] (reverse-odd-level-nodes (:left root) (:right root) 1)]
    (assoc root :left left :right right)))

(defn bfs-traversal
  [root]
  (loop [nodes []
         queue (conj clojure.lang.PersistentQueue/EMPTY root)]
    (if (seq queue)
      (let [node (peek queue)]
        (recur (conj nodes (:value node)) (cond-> (pop queue)
                                            (:left node) (conj (:left node))
                                            (:right node) (conj (:right node)))))
      nodes)))

(defn group-nodes-by-level
  [nodes]
  (loop [levels []
         nodes nodes
         level 0]
    (if (empty? nodes)
      levels
      (let [nodes-at-level (Math/pow 2 level)
            level-nodes (take nodes-at-level nodes)
            remaining-nodes (drop nodes-at-level nodes)]
        (recur (conj levels level-nodes) remaining-nodes (inc level))))))

(defn reverse-odd-levels
  [nodes]
  (let [level-nodes (group-nodes-by-level nodes)]
    (->> level-nodes
         (map-indexed (fn [idx level]
                        (if (odd? idx)
                          (reverse level)
                          level)))
         (apply concat)
         vec)))

(defn vector->binary-tree
  [nodes]
  (letfn [(build-tree [idx]
            (when (< idx (count nodes))
              {:value (nodes idx)
               :left (build-tree (inc (* 2 idx)))
               :right (build-tree (+ 2 (* 2 idx)))}))]
    (build-tree 0)))

(defn reverse-odd-levels-bfs
  [root]
  (let [level-order-nodes (bfs-traversal root)
        reversed-nodes (reverse-odd-levels level-order-nodes)]
    (vector->binary-tree reversed-nodes)))

(deftest test-reverse-odd-levels-of-binary-tree-tests
  (testing "Single node tree should be unchanged"
    (is (= {:value 1
            :left nil
            :right nil} (reverse_odd_levels {:value 1}))))
  (testing "Tree with one level should be reversed"
    (is (= {:value 1
            :left {:value 2}
            :right {:value 3}}
           (reverse_odd_levels {:value 1
                                :left {:value 3}
                                :right {:value 2}})))))

(def tree {:value 1
           :left {:value 2}
           :right {:value 3}})

(reverse-odd-levels-bfs tree)


(ns leetcode.n-ary-tree-level-order-traversal)

(defn queue
  [& xs]
  (if (seq xs)
    (apply conj clojure.lang.PersistentQueue/EMPTY xs)
    clojure.lang.PersistentQueue/EMPTY))

(defn bfs-traversal
  [root visit children]
  (loop [q (queue root)
         nodes []]
    (if (empty? q)
      nodes
      (let [level-values (mapv visit q)
            new-q (->> q (mapcat children) (filter some?) (apply queue))]
        (recur new-q (conj nodes level-values))))))

(defn level-order
  [root]
  (if (nil? root)
    []
    (bfs-traversal root :value :children)))

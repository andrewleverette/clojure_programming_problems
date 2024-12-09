(ns leetcode.task-scheduling)

(defn- queue
  [& xs]
  (if (seq xs)
    (apply conj clojure.lang.PersistentQueue/EMPTY xs)
    clojure.lang.PersistentQueue/EMPTY))

(defn nodes->graph
  "Given a list of nodes and a list of connection pairs, build a directed graph"
  [nodes connections]
  (let [graph (into {} (map vector nodes (repeat [])))]
    (reduce (fn [g [u v]] (update g u conj v)) graph connections)))

(defn find-in-degree
  [graph]
  (let [nodes (keys graph)
        in-degree (into {} (map vector nodes (repeat 0)))]
    (->> nodes
         (mapcat graph)
         (reduce (fn [d node] (update d node inc)) in-degree))))

(defn topological-sort
  "Given a graph, compute a topological ordering"
  [graph]
  (loop [result []
         in-degree (find-in-degree graph)
         q (apply queue (filter #(= 0 (in-degree %)) (keys in-degree)))]
    (if (empty? q)
      (if (= (count graph) (count result))
        result
        nil)
      (let [node (peek q)
            children (graph node)
            new-in-degree (reduce (fn [d child] (update d child dec)) in-degree children)
            new-q (apply conj (pop q) (filter #(= 0 (new-in-degree %)) children))]
        (recur (conj result node)
               new-in-degree
               new-q)))))

(defn task-scheduling
  "Given a list of tasks and a list of requirements, compute a sequence of tasks
  that can be performed such that all tasks can be completed and all requirements as satisfied"
  [tasks requirements]
  (->> requirements
       (nodes->graph tasks)
       topological-sort))

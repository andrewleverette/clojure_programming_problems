(ns leetcode.course-schedule)

(defn- queue
  [& xs]
  (if (seq xs)
    (apply conj clojure.lang.PersistentQueue/EMPTY xs)
    clojure.lang.PersistentQueue/EMPTY))

(defn nodes->graph
  [nodes connections]
  (let [initial (into {} (map vector nodes (repeat [])))]
    (reduce (fn [g [u v]] (update g u conj v)) initial connections)))

(defn in-degree-map
  [graph]
  (let [nodes (keys graph)
        in-degree (into {} (map vector nodes (repeat 0)))]
    (->> nodes
         (mapcat graph)
         (reduce (fn [in node] (update in node inc)) in-degree))))

(defn zero-degree-nodes
  [in-degree nodes]
  (filter #(= 0 (in-degree %)) nodes))

(defn topological-sort
  [graph]
  (loop [result []
         in-degree (in-degree-map graph)
         q (apply queue (zero-degree-nodes in-degree (keys in-degree)))]
    (if (empty? q)
      (if (= (count result) (count graph))
        result
        nil)
      (let [node (peek q)
            children (graph node)
            in-degree' (reduce (fn [d n] (update d n dec)) in-degree children)
            q' (apply conj (pop q) (zero-degree-nodes in-degree' children))]
        (recur (conj result node) in-degree' q')))))

(defn can-finish?
  "Given a number of courses and a list of prerequisite pairs, determine if it is possible to finish all courses"
  [num-courses prerequisites]
  (let [graph (nodes->graph (range num-courses) prerequisites)]
    (not (nil? (topological-sort graph)))))

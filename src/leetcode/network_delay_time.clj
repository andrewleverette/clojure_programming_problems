(ns leetcode.network-delay-time
  (:import [java.util PriorityQueue Comparator]))

(defn weighted-priority-queue
  [& xs]
  (let [q (PriorityQueue.
           (reify Comparator
             (compare [_ [_ uw] [_ vw]]
               (compare uw vw))))]
    (doseq [x xs] (.add q x))
    q))

(defn poll
  [pq]
  (.poll pq))

(defn add
  [pq x]
  (.add pq x))

(defn empty-queue?
  [pq]
  (.isEmpty pq))

(defn nodes->weighted-graph
  [nodes connections]
  (let [graph (into {} (map vector nodes (repeat [])))]
    (reduce (fn [g [u v w]] (update g u conj [v w])) graph connections)))

(defn dijkstra
  [graph start]
  (loop [q (weighted-priority-queue [start 0])
         distances (assoc (->> (repeat Integer/MAX_VALUE)
                               (map vector (keys graph))
                               (into {})) start 0)]
    (if (empty-queue? q)
      (if (= (count graph) (->> distances vals (filter #(not= Integer/MAX_VALUE %)) count))
        distances
        nil)
      (let [[u uw] (poll q)
            u-distance (distances u)]
        (if (> uw u-distance)
          (recur q distances)
          (let [edges (graph u)
                new-distances (reduce (fn [distances' [v v-cost]]
                                        (let [u-distance (distances' u)
                                              v-distance (distances' v)]
                                          (if (> v-distance (+ u-distance v-cost))
                                            (do
                                              (add q [v (+ u-distance v-cost)])
                                              (assoc distances' v (+ u-distance v-cost)))
                                            distances'))) distances edges)]
            (recur q new-distances)))))))

(defn network-delay-time
  "Given a list of network connections, find the network delay time"
  [times n k]
  (let [graph (nodes->weighted-graph (range 1 (inc n)) times)
        distances (dijkstra graph k)]
    (if distances
      (->> distances vals (apply max))
      -1)))

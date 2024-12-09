(ns leetcode.minimum-fuel-costs)

(defn dfs
  [graph seats u p]
  (->> (graph u [])
       (filter #(not= % p))
       (reduce (fn [[fuel people] n]
                 (let [[fuel' people'] (dfs graph seats n u)]
                   [(+ fuel fuel' (Math/ceil (/ people' seats)))
                    (+ people people')])) [0 1])))

(defn bidirectional-graph
  [connections]
  (reduce (fn [g [u v]]
            (-> g
                (update u (fnil conj []) v)
                (update v (fnil conj []) u))) {} connections))

(defn minimum-fuel-cost
  [roads seats]
  (-> roads
      bidirectional-graph
      (dfs seats 0 nil)))

(def roads [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]])
(def seats 2)

(minimum-fuel-cost roads seats)


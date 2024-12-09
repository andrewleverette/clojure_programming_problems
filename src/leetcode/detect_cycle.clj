(ns leetcode.detect-cycle)

(defn find-roots
  [roots x]
  (let [parent (roots x)]
    (if (not= parent x)
      (recur roots parent)
      x)))

(defn union
  [roots x y]
  (->> [x y]
       (map #(find-roots roots %))
       (apply assoc roots)))

(defn coordinates
  [grid]
  (let [n (count grid)
        m (count (first grid))]
    (for [i (range n)
          j (range m)]
      [i j])))

(defn neighbors
  [grid point]
  (let [deltas [[0 1] [1 0]]
        target (get-in grid point)]
    (->> deltas
         (map #(map + point %))
         (filter #(= target (get-in grid %))))))

(defn detect-cycle
  [grid roots point]
  (->> point
       (neighbors grid)
       (reduce (fn [[_ rs] n]
                 (let [[p-root n-root] (map #(find-roots rs %) [point n])]
                   (if (= p-root n-root)
                     (reduced [true rs])
                     [false (union rs p-root n-root)]))) 
               [false roots])))


(defn contains-cycle?
  [grid]
  (loop [[p & ps :as coords] (coordinates grid)
         roots (zipmap coords coords)]
    (if (nil? p)
      false
      (let [[cycle? compressed-roots] (detect-cycle grid roots p)]
        (if cycle?
          true
          (recur ps compressed-roots))))))

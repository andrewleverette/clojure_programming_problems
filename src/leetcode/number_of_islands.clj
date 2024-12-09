(ns leetcode.number-of-islands)

(defn find-root
  [m x]
  (let [root (m x)]
    (if (not= root x)
      (let [[new-parent roots] (find-root m root)]
        (recur root (assoc roots x new-parent)))
      [x m])))
  ; (let [root (m x)]
  ;   (if (not= root x)
  ;     (recur m root)
  ;     x)))

(defn union
  [m u v]
  (->> [u v]
       (map #(find-root m %))
       (apply assoc m)))

(defn- coordinates
  [pred grid]
  (into #{} (for [i (range (count grid))
                  j (range (count (first grid)))
                  :when (pred [i j])]
              [i j])))

(defn- neighbors
  [ones [i j]]
  (let [deltas [[-1 0] [0 -1]]]
    (->> deltas
         (map #(map + [i j] %))
         (filter ones))))

(defn detect-island
  [ones roots coord]
  (->> coord
       (neighbors ones)
       (reduce (fn [roots' n]
                 (let [[root rm] (find-root roots' coord)
                       [n-root compressed-roots] (find-root rm n)]
                   (if (not= root n-root)
                     (union compressed-roots root n-root)
                     compressed-roots))) roots)))

; (let [[root rm] [n-root rn]] (map #(find-root roots' %) [coord n])
                 ;   (if (not= root n-root)
                 ;     (-> roots'
                 ;         ; (assoc n (find-root roots' n-root))
                 ;         (union root n-root))
                 ;     roots'))) roots)))

(defn num-islands
  [grid]
  (let [ones (coordinates #(= (get-in grid %) "1") grid)]
    (loop [[p & ps] ones
           roots (zipmap ones ones)]
      (if (nil? p)
        roots
        (let [roots' (detect-island ones roots p)]
          (recur ps roots'))))))

(def grid [["1","1","1","1","0"],
           ["1","1","0","1","0"],
           ["1","1","0","0","0"],
           ["0","0","0","0","0"]])

(def ones (coordinates #(= (get-in grid %) "1") grid))
ones
(def roots (zipmap ones ones))
roots

(neighbors ones [1 1])

(num-islands grid)

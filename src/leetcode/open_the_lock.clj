(ns leetcode.open-the-lock)

(defn- queue
  [& xs]
  (if (seq xs)
    (apply conj clojure.lang.PersistentQueue/EMPTY xs)
    clojure.lang.PersistentQueue/EMPTY))

(def ^:private slot-changes
  {\0 [\1 \9]
   \1 [\2 \0]
   \2 [\3 \1]
   \3 [\4 \2]
   \4 [\5 \3]
   \5 [\6 \4]
   \6 [\7 \5]
   \7 [\8 \6]
   \8 [\9 \7]
   \9 [\0 \8]})

(defn- next-states-for-wheel
  [wheels pos]
  (->> (wheels pos)
       slot-changes
       (map #(apply str (assoc wheels pos %)))))

(defn- next-states
  [state]
  (let [wheels (mapv identity state)]
    (->> (count state)
         range
         (mapcat #(next-states-for-wheel wheels %)))))

(defn open-lock
  [deadends target]
  (let [initial-state "0000"
        deadend-set (set deadends)]
    (if (deadend-set initial-state)
      -1
      (loop [q (queue [initial-state 0])
             visited (conj deadend-set initial-state)]
        (if (empty? q)
          -1
          (let [[state turns] (peek q)]
            (if (= state target)
              turns
              (let [next-states (->> state next-states (filter #(not (visited %))))]
                (recur (apply conj (pop q) (map vector next-states (repeat (inc turns)))) (into visited next-states))))))))))

(def deadends ["8887","8889","8878","8898","8788","8988","7888","9888"])
(def target "8888")
(open-lock deadends target)

(ns clojuremagick.command)

(defn vec->shell [command-vec]
  "Converts vector to conch's shell format"
  (vec (apply concat
          (map (fn [command]
                 (let [k (get command 0)
                       v (get command 1)
                       option (str "-" (name k))
                       value v]
                   (remove nil? [option value])))
               command-vec))))

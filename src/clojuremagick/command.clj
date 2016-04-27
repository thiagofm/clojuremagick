(ns clojuremagick.command)

(defn vec->in [command-vec]
  "Converts vector to conch's in format"
  (vec (map (fn [[k v]]
         (let [option (str "-" (name k))
               value v]
           [option value])) command-vec)))

(ns clojuremagick.command)

(defn vec->in [command-vec]
  "Converts vector to conch's in format"
  (vec (map (fn [[k v]]
         (let [option (str "-" (name k)) ; TODO: find out whether this option exist
               value v]
           [option value])) command-vec)))

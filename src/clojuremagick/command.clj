(ns clojuremagick.command)

(defn vec->shell [command-vec]
  "Converts vector to conch's shell format"
  (vec (apply concat
          (map (fn [[k v]]
                 (let [option (str "-" (name k)) ; TODO: find out whether this option exist
                       value v]
                   [option value])) command-vec))))

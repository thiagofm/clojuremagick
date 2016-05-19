(ns clojuremagick.parser)

(defn mogrify-error
  [error-map]
  (let [err (-> error-map :proc :err)]
    err))

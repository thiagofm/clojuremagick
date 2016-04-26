(ns clojuremagick.core
  (:require [me.raynes.conch :refer [programs with-programs let-programs] :as sh]))

(defn foo
  "Working example"
  [x]
  (programs mogrify)
  (mogrify "rose.jpg" {:in ["-resize" "50%"]}))

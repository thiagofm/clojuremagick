(ns clojuremagick.core
  (:require [me.raynes.conch :refer [programs with-programs let-programs] :as sh]
            [clojuremagick.command :as command]))

(defn with-file
  "Working example"
  [file-arg command-vec]
  (programs mogrify pwd)
  (print (pwd)) ;
  (let [file file-arg
        in (command/vec->in command-vec)]
    (mogrify file {:in in})))

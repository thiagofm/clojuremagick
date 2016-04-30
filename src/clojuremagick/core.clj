(ns clojuremagick.core
  (:require [me.raynes.conch :refer [programs with-programs let-programs] :as sh]
            [clojuremagick.command :as command]))

(defn with-file
  "Given a file path and a vector command, runs the command in the file and returns a file with the new version"
  [file-arg command-vec]
  (programs mogrify)
  (let [file file-arg
        ; Converting vectors to the expected format
        shell (command/vec->shell command-vec)
        shell-args (conj shell file)]

    ; Run shell command
    (apply mogrify shell-args)

    ; Return file
    (clojure.java.io/file file)))

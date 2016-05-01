(ns clojuremagick.core
  (:require [me.raynes.conch :refer [programs with-programs let-programs] :as sh]
            [clojuremagick.command :as command]))

(defn with-file
  "Given a file path and a vector command, runs the command in the file and returns a file with the new version"
  [file-arg command-vec]
  (programs mogrify)
  (let [file-path (.toString file-arg)
        ; Converting vectors to the expected format
        shell (command/vec->shell-vec command-vec)
        shell-args (conj shell file-path)]

    ; Run shell command
    (apply mogrify shell-args)

    ; Return file
    (clojure.java.io/file file-path)))

(defn with-tempfile
  [file-arg file-prefix command-vec]
  (let [; TODO: extract most of those defs to functions inside file.clj
        file-path (.toString file-arg)
        file-base-name (org.apache.commons.io.FilenameUtils/getBaseName file-path)
        file-extension (org.apache.commons.io.FilenameUtils/getExtension file-path)
        new-filename (str (name file-prefix) "_" file-base-name)
        temp-file (java.io.File/createTempFile new-filename (str "." file-extension))
        temp-file-path (.toString temp-file)]
    ; Use a tempfile instead of the real file
    (clojure.java.io/copy (clojure.java.io/file file-path) temp-file)

    (with-file temp-file-path command-vec)))

(def with-copy
  [file-arg file-prefix command-vec]
  (let [ (with-tempfile file-arg file-prefix command-vec)]
    ))

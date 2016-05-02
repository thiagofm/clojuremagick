(ns clojuremagick.core
  (:require [me.raynes.conch :refer [programs with-programs let-programs] :as sh]
            [clojuremagick.command :as command]))

(defn with-file
  "Given a file path and a vector command, runs the command in the file and returns a file with the new version"
  [file-arg command-vec]
  (programs mogrify)
  (let [file-fullpath (.toString file-arg)
        ; Converting vectors to the expected format
        shell (command/vec->shell-vec command-vec)
        shell-args (conj shell file-fullpath)]

    ; Run shell command
    (apply mogrify shell-args)

    ; Return file
    (clojure.java.io/file file-fullpath)))

(defn with-tempfile
  [file-arg file-prefix command-vec]
  (let [file-fullpath (.toString file-arg)
        file-base-name (org.apache.commons.io.FilenameUtils/getBaseName file-fullpath)
        file-extension (org.apache.commons.io.FilenameUtils/getExtension file-fullpath)
        new-filename (str (name file-prefix) "_" file-base-name)
        temp-file (java.io.File/createTempFile new-filename (str "." file-extension))
        temp-file-path (.toString temp-file)]

    ; Use a tempfile instead of the real file
    (clojure.java.io/copy (clojure.java.io/file file-fullpath) temp-file)

    (with-file temp-file-path command-vec)))

; TODO: remove this and all the file handling here, perhaps use Raynes/fs
(defn- copy-file [source-path dest-path]
  (clojure.java.io/copy (clojure.java.io/file source-path) (clojure.java.io/file dest-path)))

(defn with-copy
  [file-arg file-prefix command-vec]
  (let [temp-file (with-tempfile file-arg file-prefix command-vec)
        file-fullpath (.toString file-arg)
        file-base-name (org.apache.commons.io.FilenameUtils/getBaseName file-fullpath)
        file-extension (org.apache.commons.io.FilenameUtils/getExtension file-fullpath)
        file-path (org.apache.commons.io.FilenameUtils/getPath file-fullpath)
        new-filename (str (name file-prefix) "_" file-base-name)
        new-extension (str "." file-extension)
        new-full-filename (str new-filename new-extension)
        new-file-fullpath (str file-path new-full-filename)]
    ; Copies processed temp file to the same directory of the file specified in the arguments
    (copy-file (.toString temp-file) new-file-fullpath)

    ; Return file
    (clojure.java.io/file new-file-fullpath)))

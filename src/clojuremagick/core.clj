(ns clojuremagick.core
  (:require [me.raynes.conch :refer [programs with-programs let-programs] :as sh]
            [clojuremagick.command :as command]
            [clojuremagick.parser :as parser])
  (:import (org.apache.commons.io FilenameUtils)
           (java.io File)))

(defn- copy-file [source-path dest-path]
  (clojure.java.io/copy (clojure.java.io/file source-path) (clojure.java.io/file dest-path)))

(defn with-file
  "Given a file path and a vector command, runs the command in the file and returns a file with the new version"
  [file-arg command-vec]
  (programs mogrify)
  (let [file-fullpath (.toString file-arg)
        ; Converting vectors to the expected format
        shell (command/vec->shell-vec command-vec)
        shell-args (conj shell file-fullpath)]

    ; Run shell command
    (try (do (apply mogrify shell-args)
             (clojure.java.io/file file-fullpath))
         (catch Exception e
           (let [error-map (ex-data e)]
             (parser/mogrify-error error-map))))))

(defn with-tempfile
  [file-arg options]
  (let [{:keys [version operators]} options
        file-fullpath (.toString file-arg)
        temp-file-name (FilenameUtils/getName file-fullpath)
        temp-file (File/createTempFile temp-file-name nil)
        temp-file-path (.toString temp-file)]

    ; Use a tempfile instead of the real file
    (copy-file file-fullpath temp-file-path)

    (with-file temp-file-path operators)))

(defn with-copy
  [file-arg options]
  (let [{:keys [version operators]} options
        temp-file (with-tempfile file-arg options)
        file-fullpath (.toString file-arg)
        file-base-name (FilenameUtils/getBaseName file-fullpath)
        file-extension (FilenameUtils/getExtension file-fullpath)
        file-path (FilenameUtils/getPath file-fullpath)
        new-filename (str (name version) "_" file-base-name)
        new-extension (str "." file-extension)
        new-full-filename (str new-filename new-extension)
        new-file-fullpath (str file-path new-full-filename)]

    ; Copies processed temp file to the same directory of the file specified in the arguments
    (copy-file (.toString temp-file) new-file-fullpath)

    ; Return file
    (clojure.java.io/file new-file-fullpath)))

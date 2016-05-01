(ns clojuremagick.support.checkers)

(defn filesize-matches? [expected-file]
  (fn filesize-matcher [actual-file]
    (= (.length expected-file) (.length actual-file))))

(defn- regexp-match?
  [matcher match]
  (not (nil? (re-find matcher match))))

(defn file-base-name-matches? [expected-file-name]
  (fn filesize-matcher [actual-file]
    (let [file-fullpath (.toString actual-file)
          file-base-name (org.apache.commons.io.FilenameUtils/getBaseName file-fullpath)]
      (regexp-match? (re-pattern expected-file-name) file-base-name))))


(defn file-path-matches? [expected-file-fullpath]
  (fn filesize-matcher [actual-file]
    (let [actual-file-fullpath (.toString actual-file)
          actual-file-path (org.apache.commons.io.FilenameUtils/getPath actual-file-fullpath)
          expected-file-path (org.apache.commons.io.FilenameUtils/getPath expected-file-fullpath)]
      (= expected-file-path actual-file-path))))

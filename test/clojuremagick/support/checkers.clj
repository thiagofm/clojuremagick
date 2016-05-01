(ns clojuremagick.support.checkers)

(defn filesize-matches? [expected-file]
  (fn filesize-matcher [actual-file]
    (= (.length expected-file) (.length actual-file))))

(defn- regexp-match?
  [matcher match]
  (not (nil? (re-find matcher match))))

(defn file-base-name-matches? [expected-file-name]
  (fn filesize-matcher [actual-file]
    (let [file-path (.toString actual-file)
          file-base-name (org.apache.commons.io.FilenameUtils/getBaseName file-path)]
      (regexp-match? (re-pattern expected-file-name) file-base-name))))

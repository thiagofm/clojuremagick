(ns clojuremagick.support.checkers)

(defn filesize-matches? [expected-file]
  (fn filesize-matcher [actual-file]
    (= (.length expected-file) (.length actual-file))))

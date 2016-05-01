(ns clojuremagick.core-test
  (:require [clojuremagick.core :as cm]
            [midje.sweet :as midje])
  (:use clojuremagick.support.checkers)
  (:use midje.sweet))

(defn resources-path [file]
  (str "test/resources/" file))

; TODO: refactor this and the other case
(with-state-changes [(before :facts (do (def rose-temp-file (java.io.File/createTempFile "temp_rose" ".jpg")) ; todo: improve this!
                                        (clojure.java.io/copy (clojure.java.io/file (resources-path "rose_full.jpg")) rose-temp-file)))]
  (facts "about with-file"
         (cm/with-file (.toString rose-temp-file) [[:resize "100x100"]]) => (filesize-matches? (clojure.java.io/file (resources-path "rose_thumb.jpg")))))

(with-state-changes [(before :facts (do (def rose-temp-file (java.io.File/createTempFile "temp_rose" ".jpg"))
                                        (clojure.java.io/copy (clojure.java.io/file (resources-path "rose_full.jpg")) rose-temp-file)))]
  (facts "about with-file"
         (cm/with-file rose-temp-file [[:resize "100x100"]]) => (filesize-matches? (clojure.java.io/file (resources-path "rose_thumb.jpg")))))




;
(facts "about with-tempfile"
       (cm/with-tempfile (resources-path "rose_full.jpg") :thumb [[:resize "100x100"]]) => (filesize-matches? (clojure.java.io/file (resources-path "rose_thumb.jpg")))
       (cm/with-tempfile (resources-path "rose_full.jpg") :thumb [[:resize "100x100"]]) => (file-base-name-matches? "thumb_rose_full"))

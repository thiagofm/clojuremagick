(ns clojuremagick.core-test
  (:require [clojuremagick.core :as cm]
            [midje.sweet :as midje])
  (:use clojuremagick.support.checkers)
  (:use midje.sweet))

(defn resources-path [file]
  (str "test/resources/" file))

; TODO: refactor this and the other case
(with-state-changes [(before :facts (do (def rose-temp-file (java.io.File/createTempFile "temp_rose" ".jpg")) ; TODO: improve this!
                                        (clojure.java.io/copy (clojure.java.io/file (resources-path "rose_full.jpg")) rose-temp-file)))]
  (facts "about with-file"
         (cm/with-file (.toString rose-temp-file) [[:resize "100x100"]]) => (filesize-matches? (clojure.java.io/file (resources-path "rose_thumb.jpg")))))

(with-state-changes [(before :facts (do (def rose-temp-file (java.io.File/createTempFile "temp_rose" ".jpg"))
                                        (clojure.java.io/copy (clojure.java.io/file (resources-path "rose_full.jpg")) rose-temp-file)))]
  (facts "about with-file"
         (cm/with-file rose-temp-file [[:resize "100x100"]]) => (filesize-matches? (clojure.java.io/file (resources-path "rose_thumb.jpg")))))




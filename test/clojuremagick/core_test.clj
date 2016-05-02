(ns clojuremagick.core-test
  (:require [clojuremagick.core :as cm]
            [midje.sweet :as midje])
  (:use clojuremagick.support.checkers)
  (:use midje.sweet))

; TODO: move this to utils?
(defn resources-path [file]
  (str "test/resources/" file))

; TODO: find a better place to leave those definitions
(def rose-path (resources-path "rose.jpg"))
(def thumb-resize-vec [[:resize "100x100"]])

; TODO: refactor this and the other case
(with-state-changes [(before :facts (do (def rose-temp-file (java.io.File/createTempFile "temp_rose" ".jpg")) ; todo: improve this!
                                        (clojure.java.io/copy (clojure.java.io/file rose-path) rose-temp-file)))]
  (facts "about with-file"
         (cm/with-file (.toString rose-temp-file) thumb-resize-vec) => (filesize-matches? (clojure.java.io/file (resources-path "thumb_rose.jpg")))))

(with-state-changes [(before :facts (do (def rose-temp-file (java.io.File/createTempFile "temp_rose" ".jpg"))
                                        (clojure.java.io/copy (clojure.java.io/file rose-path) rose-temp-file)))]
  (facts "about with-file"
         (cm/with-file rose-temp-file thumb-resize-vec) => (filesize-matches? (clojure.java.io/file (resources-path "thumb_rose.jpg")))))

(facts "about with-tempfile"
       (cm/with-tempfile rose-path :thumb thumb-resize-vec) => (filesize-matches? (clojure.java.io/file (resources-path "thumb_rose.jpg")))
       (cm/with-tempfile rose-path :thumb thumb-resize-vec) => (file-base-name-matches? "thumb_rose"))

(facts "about with-copy"
       (cm/with-copy rose-path :thumb thumb-resize-vec) => (file-path-matches? rose-path))

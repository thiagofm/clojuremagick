(ns clojuremagick.core-test
  (:require [clojuremagick.core :as cm]
            [midje.sweet :as midje])
  (:import (java.io File))
  (:use clojuremagick.support.checkers)
  (:use clojuremagick.support.utils)
  (:use midje.sweet))

(def rose-path (resources-path "rose.jpg"))
(def thumb-resize-vec [[:resize "100x100"]])
(def thumb-rose-file (clojure.java.io/file (resources-path "thumb_rose.jpg")))
(def rose-temp-file (File/createTempFile "temp_rose" ".jpg"))

(with-state-changes
  [(before :facts
           (clojure.java.io/copy (clojure.java.io/file rose-path) rose-temp-file))]
  (facts "about with-file"
         (cm/with-file (.toString rose-temp-file) thumb-resize-vec) => (filesize-matches? thumb-rose-file)

         (cm/with-file rose-temp-file thumb-resize-vec) => (file-base-name-matches? "temp_rose")))

(facts "about with-tempfile"
       (cm/with-tempfile rose-path
         {:version :thumb :operators thumb-resize-vec}) => (filesize-matches? thumb-rose-file)

       (cm/with-tempfile rose-path
         {:version :thumb :operators thumb-resize-vec}) => (file-base-name-matches? "rose.jpg"))

(facts "about with-copy"
       (cm/with-copy rose-path
         {:version :thumb :operators thumb-resize-vec})  => (file-base-name-matches? "thumb_rose")

       (cm/with-copy rose-path
         {:version :thumb :operators [[:scale]]}) => (throws clojure.lang.ExceptionInfo)

       (cm/with-copy rose-path
         {:version :thumb :operators thumb-resize-vec}) => (file-path-matches? rose-path)

       (cm/with-copy rose-path
         {:version :thumb :operators thumb-resize-vec}) => (filesize-matches? thumb-rose-file))

(facts "with varied options"
       (cm/with-copy rose-path
         {:version :thumb-rotated-oriented-scaled
          :operators [[:resize "100x100"]
                      [:rotate "-37"]
                      [:auto-orient]
                      [:scale "10"]]}) => (file-path-matches? rose-path))

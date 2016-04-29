(ns clojuremagick.core-test
  (:require [clojuremagick.core :as cm]
            [midje.sweet :as midje])
  (:use midje.sweet))



(with-state-changes [(before :facts
                             (do
                               (def rose-temp-file (java.io.File/createTempFile "temp_rose" ".jpg"))
                               (clojure.java.io/copy (clojure.java.io/file "test/resources/rose_full.jpg") rose-temp-file)))]
  (facts "about with-file"
    (.length (cm/with-file (.toString rose-temp-file) [[:resize "100x100"]])) => (.length (clojure.java.io/file "test/resources/rose_thumb.jpg"))))
;;

;

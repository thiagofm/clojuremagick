(ns clojuremagick.core-test
  (:require [clojuremagick.core :as cm]
            [midje.sweet :as midje])
  (:use midje.sweet))

(facts "about with-file"
  (cm/with-file "rose_full.jpg" [[:resize "50x50"]]) => (clojure.java.io/file "rose_thumb.jpg"))

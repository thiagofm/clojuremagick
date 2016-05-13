(ns clojuremagick.command-test
  (:require [clojuremagick.command :as command]
            [midje.sweet :as midje])
  (:use midje.sweet))

(facts "about vec->shell-vec"
       (command/vec->shell-vec [[:resize "50%"]]) => ["-resize" "50%"]
       (command/vec->shell-vec [[:flip]]) => ["-flip"]
       (command/vec->shell-vec [[:resize "100x100"]
                                [:rotate "-37"]
                                [:auto-orient]
                                [:scale "10"]]) => ["-resize" "100x100" "-rotate" "-37" "-auto-orient" "-scale" "10"]
       (command/vec->shell-vec [[:wat?]]) => (throws clojure.lang.ExceptionInfo
                                                     #(= :wat? (-> % ex-data :command))))

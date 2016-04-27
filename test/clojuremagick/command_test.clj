(ns clojuremagick.command-test
  (:require [clojuremagick.command :as command]
            [midje.sweet :as midje])
  (:use midje.sweet))

(facts "about vec->in" (command/vec->in [[:resize "50%"]]) => [["-resize" "50%"]])

(ns clojuremagick.parser-test
  (:require [clojuremagick.parser :as parser]
            [midje.sweet :as midje])
  (:use midje.sweet))

(def missing-argument-mogrify-error
  {:proc {:err "mogrify: invalid argument for option `/var/folders/22/2hspcg_n5xv7lb8lv2jrnyfm0000gn/T/rose.jpg1251614767804617693.tmp': -scale @ error/mogrify.c/MogrifyImageCommand/5881.\n"},
   :stderr "mogrify: invalid argument for option `/var/folders/22/2hspcg_n5xv7lb8lv2jrnyfm0000gn/T/rose.jpg1251614767804617693.tmp': -scale @ error/mogrify.c/MogrifyImageCommand/5881.\n"})

(facts "about mogrify-error"
       (mogrify-error missing-argument-mogrify-error) => missing-argument-mogrify-error)

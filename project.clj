(defproject clojuremagick "0.1.0-SNAPSHOT"
  :description "Image conversion made simple"
  :url "http://github.com/thiagofm/clojuremagick"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [me.raynes/conch "0.8.0"]]
  :plugins [[lein-midje "3.1.3"]]
  :profiles {:default [:dev]
             :dev {:dependencies [[midje "1.6.3"]
                                  [lein-midje "3.1.3"]]}})

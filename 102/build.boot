(set-env!
  :dependencies '[[org.clojure/clojure "1.8.0"]
                  [org.clojure/tools.namespace "0.3.0-alpha3" :scope "test"]

                  ; Amazon Lambda support
                  [uswitch/lambada "0.1.2"]

                  ; Logging
                  [com.taoensso/timbre "4.7.3"]

                  ; JSON
                  [cheshire "5.6.3"]

                  ; Environment variables
                  [environ "1.0.3"]
                  [boot-environ "1.0.3" :scope "test"]]

  :source-paths #{"src"})

(require '[clojure.string :refer [trim]]
         '[environ.boot :refer [environ]])

;; Helpers

(defn version
  "Get the version of this utility"
  []
  (trim (slurp "./VERSION")))

;; Task Settings

(task-options!
  aot {:all true}
  pom {:project 'lesson-two
       :version (version)}
  jar {:file (str "lesson-one-" (version) ".jar")
       :main 'lesson-two.core})

;; Tasks

(deftask with-dev
  "Adds the development directory 'dev' to :source-paths"
  []
  (set-env! :source-paths #(conj % "dev"))
  identity)

(deftask local
  "Sets up environment variables needed to run the app with developer settings"
  []
  (comp (with-dev)
        (environ :env {:development "true"})))

(deftask build
  "Builds the deployable artifact"
  []
  (comp (aot)
        (pom)
        (uber)
        (jar)
        (target)))

(ns lesson-one.core
  (:require
    [uswitch.lambada.core :refer [deflambdafn]]
    [cheshire.core :as json]
    [taoensso.timbre :as log]
    [clojure.java.io :as io]))

; The name of the lambda must include the full namespace in it's name.
(deflambdafn lesson-one.core.lambdafn
  [in out context]
  (let [body (-> in keyword)])
  )

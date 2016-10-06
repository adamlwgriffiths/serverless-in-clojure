(ns lesson-one.core
  (:require
    [uswitch.lambada.core :refer [deflambdafn]]
    [cheshire.core :as json]
    [taoensso.timbre :as log]
    [clojure.java.io :as io]))

(defn sieve-of-eratosthenes
  "Find all the prime numbers between 2 and <number>"
  [number]
   (if (< number 2)
     (str number " is too low")
     (loop [primes (list)
            collection (range 2 (inc number))]
       (if-not (seq collection)
         (vec (apply sorted-set primes))
         (recur (conj primes (apply min collection))
                (remove #(zero? (mod % (apply min collection))) collection))))))

; The name of the lambda must include the full namespace in it's name.
(deflambdafn lesson-one.core.lambdafn
  [in out context]
  (log/info "Starting Lambda")
  (let [body (-> in io/reader (json/parse-stream keyword))
        result (sieve-of-eratosthenes (-> body :max num))]
    (with-open [w (io/writer out)]
      (json/generate-stream result w)
      (log/info "Lambda finished"))))

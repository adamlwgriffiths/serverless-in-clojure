(ns lesson-two.core
  (:require
    [lesson-two.dynamo :as db]
    [uswitch.lambada.core :refer [deflambdafn]]
    [cheshire.core :as json]
    [taoensso.timbre :as log]
    [clojure.java.io :as io]))

(defn sieve-of-eratosthenes
  "Find all the prime numbers between 2 and <number>"
  [number]
  (if (< number 2)
    (str number " is too low")
    (let [prime-list (filter #(<= % number) (db/list-primes))
          largest-prime (if (zero? (count prime-list)) 2 (apply max prime-list))
          pre-filter (range largest-prime (inc number))]
      (loop [primes prime-list
             collection (filter #(not-any? (fn [p] (zero? (mod % p))) primes) pre-filter)]
        (if-not (seq collection)
          (vec (apply sorted-set primes))
          (let [prime (apply min collection)]
            (db/put-prime (count primes) prime)
            (recur (conj primes prime)
                   (remove #(zero? (mod % prime)) collection))))))))

; The name of the lambda must include the full namespace in it's name.
(deflambdafn lesson-two.core.lambdafn
  [in out context]
  (log/info "Starting Lambda")
  (let [body (-> in io/reader (json/parse-stream keyword))
        result (sieve-of-eratosthenes (-> body :max num))]
    (with-open [w (io/writer out)]
      (json/generate-stream result w)
      (log/info "Lambda finished"))))

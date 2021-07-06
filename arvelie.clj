(ns teo_utils)
(use '[clojure.string :only (split)])

(defn monthdays [month]
  (cond 
    (= month 2) 28
    (= month 4) 30
    (= month 6) 30
    (= month 9) 30
    (= month 11) 30
    (< month 13) 31
  )
 )

 (defn countdays [total month]
  (if (= month 0) 
    total
    (countdays (+ total (monthdays month)) (- month 1)))
 )

(defn dayofyear [day month]
  (- (+ day (countdays 0 (- month 1))) 1)
)
(defn num-to-letter [num]
  (char (+ 65 num))
)

(defn arvelie-day-str [day]
  (let [ones (rem day 10)]
  (let [tens (int (/ day 10))]
  (str (char (+ 48 tens)) (char (+ 48 ones)))))
)

(defn arvelie-year-str [start-year now-year]
  (let [span (- now-year start-year)] 
    (println span)
    (if (and (>= span 0) (< span 100))
      (arvelie-day-str span))) 
)

(defn gregorian-to-arvelie [day month]
  (let [doy (dayofyear day month)]
  (str (num-to-letter (int(/ doy 14))) (arvelie-day-str (rem doy 14))))
)

;; depends on a javascript function. Yuck!
(defn day-month-year []
  (split (.myGoddamnDate js/window) #"/")
)


;; args are: roam-date start-of-journal-year
;; The roam date format is [:block/uid mm-dd-yyyy]
(defn main [{:keys [block-uid]} & args]
  (let [today (day-month-year)]
    (let [arg-date (split (nth (nth args 0) 1) #"-")]
        [:b 
         (arvelie-year-str (nth args 1) (nth today 2))
	;; since roam still does us style date we pass into func in reverse order
         (gregorian-to-arvelie (int (nth arg-date 1)) (int (nth arg-date 0)))]
)))

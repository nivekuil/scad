(ns scad.core
  (:use [scad-clj.scad])
  (:use [scad-clj.model]))

;; Primitives
(def block
  (cube 5 5 5))

(def stick-x
  (cube 5 5 75))

(def stick-y
  (rotate [0 (/ Math/PI 2) 0] stick-x))

(def ball
  (sphere 15))

(def tie-fighter
  (union (translate [0 37.5 0] stick-x)
         (translate [0 -37.5 0] stick-x)
         (translate [0 -37.5 0] stick-y)
         ball))

(color [0 1 0 1] (hull (union stick-y stick-x)))

(hull (hull stick-x (translate [0 75 0] stick-x))
      (hull stick-y (translate [0 75 75] stick-x)))

(def plus-sign
  (union stick-x (rotate [0 (/ Math/PI 2) 0] stick-x)))

(def gear-x
  (map #(rotate [0 0 (* (/ Math/PI 8) %1)] %2)
       (range)
       (repeat 8 plus-sign)))

(def gearcase
  (map
   #(translate [(* 37.5 %1) (* 6 %1) (* 32.5 %1)]
               (color [(mod %1 0.8) (mod %1 0.7) (mod %1 0.9)] %2))
   (range)
   (repeat 16 gear-x)))

(map
 #(rotate [0 0 (* (/ Math/PI 16) %1)]
          %2)
 (range)
 (repeat 32 gearcase))



;; (def intersects
;;   (intersection
;;    (cube 50 50 50)
;;    (sphere 35)))

;; (defn spit-scad []
;;   (spit "post-demo.scad"
;;         (write-scad intersects)))

;; (defun spit-scad-last-expression ()
;;    (interactive)
;;    (cider-interactive-eval
;;     "(spit \"repl.scad\" (scad-clj.scad/write-scad %s))"
;; (cider-last-sexp))

;; (local-set-key (kbd "C-c c") #'update-file)

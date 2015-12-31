(ns scad.core
  (:use [scad-clj.scad])
  (:use [scad-clj.model]))

;; Primitives
(def block
  (cube 50 50 50))

(def stick-x
  (cube 50 50 750))

(def ball
  (sphere 150))

(def stick-y
  (union
   (map #(translate [0 (* 50 %1) 0] %2)
        (range)
        (repeat 16 block))))

(def tie-fighter
  (union (translate [0 375 0] stick-x)
         (translate [0 -375 0] stick-x)
         (translate [0 -375 0] stick-y)
         ball))

(color [0 1 0 1] (hull (union stick-y stick-x)))

(hull (hull stick-x (translate [0 750 0] stick-x))
      (hull stick-y (translate [0 750 750] stick-x)))

(def plus-sign
  (union stick-x (rotate [0 (/ Math/PI 2) 0] stick-x)))

(def gear-x
  (map #(rotate [0 0 (* (/ Math/PI 8) %1)] %2)
       (range)
       (repeat 8 plus-sign)))

(map #(translate [(* 375 %1) (* 65 %1) (* 325 %1)] %2) (range) (repeat 10 gear-x))

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

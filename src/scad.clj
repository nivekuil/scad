(ns scad.core
  (:use [scad-clj.scad])
  (:use [scad-clj.model]))

(def block
  (cube 50 50 50))

(def stick-x
  (cube 500 50 750))

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

(hull (hull stick-x (translate [0 750 0] stick-x))
      (hull stick-y (translate [0 750 750] stick-x)))

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

;;;;
;;;; corrige.stk         --
;;;;
;;;;           Author: Erick Gallesio [eg@unice.fr]
;;;; Last file update: 29-Mar-2022 17:27 (eg)
;;;;

;;; ======================================================================
;;;
;;; QUESTION 1
;;;
;;; ======================================================================

(define deps '(("prog" ("m1.o" "m2.o")
                       "gcc -o prog m1.o m2.o")
               ("m1.o" ("m1.c" "common.h" "m1.h")
                       "gcc -c m1.c")
               ("m2.o" ("m2.c" "common.h" "m2.h")
                       "gcc -c m2.c")
               ("all.tgz"  ("prog")
                       "tar cvfz all.tgz prog m1.* m2.* common.h")))

(define target       car)
(define dependancies cadr)
(define command      caddr)


;;;
;;; Question 1.1
;;;

;;; Cette fonction est récursive terminale et n'utilise pas append.
;;; res doit être inversé avant d'être renvoyé.
(define (unique lst)
  (define (aux lst res)
    (cond
     ((null? lst)             (reverse res))
     ((member (car lst) res)  (aux (cdr lst) res))
     (else                    (aux (cdr lst) (cons (car lst) res)))))

  (aux lst '()))

#|
> (unique '("m1.o" "m2.o" "prog" "prog" "all.tgz" "m1.o" "all.tgz"))
("m1.o" "m2.o" "prog" "all.tgz")
> (unique '("m1.o" "m2.o"))
("m1.o" "m2.o")
|#

;;;
;;; Question 1.2
;;;

;; Version récursive "classique"
(define (direct-targets deps touch)
  (if (null? deps)
      '()
      (let ((x (car deps)))
        (if (member touch (dependancies x))
            (cons (target x) (direct-targets (cdr deps) touch))
            (direct-targets (cdr deps) touch)))))


;; Version plus "fonctionnelle" utilisant map
(define (direct-targets deps touch)
  (apply append (map (λ (x)
                       (if (member touch (dependancies x))
                           (list (car x))
                           '()))
                     deps)))

;; Version plus "fonctionnelle" utilisant map et filter (que l'on a du écrire en TD)
(define (direct-targets deps touch)
  (map target (filter (λ (x) (member touch (dependancies x)))
                      deps)))

#|
> (direct-targets deps "m2.c")
("m2.o")
> (direct-targets deps "common.h")
("m1.o" "m2.o")
|#


;;;
;;; Question 1.3
;;;

;; les cibles à construire sont les cibles directes et celle qui sont atteignables
;; à partir de celles-ci. Chaque cible doit être construite 1 fois => utiliser unique
(define (targets deps touch)
  (let* ((direct    (direct-targets deps touch))
         (indirect  (apply append (map (λ(x) (targets deps x))
                                       direct))))
    (unique (append direct indirect))))

#|
> (targets deps "m1.c")
("m1.o" "prog" "all.tgz")
> (targets deps "prog")
("all.tgz")
> (targets deps "common.h")
("m1.o" "m2.o" "prog" "all.tgz")
|#

;;;
;;; Question 1.4
;;;
;; Un bête print de la commande associée à chaque cible
(define (build deps touch)
  (for-each (λ (x) (print (command (assoc x deps))))
            (targets deps touch)))

#|
> (build deps "prog")
tar cvfz all.tgz prog m1.* m2.* common.h
> (build deps "common.h")
gcc -c m1.c
gcc -c m2.c
gcc -o prog m1.o m2.o
tar cvfz all.tgz prog m1.* m2.* common.h
|#

;;; ======================================================================
;;;
;;; QUESTION 2
;;;
;;; ======================================================================

;; Cela ressemble pas mal aux fonctions de recherches que l'on a vues en
;; TD. Toutefois, plusieurs écritures sont envisageables ...

;; Style "direct" (boucle interne sur E)
(define (hashmap E F)
  (λ (x)
    (let Loop ((l1 E)
               (l2 F))
      (cond
       ((null? l1) #f)
       ((equal? x (car l1)) (car l2))
       (else (Loop (cdr l1) (cdr l2)))))))

;; Style "récursif" (si le 1er n'est pas le bon utiliser une hashmap sur les cdr)
(define (hashmap E F)
  (λ (x)
    (cond
       ((null? E) #f)
       ((equal? x (car E)) (car F))
       (else (let ((new-hashmap (hashmap (cdr E) (cdr F))))
               (new-hashmap x))))))

(define (hashmap E F)  ; le même sans le let iniutile
  (λ (x)
    (cond
       ((null? E) #f)
       ((equal? x (car E)) (car F))
       (else ( (hashmap (cdr E) (cdr F))  x))))) ; new-hashmap remplacé par sa valeur

;; Style "avec capture une tabe d'association
(define (hashmap E F)
  (let ((table (map cons E F)))        ;; construire une A-list "static"
    (λ (x)
      (let ((res (assoc x table)))
        (and res (cdr res))))))


#|
> (define h (hashmap '(1 2 3) '(un deux trois)))
> (list (h 1) (h 3) (h 4))
(un trois #f)
|#

;;; ======================================================================
;;;
;;; QUESTION 3
;;;
;;; ======================================================================

;;;
;;; Question 3.1
;;;

;; Une version simple (mais pas complètement correcte) qui utilise le fait que
;; member renvoie une liste commençant par ce qu'on cherche (le test sur la
;; parité de la longueur de ce qui est retourné par member permet de vérifier
;; que v est bien une clé et non pas une valeur (mais ne marche pas si cette
;; valeur est égale à v AVANT la clé v — voir exemple).

(define (find-value v lst default)
  (let ((sublist (member v lst)))
    (if (and sublist (even? (length sublist)))
        (cadr sublist)
        default)))

#|
> (find-value 'width (list 'title "mywin" 'height 700 'width 1000) 600)
1000
> (find-value 'width (list 'title "mywin" 'height 700) 600)
#f
> (find-value 'a (list 'b 'a 'c 1) 'absent)
absent
> (find-value 'a (list 'b 'a 'c 1 'a "hello") 'absent)
absent     ;; FAUX: devrait renvoyer "hello"
|#

;; En fait on a pas trop le choix, il faut avancer 2 par 2 ...
(define (find-value v lst default)
  (cond
   ((or (null? lst) (null? (cdr lst)))  default)
   ((equal? v (car lst))                (cadr lst))
   (else                                (find-value v (cddr lst) default))))

;;;
;;; Question 3.2
;;;

;; Pour l'expansion on a pas beaucoup le choix:
(λ lst
  (let ((title (find-value 'title lst '"Window"))
        (width (find-value 'width lst '1000))
        (height (find-value 'height lst '600)))
    (list title width height)))

;; On en déduit la macro qui est assez simple:
(define-macro (lambda-opt args . body)
  `(λ lst
     (let ,(map (λ (x) `(,(car x) (find-value ',(car x) lst ',(cadr x))))
                args)
       ,@body)))

#|
(define win (lambda-opt ((title "Window") (width 1000) (height 600))
               (printf "Title=~s width=~s height=~s\n" title width height)))

> (win)
Title="Window" width=1000 height=600
> (win 'title "my-app")
Title="my-app" width=1000 height=600
> (win 'width 300 'height 100)
Title="Window" width=300 height=100
> (win 'height 400 'title "my-app" 'width 1200)
Title="my-app" width=1200 height=400
|#

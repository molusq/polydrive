;;;;                                                    -*- coding: utf-8 -*-
;;;; Corrigé.stk        -- Corrigés de la feuille 2
;;;;
;;;;           Author: Erick Gallesio [eg@unice.fr]
;;;;    Creation date: 28-Feb-2000 09:06 (eg)
;;;; Last file update: 25-Feb-2019 14:08 (eg)
;;;;


;=============================================================================
;                                       EXO 1
;=============================================================================

;(equal? 'zero 0)        ; => #f
;(eq? 'zero 0)           ; => #f
;(= 'zero 0)             ; erreur car '=' ne peut être utilisé que sur des nombres
                         ; et zero est un symbole


;=============================================================================
;                                       EXO 2
;=============================================================================

(define rotation-gauche
  (λ (l)
    (append (cdr l)
            (list (car l)))))

(define rotation-droite
  (λ(l)
    (cons (car (reverse l))
          (reverse (cdr (reverse l))))))

;; Cette solution est très inefficace car elle nécessite par trois
;; fois l'appel de la fonction reverse. Une meilleure solution
;; passe par une variable temporaire.
(define rotation-droite
  (λ(l)
    (define rev (reverse l))
    (cons (car rev)
          (reverse (cdr rev)))))

;; Noter que l'on ne peut pas ôter le dernier reverse car il travaille sur une
;; liste différente.

;; Pour terminer une solution plus efficace, mais moins évidente

(define rotation-droite
  (λ(l)
    (define aux
      (λ(l res)
        (cond
         ((null? l)       res)
         ((null? (cdr l)) (cons (car l) (reverse res)))
         (else            (aux (cdr l) (cons (car l) res))))))
    (aux l '())))

;=============================================================================
;                                       EXO 3
;=============================================================================
(define milieu
  (λ(l)
    (if (or (null? l) (null? (cdr l)))
        '()
        (reverse (cdr (reverse (cdr l)))))))

#|
(milieu '(bonjour tout le monde))
(milieu '(hello world))
(milieu '(seul))
(milieu '(x (y) z))
(milieu '(a b))
(milieu '())
|#

;=============================================================================
;                                       EXO 4
;=============================================================================
; Deux versions utilisant chacune des fonctions définies dans les exercices
; précédents

(define swap1
  (λ(l)
    (cons (car (reverse l))
          (append (milieu l)
                  (list (car l))))))

(define swap2
  (λ(l)
    (append (rotation-droite (cdr l))
            (list (car l)))))
#|
(swap1 '(1 2))
(swap1 '(1 2 3 4))
(swap1 '((1 2 )(3 4)))
(swap1 '(x () y))
(swap1 '(1))
|#

;=============================================================================
;                                       EXO 5
;=============================================================================

; ----  LIST?

; Une première écriture (cond �  trois branches "habituel")
(define List?
  (λ(l)
    (cond
     ((null? l) #t)
     ((pair? l) (List? (cdr l)))
     (else      #f))))

; Autre écriture
(define List?
  (λ(l)
    (or (null? l)
        (and (pair? l) (List? (cdr l))))))

; ---- LENGTH
(define Length
  (λ(l)
    (cond
      ((null? l) 0)
      ((pair? l) (+ 1 (Length (cdr l))))
      (else      (error "Mauvaise liste")))))

; ---- APPEND
(define Append
  (λ(a b)
    (cond
      ((null? a) b)
      ((pair? a) (cons (car a)
                       (Append (cdr a) b)))
      (else      (error "Mauvaise liste")))))

; ---- LIST-REF
(define List-ref
  (λ(l k)
    (cond
     ((null? l) (error "index trop grand"))
     ((pair? l) (if  (zero? k)
                     (car l)
                     (List-ref (cdr l) (- k 1))))
      (else     (error "Mauvaise liste")))))

; ---- MEMBER
(define Member
  (λ(x l)
    (cond
     ((null? l)   #f)
     ((pair? l)   (if (equal? x (car l))
                      l
                      (Member x (cdr l))))
     (else        (error "Mauvaise liste")))))

;=============================================================================
;                                       EXO 6
;=============================================================================

;; Version 1: écriture  habituelle

(define entiers
  (λ(l)
    (cond
     ((null? l)    0)
     ((pair? l)    (+ (entiers (car l)) (entiers (cdr l))))
     (else         (if (integer? l)
                       1
                       0)))))

;; Ce qui se réécrit en
(define entiers
  (λ(l)
    (cond
     ((null? l)    0)
     ((pair? l)    (+ (entiers (car l)) (entiers (cdr l))))
     ((integer? l) 1)
     (else         0))))

;; Version 2: On regroupe maintenant les deux cas qui renvoient 0
(define entiers
  (λ(l)
    (cond
     ((pair? l)    (+ (entiers (car l)) (entiers (cdr l))))
     ((integer? l) 1)
     (else         0))))


#|
(entiers '(1 (2 (34) 5) 6 () (78)))
(entiers '((((1 a b))) (((x 2)))))
(entiers '12)
(entiers 12)
(entiers '())
|#

;=============================================================================
;                                       EXO 7
;=============================================================================
(define Reverse
  (λ(l)
    (define aux (λ(l res)
                  (cond
                     ((null? l) res)
                     ((pair? l) (aux (cdr l) (cons (car l) res)))
                     (else      (error "Mauvaise liste")))))
    (aux l '())))


;; Ecriture "directe"
(define full-reverse
  (λ(l)
    (define aux (λ(l res)
                  (cond
                   ((null? l) res)
                   ((pair? l) (aux (cdr l) (cons (full-reverse (car l))
                                                 res)))
                   (else      l))))  ;; Attention ici on renvoie l et pas erreur...
    (aux l '())))



;; Cette fonction ne renvoie donc plus une erreur si on l'appelle avec un
;; argument qui n'est pas une liste. Pour éviter ce problème, on peut
;; tester ce cas explicitement dans le corps de la fonction
(define full-reverse
  (λ (l)
    (define full-reverse-int
      (λ(l)
        (define aux (λ (l res)
                      (cond
                        ((null? l) res)
                        ((pair? l) (aux (cdr l)
                                        (cons (full-reverse-int (car l))
                                              res)))
                        (else      l))))
        ;; Corps de la fonction full-reverse-int
        (aux l '())))

    ;; Corps de la fonction full-reverse
    (if (list? l)
        (full-reverse-int l)
        (error "Mauvaise liste"))))


#|
(full-reverse '(1 (2 (3 4) 5)(6 7) (8)))
(full-reverse '(1 (2 (3 (4 (5))))))
(full-reverse 'symbole)
|#


;=============================================================================
;                                       EXO 8
;=============================================================================
(define is-in?
  (λ(x l)
    (cond ((null? l) #f)
          ((pair? l) (or (equal? x (car l))
                         (equal? x (cdr l))
                         (is-in? x (car l))
                         (is-in? x (cdr l))))
          (else #f))))

;; Comme on fait la même chose dans le cas null? et dans le else, on peut simplifier
;; l'écriture de la fonction:
(define is-in?
  (λ(x l)
    (if (pair? l)
        (or (equal? x (car l))
            (equal? x (cdr l))
            (is-in? x (car l))
            (is-in? x (cdr l)))
        #f)))

;; ce qui peut encore se simplifier en
(define is-in?
  (λ(x l)
    (and (pair? l)
         (or (equal? x (car l))
             (equal? x (cdr l))
             (is-in? x (car l))
             (is-in? x (cdr l))))))

#|
(is-in? 'y '(x (y z) w))
(is-in? 'a '(a . b))
(is-in? 'b '(a . b))
(is-in? 'c '(a b . c))
(is-in? '(a b) '(x (y (a b) z) w))
(is-in? '(a b) '(x a b y))
(is-in? 'x '(x))
(is-in? 'x '())
(is-in? '() '())
|#

;=============================================================================
;                                       EXO 9
;=============================================================================
(define flat
  (λ(l)
    (cond
       ((null? l) l)
       ((pair? l) (append (flat (car l))
                          (flat (cdr l))))
       (else      (list l)))))

#|
(flat '(a(b (c)((d)))e()(f)))
(flat '(() 1()2()3))
(flat '(((((x))))))
(flat '(()()()()))
|#

;=============================================================================
;                                       EXO 10
;=============================================================================

;; Suppression du premier seulement
(define Remove
  (λ (e l)
    (cond
      ((null? l) l)
      ((pair? l) (if (eq? (car l) e)
                     (cdr l)         ;; Supprimer cet élement de la liste
                     (cons (car l)
                           (Remove e (cdr l)))))
      (else (error "bad list" l)))))


;; Suppression de toutes les occurences
(define Remove
  (λ (e l)
    (cond
      ((null? l) l)
      ((pair? l) (if (eq? (car l) e)
                     (Remove e (cdr l))         ;; Supprimer cet élement de la liste ET DANS LE RESTE
                     (cons (car l)
                           (Remove e (cdr l)))))
      (else (error "bad list" l)))))

#|
(Remove 1 '(0 1 2 3 0 1 2 3))
|#

;=============================================================================
;                                       EXO 11
;============================================================================
(define remove-duplicate
  (λ(l)
    (define aux (λ(l res)
                  (cond
                    ((null? l) (reverse res))
                    ((pair? l) (aux (cdr l)
                                    (if (member (car l) res)
                                        res
                                        (cons (car l) res))))
                    (else      (error "Mauvaise liste")))))
    (aux l '())))
#|
(remove-duplicate '(x (x y) x z (x) x) )
(remove-duplicate '(a (a b) b (a b) a (a b) b) )
(remove-duplicate '(a (a (a (a)))) )
|#
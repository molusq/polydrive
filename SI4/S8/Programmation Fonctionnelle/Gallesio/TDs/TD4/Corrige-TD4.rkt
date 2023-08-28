;;;;                                            -*- coding: utf-8 -*-
;;;;
;;;; Corrigé.ss         -- Corrigés de la feuille 4
;;;;
;;;;           Author: Erick Gallesio [eg@unice.fr]
;;;;    Creation date: 28-Feb-2000 09:06 (eg)
;;;; Last file update: 23-Feb-2021 12:38 (eg)
;;;;

;=============================================================================
;                                       EXO 1
;=============================================================================
(define (depth l)
  (if (pair? l)
      (+ 1 (apply max (map depth l)))
      0))

(define (flat l)
  (cond
     ((null? l) l)
     ((pair? l) (apply append (map flat l)))
     (else      (list l))))

;=============================================================================
;                                       EXO 2
;=============================================================================

(define (sum term a next b)
  (if (> a b)
      0
      (+ (term a)
         (sum term (next a) next b))))

;; Somme des 100 premiers entiers
(sum (λ (x) x) 0 (λ (x) (+ x 1)) 99)

;; Somme des carrés des 20 premiers entiers
(sum (λ (x) (* x x)) 0 (λ (x) (+ x 1)) 19)

;; Intégrale de f(x)=x^2-1 entre 3 et 5
(let ((pas  1/10000)
      (func (λ (x) (- (* x x) 1))))
  (sum (λ (x) (* (func x) pas)) 3 (λ (x) (+ x pas)) 5))

;=============================================================================
;                                       EXO 3
;=============================================================================

;;
;; Un map avec des fonctions unaires
;;
(define (simple-map fn l)
  (if (null? l)
      '()
      (cons (fn (car l))
            (simple-map fn (cdr l)))))


(define (simple-map fn l)
  (cond
    ((null? l) l)
    ((pair? l) (cons (fn (car l))
                     (simple-map fn (cdr l))))
    (else      (error "bad list"))))


;;
;; Un map qui accepte des fonctions de n'importe qu'elle arité (mais
;; ici toutes les listes sont de longueur égale).
;; Remarque: On aura jamais (null? l). En fait quand on a fini, l est
;; égal �  '( () () ... () ).
;; Pour simplifier, on regarde que (car l) est null.
;;
(define (gen-map fn  . l)
  (cond
    ((null? (car l)) '())
    ((pair? l)       (cons (apply fn (simple-map car l))
                           (apply gen-map fn (simple-map cdr l))))
    (else            (error "bad list"))))


;;; (gen-map cons '(1 2 3) '(4 5 6))
;;; ((1 . 4) (2 . 5) (3 . 6))
;;; (gen-map cons '(1 2 3) '(4 5))
;;; ==> plante


;;
;; Le vrai map (les listes peuvent être de longueur différentes. On
;; s'arrête dès que l'on a atteint la fin d'une des listes).
;; En fait, c'est simple on s'arrête quand on rencontre une liste vide
;; dans l.
;; Note: cette version n'était pas demandée.

(define (Xmap fn . l)
  (cond
    ((member '()  l)  '())
    ((pair? l)        (cons (apply fn (simple-map car l))
                            (apply Xmap fn (simple-map cdr l))))
    (else             (error "bad list"))))



;;; (Xmap cons '(1 2 3) '(4 5 6))
;;; ((1 . 4) (2 . 5) (3 . 6))
;;; (Xmap cons '(1 2 3) '(4 5))
;;; ((1 . 4) (2 . 5))




;=============================================================================
;                                       EXO 4
;=============================================================================
;; La fonction reduce
(define (reduce fct lst base)
  (cond
    ((null? lst) base)
    ((pair? lst) (fct (car lst)
                      (reduce fct (cdr lst) base)))
    (else (error "bad list" lst))))


;;; Factorielle de 5
(reduce * '(1 2 3 4 5) 1)
;;; Somme des 5 premiers entiers
(reduce + '(1 2 3 4 5) 0)
;;; Reverse
(reduce (λ (item res) (append res (list item)))
        '(1 2 3 4 5)
        '())


;; ==============================================
;; 4.1 Somme des carrés des 5 premiers entiers
(reduce (λ (x res) (+ (* x x) res))
        '(1 2 3 4 5)
        0)
;; ==============================================
;; 4.2 min et max
(define (Min . lst)
  (reduce (λ (x res) (if (< x res) x res))
          (cdr lst)
          (car lst)))   ; valeur de base = 1 item de la liste

(define (Max . lst)
  (reduce (λ (x res) (if (> x res) x res))
          (cdr lst)
          (car lst)))   ; valeur de base = 1 item de la liste


;; note: ces fonctions ne marchent que si elles ont au moins un paramètre et
;; se plantent sinon (comme les fonctions standard). Si on veut déclencher sa
;; propre erreur on peut écrire:

(define (Min . lst)
  (if (null? lst)
      (error "Min doit avoir au moins paramètre")
      (reduce (λ (x res) (if (< x res) x res))
              (cdr lst)
              (car lst))))   ; valeur de base = 1 item de la liste
 
;; Même chose pour Max

(define L '(1 2 3 -1 0 -10 0 100 7 1 3))
(printf "L = ~s\n" L)

(printf "Min = ~S\n" (apply Min L))
(printf "Max = ~S\n" (apply Max L))

;; ==============================================
;; 4.3 length
(define (Length lst)
  (reduce (λ (x res) (+ res 1))
          lst
          0))

(printf "Length = ~S\n" (Length L))
;; ==============================================
;; 4.4 Map

(define (Map fct lst)
  (reduce (λ (x res) (cons (fct x) res))
          lst
          '()))

(printf "Map carré: ~S\n" (Map (λ(x) (* x x)) L))
(printf "Map positive?: ~S\n" (Map positive? L))

;; ==============================================
;; 4.5 Filter
(define (Filter pred? lst)
  (reduce (λ (x res) (if (pred? x) (cons x res) res))
          lst
          '()))

(printf "Filter positive: ~S\n" (Filter positive? L))   ; positive? est pré-définie
(printf "Filter negative: ~S\n" (Filter negative? L))   ; negative? est pré-définie

;; ==============================================
;; 4.6 List->set
(define (List->set lst)
  (reduce (λ (x res) (if (member x res) res (cons x res)))
          lst
          '()))
(printf "->set: ~S\n" (List->set L))

;; ========================================================
;; 4.7 Every?
(define (Every? pred? lst)
  (reduce (λ (x res) (and (pred? x) res))
          lst
          #t))

(printf "Every integer?: ~S\n" (Every? integer? L))
(printf "Every positive?: ~S\n" (Every? positive? L))

;; ========================================================
;; 4.7 Any?
(define (Any? pred? lst)
  (reduce (λ (x res) (or (pred? x) res))
          lst
          #f))

(printf "Any integer?: ~S\n" (Any? integer? L))
(printf "Any boolean? ~S\n" (Any? boolean? L))


;=============================================================================
;                                       EXO 5
;=============================================================================

;; En fait, les ensembles sont représentés par des prédicats. Cela
;; veut dire que x appartient �  E ssi (E x) renvoie #t et, par
;; conséquent, si le prédicat renvoie #f, on considère que l'élément
;; n'est pas dans l'ensemble.
;;
;; Qu'est ce qu'on gagne avec cette représentation? Avec cette
;; représentation, on n'a pas besoin d'avoir une structure de données
;; qui contienne en mémoire effectivement tous les éléments de
;; l'ensemble. Ce qu'on a en mémoire c'est la propriété que partagent
;; les éléments de l'ensemble. On peut donc représenter des ensembles
;; infinis sans problème.

(define (vide)
  (λ (x) #f))

(define (entiers)
  (λ (x) (integer? x)))  ;; puisque d'aprèsl'énoncé x est entier

(define (multiple k)
  (λ (x) (= (modulo x k) 0)))

(define (singleton k)
  (λ (x) (= x k)))

(define (intervalle m n)
  (λ (x) (<= m x n)))


(define (appartient? x E)
  (E x))

(define (union E1 E2)
  (λ (x)
    (or (appartient? x E1)
        (appartient? x E2))))

(define (intersection E1 E2)
  (λ (x)
    (and (appartient? x E1)
         (appartient? x E2))))

(define (complementaire E)
  (λ (x)
    (not (appartient? x E))))

(define (difference E1 E2)
  (λ (x)
    (and (appartient? x (union E1 E2))
         (not (appartient? x (intersection E1 E2))))))


;;; Tests 
(define N (entiers))
(define Pair (multiple 2))
(define Impairs (lambda(x) (not (multiple 2))))

(define only-42 (singleton 42))

(printf "42 ∈ ℕ ⟹ ~a\n" (appartient? 42 N))
(printf "42 ∈ ∅ ⟹ ~a\n" (appartient? 42 (vide)))

(printf "42 ∈ only-42 ⟹ ~a\n" (appartient? 42 only-42))
(printf "1 ∈  only 42 ⟹ ~a\n" (appartient? 1 only-42))

(define fizz (multiple 3))
(define buzz (multiple 5))
(define fizz-buzz (union fizz buzz))

(printf "29 ∈ fizz-buzz ⟹ ~a\n" (appartient? 29 fizz-buzz))
(printf "30 ∈ fizz-buzz ⟹ ~a\n" (appartient? 30 fizz-buzz))





;=============================================================================
;                                       EXO 6
;=============================================================================

;; En fait c'est super simple!!

;; Comme c'est dit dans le sujet, il faut transformer une fonction �  n
;; paramètres en une fonction qui renvoie une fonction �  n-1 paramètres.
;; On a donc:
(define (curry f param1)
  (λ params
    (apply f param1 params)))

;; Noter ici que params n'est pas entre parenthèses, cela veut dire que la
;; fonction renvoyée par curry est une fonction d'arité variable (on
;; suppose que c'est n-1)

#|
(define mult2   (curry * 2))       ;; mult2: c'est multiplier par 2
(define doubles (curry map mult2)) ;; doubles d'une liste c'est «maper» mult2 sur la liste

> (mult2 100)
200
> (doubles '(1 2 3 4 5))
(2 4 6 8 10)
|#


;=============================================================================
;                                       EXO 7
;=============================================================================

;; L�  c'est pas compliqué, il faut juste ce concentrer un peu et ça
;; devient évident.

(define (kons a b)
  (λ (f)
    (f a b)))

(define (kar obj)
  (obj (λ (a b) a)))

(define (kdr obj)
  (obj (λ (a b) b)))

;; Donc maintenant on peut representer des paires (i.e. des données) avec
;; des procédures (i.e. du code). Funny, isn't it?
(define k (kons 1 (kons 2 3)))
(printf "Exo 6\n")
(printf "         k = ~s\n" k)
(printf "2e élément = ~s\n" (kar (kdr k)))


;; Il est facile maintenant de construire une fonction qui clone une liste
;; Scheme classique en une liste de kons
(define (klone lst)
  (cond
    ((null? lst) '())
    ((pair? lst) (kons (car lst)
                       (klone (cdr lst))))
    (else        (error "bad list"))))

;; Utilisons ce klone
(define lst (klone '(1 2 3)))
(kar lst)
(kar (kdr lst))
(kar (kdr (kdr lst)))
(kdr (kdr (kdr lst)))
;=============================================================================
;                                       EXO 2
;=============================================================================
(define fact
  (Î» (n)
    (if (< n 2)
        1
        (* n (fact (- n 1))))))

(define fib
  (Î» (n)
    (if (< n 2)
        n
        (+ (fib (- n 1)) (fib (- n 2))))))


;=============================================================================
;                                       EXO 3
;=============================================================================
(define delta
  (Î» (a b c)
    (- (* b b) (* 4 a c))))

(define racines
  (Î» (a b c)
    (cond
      ((> (delta a b c) 0) (list (/ (- (- b) (sqrt (delta a b c))) (* 2 a))
                                 (/ (+ (- b) (sqrt (delta a b c))) (* 2 a))))
      ((= (delta a b c) 0) (list (/ (- b) (* 2 a))))
      (else                (list)))))

; Bien sÃ»r, cette solution n'est pas trÃ¨s efficace puisqu'on calcule
; plusieurs fois le DELTA. Dans une version plus rÃ©aliste, on utiliserait
; la forme LET que nous verrons plus tard
; En attendant, on peut dÃ©finir une variable interne avec define
; Cela donne:
(define racines
  (Î» (a b c)
    (define Î” (- (* b b) (* 4 a c))) ; variable est interne Ã  la fct racine
    (cond
      ((> Î” 0) (list (/ (- (- b) (sqrt (delta a b c))) (* 2 a))
                     (/ (+ (- b) (sqrt (delta a b c))) (* 2 a))))
      ((= Î” 0) (list (/ (- b) (* 2 a))))
      (else    (list)))))

; (racines 1 2 1)
; (racines 1 -1 -6)
; (racines 1 0 -1)
; (racines 1 1 1)

;=============================================================================
;                                       EXO 4
;=============================================================================
(define somme-carrÃ©s
  (Î» (n)
    (if (zero? n)
        (f 0)
        (+ (* n n) (somme-carrÃ©s (- n 1))))))

; (somme-carrÃ©s 2)
; (somme-carrÃ©s 3)


(define somme-cubes
  (Î» (n)
    (if (zero? n)
        0
        (+ (* n n n) (somme-cubes (- n 1))))))

; (somme-cubes 3)
; (somme-cubes 5)

;;
;; Une premiÃ¨re version de sigma
;;
(define sigma
  (Î» (f n)
    (if (zero? n)
        0
        (+ (f n) (sigma f (- n 1))))))

(define somme-carrÃ©s
  (Î» (n)
    (sigma (Î» (n) (* n n)) n)))

(define somme-cubes
  (Î» (n)
    (sigma (Î» (n) (* n n n)) n)))


;=============================================================================
;                                       EXO 5
;=============================================================================
(define dÃ©rivÃ©e
  (Î» (f)
    (Î»(x dx)
      (/ (- (f (+ x dx)) (f x))
         dx))))

; (define carrÃ© (Î» (n) (* n n)))
; (define d-carrÃ© (dÃ©rivÃ©e carrÃ©))
;    Le dx doit Ãªtre passÃ© au moment de l'appel Ã  d-carrÃ© (â†’ on peut le changer)
; (d-carrÃ© 3 0.001) â†’ 6.000999999999479
; (d-carrÃ© 3 0.1)   â†’ 6.100000000000012


;;
;; DeuxiÃ¨me version
;;
(define dÃ©rivÃ©e
  (Î» (f dx)
    (Î»(x)
      (/ (- (f (+ x dx)) (f x))
         dx))))

; (define carrÃ© (Î» (n) (* n n)))
; (define d-carrÃ© (dÃ©rivÃ©e carrÃ© 0.001))
;     le dx est passÃ© au moment de la construction de la dÃ©rivÃ©e. Il est donc fixe
; (d-carrÃ© 3)  â†’ 6.000999999999479

;=============================================================================
;                                       EXO 6
;=============================================================================

;; Fonctions mutuellement rÃ©cursives car  Pair? appelle Impair?
;; qui appelle Pair? ...
(define Pair?
  (Î» (n)
    (if (= n 0)
        #t
        (Impair? (- n 1)))))

(define Impair?
  (Î» (n)
    (if (= n 0)
        #f
        (Pair? (- n 1)))))


;=============================================================================
;                                       EXO 7
;=============================================================================
(define fizzbuzz-value
   (Î» (n)
     (cond
       ((zero? (modulo n 15)) "FizBuzz")
       ((zero? (modulo n 3))  "Fizz")
       ((zero? (modulo n 5))  "Buzz")
       (else  n))))
   
(define fizzbuzz
  (Î» (min max)
    (if (< min max)
        (begin
          (display (fizzbuzz-value min))   ; afficher min
          (display " ")                    ; afficher un sÃ©parateur
          (fizzbuzz (+ min 1) max))        ; Boucle: afficher [min+1..max[
        (newline))))                       ; Fini: passer Ã  la ligne


;; MÃªme chose avec la fonction fizzbuzz-value dans la fonction fizzbuzz
(define fizzbuzz
  (Î» (min max)
    (define fizzbuzz-value
      (Î» (n)
        (cond
          ((zero? (modulo n 15)) "FizBuzz")
          ((zero? (modulo n 3))  "Fizz")
          ((zero? (modulo n 5))  "Buzz")
          (else  n))))
    
    (if (< min max)
        (begin
          (display (fizzbuzz-value min))   ; afficher min
          (display " ")                    ; afficher un sÃ©parateur
          (fizzbuzz (+ min 1) max))        ; Boucle: afficher [min+1..max[
        (newline))))


;=============================================================================
;                                       EXO 8
;=============================================================================
(define sigma
  (Î» (f)
    (define somme (Î» (n)
                    (if (zero? n)
                        (f 0)
                        (+ (f n) (somme (- n 1))))))
    somme))

(define somme-carrÃ©s (sigma (Î» (x) (* x x))))
(define somme-cubes  (sigma (Î» (x) (* x x x))))
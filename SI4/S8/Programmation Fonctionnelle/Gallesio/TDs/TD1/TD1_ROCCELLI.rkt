;EXERCICE 1
(display "=========EXO1=========\n")
(+ 3 2)
(- (+ 2 5) 3)
(* (+ 2 5) 4 3)
(if(< 1 2)(+ 3 2) 0)
(+ 1 2 3 4 5)
(- 5 2 3)
(* (+ 1 2) (+ 2 3 4))
;EXERCICE 2
(display "=========EXO2=========\n")
(display "FACTORIELLE\n")
(define fact
  (lambda(x)
    (if(< x 0)
       0
       (if(= x 0)
          1
          (* x (fact(- x 1)))))))

(fact 5)
(fact 10)

(display "FIBONACCI\n")
(define fib
  (lambda(x)
    (if(< x 0)
       0
       (if(= x 0)
          0
          (if (= x 1)
              1
              (+ (fib (- x 1)) (fib (- x 2))))))))

(fib 5)
(fib 10)
(fib 20)
;EXERCICE 3
(display "=========EXO3=========\n")
(define racines
  (lambda(a b c)
    (define carré (lambda(x) (* x x)))
    (define delta (lambda(a b c) (- (carré b) (* 4 a c))))
    (cond
      ((< (delta a b c) 0) (list ))
      ((= (delta a b c) 0) (list (/(+ (- b) (sqrt (delta a b c))) (* 2 a))))
      (else (list (/(+ (- b) (sqrt (delta a b c))) (* 2 a))
                  (/(- (- b) (sqrt (delta a b c))) (* 2 a)))))))

(racines 1 2 1)
(racines 1 0 -1)
(racines 1 1 -6)
(racines 1 1 1)

;EXERCICE 4
(display "=========EXO4=========\n")
(display "SOMMES CARRES\n")
(define carré (lambda(x) (* x x)))
(define somme-carrés
  (lambda(x)
    (if (<= x 0)
        0
        (+ (somme-carrés (- x 1)) (carré x)))))

(somme-carrés 2)
(somme-carrés 3)
(somme-carrés 100)

(display "SOMMES CUBES\n")
(define cube (lambda(x) (* x x x)))
(define somme-cubes
  (lambda(x)
    (if (<= x 0)
        0
        (+ (somme-cubes (- x 1)) (cube x)))))

(somme-cubes 2)
(somme-cubes 3)
(somme-cubes 100)

(display "SIGMA\n")
(define sigma
  (lambda(x f)
    (if (<= x 0)
        0
        (+ (sigma (- x 1) f) (f x)))))

(sigma 2 carré)
(sigma 3 carré)
(sigma 100 carré)
(sigma 2 cube)
(sigma 3 cube)
(sigma 100 cube)

;EXERCICE 5
(display "=========EXO5=========\n")
(display "DERIVEE\n")
(define dérivée
  (lambda(f)
    (lambda (x dx)
      (/ (- (f (+ x dx)) (f x)) dx))))

(define carre (lambda (n) (* n n)))
(define d-carre (dérivée carre))
(d-carre 3 0.001)

(display "DERIVEE2\n")

(define dérivée2
  (lambda(f dx)
    (lambda (x)
      (/ (- (f (+ x dx)) (f x)) dx))))

(define d-carre2 (dérivée2 carre 0.001))
(d-carre2 3)

;EXERCICE 6
(display "=========EXO6=========\n")
(display "PAIR\n")
(define Pair?
  (lambda(x)
    (if(> x 1)
       (Pair? (- x 2))
       (if(= x 0)#t #f))))
(Pair? 0)
(Pair? 1)
(Pair? 2)
(Pair? 3)
(Pair? 1234)
(Pair? 1235)
(display "IMPAIR\n")
(define Impair?
  (lambda(x)
    (if(> x 1)
       (Impair? (- x 2))
       (if(= x 0)#f #t))))
(Impair? 0)
(Impair? 1)
(Impair? 2)
(Impair? 3)
(Impair? 1234)
(Impair? 1235)

;EXERCICE 7
(display "=========EXO7=========\n")
(define fizzbuzz
  (lambda(min max)
    (if(> min max)
       (display "\n")
       (if(= (modulo min 15) 0)
          (begin (display "FizzBuzz ") (fizzbuzz (+ min 1) max))
          (if(= (modulo min 3) 0)
             (begin (display "Fizz ") (fizzbuzz (+ min 1) max))
             (if(= (modulo min 5) 0)
                 (begin (display "Buzz ") (fizzbuzz (+ min 1) max))
                 (begin (display min) (display " ") (fizzbuzz (+ min 1) max))))))))

(fizzbuzz 0 0)
(fizzbuzz 0 1)
(fizzbuzz 0 20)
(fizzbuzz 0 21)

;EXERCICE 8
(display "=========EXO8=========\n")
(display "SIGMA2\n")
(define sigma2
  (lambda(f)
    (begin
      (define fct
        (lambda(x)
          (if (<= x 0)
              0
              (+ (fct (- x 1)) (f x)))))
      fct)))

(define somme-carres3(sigma2 carre))
(define somme-cubes3(sigma2 cube))
(somme-carres3 2)
(somme-carres3 3)
(somme-carres3 100)
(somme-cubes3 2)
(somme-cubes3 3)
(somme-cubes3 100)


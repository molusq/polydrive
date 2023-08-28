;;EXERCICE 1
(display "EXO1\n")

(car '((a b) c (d e) f))
;;(a b)
(car '((1 2 3)))
;;(1 2 3)
(car '(x y z))
;;x
(cdr '((a b) c (d e) f))
;;(c (d e) f)
(cdr '((1 2 3)))
;;()
(cdr '(x y z))
;;(y z)
(car (cdr '((a b) c (d e) f)))
;;c
(cdr (car '((a b) c (d e) f)))
;;(b)
;;(= 'zero 0)
;;ERROR
(equal? 'zero 0)
;;#f
(eq? 'zero 0)
;;#f

;;EXERCICE 2
(display "\nEXO2\n")
(define rotation-gauche
  (lambda(lst)
    (append
     (cdr lst)
     (list (car lst)))))

(rotation-gauche '(a b c d))

(define rotation-droite
  (lambda(lst)
    (append
     (list (car (reverse lst)))
     (reverse (cdr (reverse lst))))))

(rotation-droite '(a b c d))

;;EXERCICE 3
(display "\nEXO3\n")
(define milieu
  (lambda(lst)
    (cond
      ((null? lst)  ())
      ((pair? lst)
       (if (equal? (length lst) 1)
           ()
           (reverse (cdr (reverse (cdr lst))))))
      (else         (display "ERROR : bad list\n")))))

(milieu '(bonjour tout le monde))
(milieu '(Hello World))
(milieu '(seul))
(milieu '(x (y) z))
(milieu '(a b))
(milieu '())
(milieu '(agrou zehahahaha '(liste au pif tu coco) dernier))

;;EXERCICE 4
(display "\nEXO4\n")
(define swap
  (lambda (lst)
    (cond
      ((null? lst)  ())
      ((pair? lst)
       (if (< 1 (length lst))
           (append
            (list(car (reverse lst)))
            (milieu lst)
            (list (car lst)))
           ()))
      (else (display "ERROR : bad list\n")))))

(swap '(1 2))
(swap '(1 2 3 4))
(swap '((1 2)(3 4)))
(swap '(x () y))
(swap '(1 2 3 wazaaaaaaaaaa 5 6))

;;EXERCICE 5
(display "\nEXO5\n")
(define liste-ref
  (lambda(lst n)
    (cond
      ((null? lst)  () )
      ((pair? lst)
       (cond
         ((> n (length lst)) (display "ERROR : liste-ref : n>length(lst)\n"))
         ((< n 0) (display "ERROR : liste-ref : n<0\n"))
         (else
          (if (= 0 n)
              (car lst)
              (liste-ref (cdr lst) (- n 1))))))
      (else         (display "ERROR : liste-ref : bad list\n") ))))

(list-ref '(a b c) 0)
(liste-ref '(a b c) 0)
(list-ref '(a b c) 2)
(liste-ref '(a b c) 2)
;;(list-ref '(a b c) 5) ;;-->list-ref: index too large for list
(liste-ref '(a b c) 5)
;;(list-ref '(a b c) -1) ;; --> list-ref: contract violation expected: exact-nonnegative-integer?
(liste-ref '(a b c) -1)


(define MEMBER
  (lambda(elem lst)
    (cond
      ((null? lst)  #f )
      ((pair? lst)
           (if (equal? elem (car lst))
               lst
               (MEMBER elem (cdr lst))))
      (else         (display "ERROR : MEMBER : bad list\n") ))))

(member 3 '(1 2 3 4 5))
(MEMBER 3 '(1 2 3 4 5))
(member 1 '(1 2 3 4 5))
(MEMBER 1 '(1 2 3 4 5))
(member 6 '(1 2 3 4 5))
(MEMBER 6 '(1 2 3 4 5))
(member 2 '(1 (2 3 4) 5))
(MEMBER 2 '(1 (2 3 4) 5))

;;EXERCICE 6
(display "\nEXO6\n")
(define entiers
  (lambda(obj)
    (cond
      ((integer? obj) 1)
      ((null? obj) 0)
      ((pair? obj)
       (cond
         ((integer? (car obj)) (+ 1 (entiers (cdr obj))))
         ((list? (car obj)) (+ (entiers (car obj)) (entiers (cdr obj))))
         (else (entiers (cdr obj)))))
      (else (display "ERROR : entiers : bad list\n") ))))

(entiers '(1 (2 (34) 5) 6 () (78)) )
(entiers '((((1 a b))) (((x 2)))) )
(entiers '12)
(entiers '())

;;EXERCICE 7
(display "\nEXO7\n")
(define full-reverse
  (lambda(lst)
    (define aux
      (lambda(lst res)
        (cond
          ((null? lst) res)
          ((pair? lst)
           (aux (cdr lst) (cons (full-reverse (car lst)) res)))
        (else lst))))
    (aux lst '())))

(full-reverse '(1 (2 (3 4) 5) (6 7) (8)) )
(full-reverse '(1 (2 (3 (4 (5))))) )
(full-reverse 'symbole)
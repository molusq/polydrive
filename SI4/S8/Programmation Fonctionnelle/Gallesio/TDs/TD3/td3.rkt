;;EXERCICE 1
(display "REMOVE-IF\n")
(define remove-if
  (lambda (tst lst)
    (cond
      ((null? lst)  () )
      ((pair? lst)
       (cond
         ((tst (car lst)) (remove-if tst (cdr lst)))
         (else (append (list (car lst)) (remove-if tst (cdr lst))))))
      (else (display "ERROR : remove-if : bad list\n") ))))

(remove-if number? '(a 1 (1) 2 (x y) ()) )
(remove-if (λ (x)(< x 5)) '(6 2 9 3 7 1) )
(remove-if null? '(a () 1 () (())))

(display "COUNT-IF\n")
(define count-if
  (lambda (tst lst)
    (cond
      ((null? lst)  0 )
      ((pair? lst)
       (cond
         ((tst (car lst)) (+ 1 (count-if tst (cdr lst))))
         (else (count-if tst (cdr lst)))))
      (else (display "ERROR : count-if : bad list\n") ))))

(count-if symbol? '(a (a) () c (x y) b) )
(count-if symbol? '(a x () c (x y) b) )
(count-if (λ (x)(> x 0)) '(-1 2 0 3 -5 -10 7) )

(display "FIND-IF\n")
(define find-if
  (lambda (tst lst)
    (cond
      ((null? lst)  () )
      ((pair? lst)
       (cond
         ((tst (car lst)) (car lst))
         (else (find-if tst (cdr lst)))))
      (else (display "ERROR : find-if : bad list\n") ))))

(find-if list? '(a 1 (x y) () (a b)) )
(find-if (λ (x)(> x 10)) '(2 5 12 1 15) )

(display "REMOVE-IF-NOT\n")
(define remove-if-not
  (lambda (tst lst) (remove-if (lambda (x)(not (tst x))) lst )))

(remove-if-not (λ (x)(< x 5)) '(6 2 9 3 7 1) )
(remove-if-not null? '(a () 1 () (())) )


;;AVEC MAP ET APPLY
(display "COUNT-IF2\n")
(define count-if2
  (lambda (tst lst)
    (cond
      ((null? lst)  0 )
      ((pair? lst)
       (apply + (map
               (lambda (boolean)(if (equal? boolean true) 1 0))
               (map tst lst))))
      (else (display "ERROR : count-if2 : bad list\n") ))))

(count-if2 symbol? '(a (a) () c (x y) b) )
(count-if2 symbol? '(a x () c (x y) b) )
(count-if2 (λ (x)(> x 0)) '(-1 2 0 3 -5 -10 7) )

;;EXERCICE 2
(display "EXO2\n")
(define (show . lst)
  (for-each (λ (x)
              (display x)
              (display " "))
            lst)
  (newline))

(define hanoi
  (lambda (nbDisques)
    (define hanoi-aux
      (lambda (x t1 t2 t3)
        (if (= x 1)
            (begin (show "deplacer un disque de" t1 "vers" t2) 1)
            (+ (+ (hanoi-aux (- x 1) t1 t3 t2)
                  (hanoi-aux 1 t1 t2 t3))
               (hanoi-aux (- x 1) t3 t2 t1))          
        )))
    (hanoi-aux nbDisques 'a 'b 'c)))

(hanoi 3)
(hanoi 4)
(hanoi 5)

;;EXERCICE 3
(display "EXO3\n")
(define add2 (λ (a b) (+ a b)))
(define add
  (lambda lst
    (cond
      ((null? lst)  0 )
      ((pair? lst)
       (if (list? (car lst))
           (if (null? (car lst))
               0
               (add2 (car (car lst)) (add (cdr (car lst)))))
           (add2 (car lst) (add (cdr lst)))))
      (else (display "ERROR : add : bad list\n") ))))

(add 1 2)
(add 1 2 3)
(add 1 2 3 4 5)
(add 1)
(add)

;;EXERCICE 4
(display "EXO4\n")
(define (append2 l1 l2)
  (cond
    ((null? l1) l2)
    ((pair? l1) (cons (car l1)
                     (append2 (cdr l1) l2)))
    (else       (error "bad list" l1))))

(define my-append
  (lambda lst
    (define res ())
    (cond
      ((null? lst)  () )
      ((pair? lst)
       (begin (for-each (lambda (lst2) (for-each (lambda (elem) (if (null? res)
                                                              (set! res (list elem))
                                                              (set! res (append2 res (list elem))))) lst2)) lst) res))
      (else (display "ERROR : my-append : bad list\n") ))))

(my-append '(1 2 3) '(4 5))
(my-append '(1 2 3) '(4 5) '(6 7 9))
(my-append '(1 2 3))
(my-append)
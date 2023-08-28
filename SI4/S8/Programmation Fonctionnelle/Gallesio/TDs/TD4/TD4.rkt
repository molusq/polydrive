;;TD04
;;EXERCICE 1
(display "EXO1\n")

(define flat
  (lambda (lst)
    (cond
      ((not (list? lst)) (list lst))
      ((null? lst) ())
      ((pair? lst)
       (apply append (map flat lst)))
      (else (display "ERROR : flat : bad list\n")))))

(print (flat '(a (b) c)))
(newline)
(print (flat '(a (((b d (d) e) (((f))))) g (h))))
(newline)
(print (flat '(hello world)))
(newline)
(print (flat '()))
(newline)

(define depth2
  (lambda (lst dept)
    (cond
      ((not (list? lst)) dept)
      ((null? lst) 0)
      ((pair? lst)
       (map (lambda (x) (depth2 x (+ dept 1))) lst))
      (else (display "ERROR : depth : bad list\n")))))
(define depth
  (lambda (lst)
    (cond
      ((not (list? lst)) 1)
      ((null? lst) 0)
      ((pair? lst)
       (apply max (flat (depth2 lst 0))))
      (else (display "ERROR : depth : bad list\n")))))

(print (depth '(hello world)))
(newline)
(print (depth '(a (b) c)))
(newline)
(print (depth '()))
(newline)
(print (depth '(a (((b d (d) e) (((f))))) g (h))))
(newline)

;;EXO 2
(display "EXO2\n")
(define (sum term a next b)
  (if (> a b)
      0
      (+ (term a)
         (sum term
              (next a)
              next
             b))))

(define (rep1 n)
  (sum (lambda (x) x) 0 (lambda (x) (+ x 1)) (- n 1))
)

(define (rep2 n)
  (sum (lambda (x) (expt x 2)) 0 (lambda (x) (+ x 1)) (- n 1))
)

(define (rep3 a b) 
   (let ((pas  (/ 1 10000))
         (func (λ (x) (- (* x x) 1))))
     (sum (lambda (x) (* (func x) pas)) a (lambda (x) (+ x pas)) b)
))

(print (rep1 100))
(newline)
(print (rep1 20))
(newline)

(print (rep2 20))
(newline)
(print (rep2 5))
(newline)

(print (round (rep3 3 5)))
(newline)
(print (round (rep3 7 18)))
(newline)

;;EXO3
(display "EXO3\n")
(define (simple-map func lst)
  (cond
    ((null? lst) ())
    ((pair? lst)
;;     (begin
;;       (display lst)
     (append (list (func (car lst))) (simple-map func (cdr lst))))
;;     )
    (else (display "ERROR : simple-map : bad list\n"))))

(print (simple-map (lambda (x) (+ x 1)) '(0 1 2 3 4 6)))
(newline)
(print (simple-map - '(1 2 3 4 5)))
(newline)

(define (gen-map func lst . autres)
  (cond
    ((null? lst) ())
    ((pair? lst)
     (if(list? (car (car autres))) ;;Si on est dans une iteration > 1
        (append (list (apply func (append (car lst)) (simple-map car (car autres)))) (gen-map func (cdr lst) (simple-map cdr (car autres))))
        (append (list (apply func (append (car lst)) (simple-map car autres))) (gen-map func (cdr lst) (simple-map cdr autres)))))
    (else (display "ERROR : gen-map : bad list\n"))))


(print (map cons '(a b c) '(1 2 3)))
(newline)
(print (gen-map cons '(a b c) '(1 2 3)))
(newline)

(print (map max '(3 4 2) '(1 2 3) '(8 1 1)))
(newline)
(print (gen-map max '(3 4 2) '(1 2 3) '(8 1 1)))
(newline)

(print (map * '(0 1 2 3) '(4 5 6 7) '(8 9 10 11)))
(newline)
(print (gen-map * '(0 1 2 3) '(4 5 6 7) '(8 9 10 11)))
(newline)

;;EXO4
(display "EXO4\n")
(define (reduce f lst base)
  (cond
    ((null? lst) base)
    ((pair? lst)
     (f (car lst) (reduce f (cdr lst) base)))
    (else (display "ERROR : reduce : bad list\n"))))

(reduce * '(1 2 3 4 5) 1)
(reduce + '(1 2 3 4 5) 0)
(reduce (lambda (item res) (append res (list item)))
          '(1 2 3 4 5)
          '())

(define (MinAux var1 var2)
  (if(< var1 var2)
      var1
      var2))

(define (Min . lst)
  (reduce MinAux (cdr lst) (car lst)))

(print (Min 1 10 20 -7 8))
(newline)

(define (LengthAux elem nb)
  (+ nb 1))

(define (Length lst)
  (reduce LengthAux lst 0))

(print (Length '(1 2 3 4 (5 6 7))))
(newline)

(define (simple-map2 f lst)
  (reduce (lambda (elem1 elem2) (append (list (f elem1)) (if (list? elem2) elem2 (list elem2))))
          lst
          ()))

(print (simple-map2 - '(1 2 3 4)))
(newline)
(print (simple-map2 (λ (x) (* x x x)) '(1 2 3 4 5)))
(newline)

(define (Filter f lst)
  (reduce (lambda (elem1 elem2) (if (f elem1) (append (list elem1) elem2) elem2))
          lst
          ()))

(print (Filter integer? '(1 10 #f "NON" (a b 1) 12)))
(newline)
(print (Filter (lambda(x) (> x 10)) '(1 2 100 18 -7 5 48)))
(newline)

(define (contains elem lst)
  (cond ((null? lst) #f)
        ((equal? elem (car lst)) #t)
        (else (contains elem (cdr lst)))))

(define (List->set lst)
  (reduce (lambda (elem lst2)
          (if (contains elem lst2) lst2 (append (list elem) lst2)))
          lst
          ()))

(print (List->set '(1 2 3 1)))
(newline)

(define (Every? pred? lst)
  (reduce (lambda (elem res)
            (if (and (pred? elem) res) #t #f))
          lst
          #t))

(print (Every? number? '(1 2 3 6.8 0)))
(newline)
(print (Every? number? '(1 2 3 no 6.8 0)))
(newline)

;;EXO5
(display "EXO5\n")
(define (vide)           (lambda (x) #f))
(define (entiers)        (lambda (x) (integer? x)))
(define (multiple k)     (lambda (x) (zero? (modulo x k))))
(define (singleton k)    (lambda (x) (equal? x k)))
(define (intervalle m n) (lambda (x) (and (< x n) (> x m))))

(define N       (entiers))
(define Pairs   (multiple 2))
(define Impairs (lambda(x) (not (multiple 2))))
(define only-42 (singleton 42))

(define (appartient? nb f) (f nb))

(define (union f1 f2)          (lambda (x) (or (f1 x) (f2 x))))
(define (intersection f1 f2)   (lambda (x) (and (f1 x) (f2 x))))
(define (complementaire f1)    (lambda (x) (not (f1 x))))
(define (difference f1 f2)     (lambda (x) (and (f1 x) (not (f2 x)))))

(printf "42 ∈ N ⟹ ~a\n"       (appartient? 42 N))
(printf "42 ∈ ∅ ⟹ ~a\n"       (appartient? 42 (vide)))
(printf "42 ∈ Pairs ⟹ ~a\n"   (appartient? 42 Pairs))
(printf "42 ∈ Impairs ⟹ ~a\n" (appartient? 42 Impairs))
(printf "42 ∈ only-42 ⟹ ~a\n" (appartient? 42 only-42))
(printf "1 ∈ only-42 ⟹ ~a\n"  (appartient? 1 only-42))

(define fizz (multiple 3))
(define buzz (multiple 5))
(define fizz-buzz (union fizz buzz))

(printf "29 ∈ fizz-buzz ⟹ ~a\n" (appartient? 29 fizz-buzz))
(printf "30 ∈ fizz-buzz ⟹ ~a\n" (appartient? 30 fizz-buzz))


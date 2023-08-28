;;TD05
(require mzlib/defmacro)

(define-macro (macro-expand m)
  `(syntax-object->datum (expand-once ,m)))

(define-macro (macro-expand* m)
  `(syntax-object->datum (expand-to-top-form ,m)))

(define (pp form . width)
  (pretty-print-columns (if (null? width) 30 (car width)))
  (pretty-print form))


;;EXERCICE 1
(display "EXERCICE 1\n")
(define  (create-accumulator k)
  (let ((accu k))
     (λ (x)
        (set! k (+ k x)) k)))

(define a (create-accumulator 100)) ; 100 = valeur initiale acc.
(define b (create-accumulator 0))    ; 0 = valeur initiale acc.
(a 10)
(a 20)
(a 30)
(b 1)
(b 1)

;;EXERCICE 2
(display "EXERCICE 2\n")
(define  (create-accumulator2 k)
  (let ((accu k) (valIni k))
     (λ (x)
       (if (number? x)
           (begin (set! k (+ k x)) k)
           (if (equal? x 'init) 
               (begin (set! k valIni) k))))))

(define c (create-accumulator2 100))
(c 10)
(c 20)
(c 'init)
(c 10)
(c 20)

;;EXERCICE 3
(display "EXERCICE 3\n")
(define (memo f)
  (let ((a-list '()))    ;; la liste des résultats déjà connus
    (λ (x)
      (let ((previous (assoc x a-list)))
        (if previous
            (cdr previous)
            (let ((res (f x)))
              (set! a-list (append a-list (list (cons x res))))
              ;; On peut maintenant renvoyerres à l'utilisateur
              res))))))

(define (fib n) (if (< n 2) n (+ (fib (- n 1)) (fib (- n 2)))))
;;(define mfib (memo fib))
;;(mfib 34)
;;(mfib 34)
;;(mfib 32)
;;((memo fib) 35)
;;((memo fib) 33)
;;VOODOO MAGIC
(set! fib (memo fib))
(fib 1000)

;;EXERCICE 4
(display "EXERCICE 4\n")

(define-macro (prog1 e1 . en)
  `(let ((first ,e1))
    (begin ,@en)
    first))

(define l '(a b c))
(prog1 (car l) (set! l (cdr l)))
l
(prog1 (+ 2 2) (print "Hello") (print "-----"))
(prog1 'cela 'ne 'sert 'pas 'à 'grand 'chose 'mais 'pourquoi 'pas)

;;EXERCICE 5
(display "EXERCICE 5\n")
(define-macro (push! p x)
  `(set! ,p (cons ,x ,p)))

(define-macro (pop! p)
  `(let ((first (car ,p)))
     (set! ,p (cdr ,p))
     first))

(define p '())
(push! p 1)
(push! p 2)
(pop! p)
(pop! p)

;;EXERCICE 6
(display "EXERCICE 6\n")
  
(define-macro (for-all var in lst . body)
  `(if (not (null? ,lst))
   (let ((,var (car ,lst)) (truc ,@body))
      (begin
        ;;(display ,in)
        (truc ,var)
        (callForAll ,var ,lst truc)
      ))))


(define callForAll
  (lambda (var lst body)
    (begin
      (display var)
      (display lst)
      (display body)
      (newline)
      ;;(for-all var 'in (cdr lst) body)
      )))
;;(macro-expand '(for-all x in '(10 20 -17) (printf "carré de ~s = ~s\n" x (* x x))))

(for-all x in '(10 20 -17) (printf "carré de ~s = ~s\n" x (* x x)))

;;(for-all item in '((x . 10) (y . 20) (z . 30))
;;     (printf "item = ~s\n" item)
;;     (print (car item))
;;     (print (cdr item)))


;;(define-macro (for-all var in lst . body)
;;  `(if (or (not (null? ,lst))
;;   (let ((,var (car ,lst)))
;;      ,@body
;;      ;;(for-all ,var ,in (cdr ,lst) ,@body)
;;      )))
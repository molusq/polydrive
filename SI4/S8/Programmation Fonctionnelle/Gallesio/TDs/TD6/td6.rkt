;;TD06

;; Spécificités DrScheme (plus nécessaire dans les versions récentes)
(require mzlib/defmacro)

;; Un macro-expand qui correspond à celui du cours
(define-macro (macro-expand m)
  `(syntax-object->datum (expand-once ,m)))

;; Un macro-expand "récursif"
(define-macro (macro-expand* m)
  `(syntax-object->datum (expand-to-top-form ,m)))

;; Une fonction pretty-print (avec largeur optionnelle)
(define (pp form . width)
  (pretty-print-columns (if (null? width) 30 (car width)))
  (pretty-print form))

(define (fib n) (if (< n 2) n (+ (fib (- n 1)) (fib (- n 2)))))

;;EXO1
(display "EXO1\n")
(time (fib 34))

(define-macro (time2 expr)
  `(begin
     (let ((timer (current-milliseconds)) (aled ,expr))
       (printf "Elapsed time: ~ams\n" (- (current-milliseconds) timer))
       aled)))

(time2 (fib 34))

;;EXO2
(display "EXO2\n")
(define-macro (define-func params . body)
  `(define
     (,(car params) ,@(map car (cdr params)))
      ,@(map (λ (z) `(unless (,(cadr z) ,(car z)) (error ,(format "incorrect type for ~a (expected ~a)" (car z) (cadr z))))) (cdr params))
      ,@body))


(define-func (puissance (x integer?) (y integer?)) (expt x y))
(puissance 2 4)
;;(puissance 2 'a)

;;EXO3
(display "===EXO3===\n")
(define (simple-trace-function f fName)
  (lambda (x)
    (begin
      (printf "Calling (~a ~a)\n" fName x)
      (let ((res (f x)))
        (printf "Result: ~a\n" res)
         res)
      )))

;;(set! fib (simple-trace-function fib 'fib))
;;(fib 3)

(define (trace-function f fName . size)
  (begin
    (set! size 0))
    (lambda (x)
      (begin
        (printf "~a Calling (~a ~a)\n" (make-string (* 3 size) #\.) fName x)
        (set! size (+ size 1))
        (define res (f x))
        (set! size (- size 1))
        (printf "~a Result: ~a\n" (make-string (* 3 size) #\.) res)
        res)
        ))

;;(set! fib (trace-function fib 'fib))
;;(fib 3)

(define-macro (trace func)
  `(set! ,func (trace-function ,func ',func)))

(trace fib)
(fib 3)

(require racket/trace)

(define (ReversePasEfficace lst)     ;; version non efficace
  (cond
    ((null? lst) lst)
    ((pair? lst) (append (ReversePasEfficace (cdr lst))
                         (list (car lst))))
    (else (error "bad list" lst))))

(trace ReversePasEfficace)
(ReversePasEfficace '(a b c d))

;;Reverse
(display "============================== kreverse ==============================\n")
(define (kreverse lst f)
  (if (null? lst)
      (f lst)
      (kreverse (cdr lst)
             (λ (v) (f (append v (list (car lst))))))))

(trace kreverse)
(kreverse '(a b c d) (λ (x) x))
(untrace kreverse)

(kreverse '(1 2 3) print)
(newline)
(kreverse '(a b c (d e f) (g h)) print)
(newline)
(kreverse '() print)
(newline)
(kreverse '((un deux trois)) print)
(newline)
(kreverse '(1 2 3 4 5) (lambda (v) (kreverse v print)))
(newline)
(kreverse '(1 (2 3) 4 5) (lambda (v) (kreverse v print)))
(newline)
(define res #f)
(kreverse '(a b c d) (lambda(v) (set! res v)))
(print res)
(newline)

;;Fact
(display "============================== kfact ==============================\n")
(define (kfact val f)
  (if (eq? 0 val)
      (f 1)
      (kfact (- val 1)
             (λ (v) (f (* v val))))))

(kfact 10 print)
(newline)
(kfact 0 print)
(newline)
(kfact 20 print)
(newline)

;;Fib
(display "============================== kfib ==============================\n")
(define (kfib val f)
  (if (eq? 0 val)
      (f 0)
      (if (eq? 1 val)
          (f 1)
           (kfib (- val 1)
                 (λ (v) (f (+ v
                              (kfib (- val 2)
                                    (λ (v2) v2)))))))))
  

(kfib 0 print)
(newline)
(kfib 1 print)
(newline)
(kfib 25 print)
(newline)
(kfib 10 print)
(newline)

;;Append
(display "============================== kappend ==============================\n")
(define (kappend lst1 lst2 f)
  (if (null? lst1)
      (f lst2)
      (kappend (cdr lst1) lst2
               (λ (v) (f (cons (car lst1) v))))))

(kappend '(1 2 3) '(4 5 6) print)
(newline)
(kappend '(1 (2 3)) '(((4) 5) 6) print)
(newline)
(kappend '() '(a b c) print)
(newline)
(kappend '(a b c) '() print)
(newline)
(kappend '() '() print)
(newline)
(kappend '(a b c)
         '(d e f)
         (λ (v)
           (kappend v
                    '(g h i)
                    print)))
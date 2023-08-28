;;TD06_2

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

;;EXO1
(display "EXO1\n")
(define (create-card n)
  (let ((val n))
    (define decr!
      (λ()
        (set! n (- n 1))))
    (define show-count
      (λ()
        n))

    (λ (op)
      (case op
        ((decr!)
         (decr!))
        ((show-count)
         (show-count))
        (else     (error "Unknown operation: " op))))))

(define C1 (create-card 50))
(C1 'decr!)
(print (C1 'show-count))

(define (create-protected-card n password)
  (let ((val n) (real-pass password) (input-pass #f))
    (define decr!
      (λ()
        (if (equal? real-pass input-pass)
            (set! n (- n 1))
            (error "No password given"))))
    (define show-count
      (λ()
        n))
    (define password
      (λ(pass)
        (if (equal? pass real-pass)
            (set! input-pass pass)
            (error "Bad password"))))
    (define remove
      (λ()
        (set! input-pass #f)))

    (λ (op . arg)
      (case op
        ((decr!)
         (decr!))
        ((show-count)
         (show-count))
        ((password)
         (password (car arg)))
        ((remove)
         (remove))
        (else     (error "Unknown operation: " op))))))

(define C2 (create-protected-card 50 1234))
(C2 'show-count)
;;(C2 'decr!)
;;(C2 'password "bad")
(C2 'password 1234)
(C2 'decr!)
(C2 'show-count)

;;EXO2
(display "EXO2\n")
(define-macro (define-record nom list)
  `(define (,nom)
     (let ((,(car list) #f) (,(cadr list) #f))
       (λ(msg . arg)
         (case msg
           ((type)    ',nom)
           ((print)   (printf "#<~a" ',nom)
                      (printf " ~a=~s" ',(car list) ,(car list))
                      (printf " ~a=~s" ',(cadr list) ,(cadr list))
                      (printf ">\n"))
           ((get-x)   ,(car list))
           ((get-y)   ,(cadr list))
           ((set-x!)  (set! ,(car list) (car arg)))
           ((set-y!)  (set! ,(cadr list) (car arg)))
           (else      (error "Accesseur inconnu:" msg)))))))

(define (make-record nom . args)
  (let ((retour (nom)))
  (begin
    (if (equal? (length args) 2)
        (if (equal? (car args) 'x)
            (retour 'set-x! (cadr args))
            (retour 'set-y! (cadr args)))
        (if (equal? (length args) 4)
            (if (equal? (car args) 'x)
                (begin 
                  (retour 'set-x! (cadr args))
                  (retour 'set-y! (cadr (cdr (cdr args)))))
                (begin
                  (retour 'set-y! (cadr args))
                  (retour 'set-x! (cadr (cdr (cdr args))))))))
    retour)))
    

;;(pp (macro-expand '(define-record Point  (x y))))
(define-record Point (x y))

((make-record Point)              'print)
((make-record Point 'x 10)        'print)
((make-record Point 'x 10 'y 20)  'print)
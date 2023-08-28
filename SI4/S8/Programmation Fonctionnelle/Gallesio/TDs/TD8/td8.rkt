;;TD8
(load "call-cc-lib.ss")

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
(display "===============EXO1===============\n")
(define-macro (try handler . body)          ;; Non hygienic macro
  `(let ((old throw))
     (call/cc
      (λ (k)
        ;; define a new throw function
        (set! throw (λ (x)
                      (set! throw old)
                      (k (,handler x))))

        ;; execute body with the given handler
        (let ((result (begin ,@body)))
          (set! throw old)
          result)))))

(define (throw) (error "No handler installed !!!"))


(define (foo n)
  (try
   ;; ==== handler
   (λ (val)
     (case val
       ((not-integer) (printf "integer expected\n"))
       ((is-negative) (printf "positive integer expected\n"))
       (else          (printf "received unknown exception: ~s\n" val))))

   ;; ==== body
   (unless (integer? n) (throw 'not-integer))
   (printf "Result is: ~s\n" (compute-value n))))

(define (compute-value v)
  (cond
    ((zero? v)     (throw 'zero))
    ((negative? v) (throw 'is-negative))
    (else          (sqrt v))))

(foo 4)
(foo "bad")
(foo -4)
(foo 0)

(try (λ (x) (+ x 1))
     (+ 10 20 30))

(try (λ (x) (+ x 1))
     (+ 10 (throw 20) 30))

(try
   (λ (x) (printf "Handler 1 appelé avec ~s\n" x))
   (printf "Intérieur try 1\n")
   (try
    (λ (x)
      (printf "Handler 1 appelé avec ~s\n" x)
      (throw 'handler-try2))
    (printf "Intérieur try 2\n")
    (throw 'in-try2)
    (printf "Jamais affiché try2"))
   (printf "Jamais affiché try1"))

;;EXO2
(display "===============EXO2===============\n")
(define *top* #f)
(call/cc (λ (c) (set! *top* c)))
(define *user-code* #f)

(define (breakpoint . args)
  ;; Afficher les arguments
  (display "*** Breakpoint: ")
  (for-each display args)
  (newline)
  ;; stocker la continuation de breakpoint dans *user-code*
  ;; et retouner au topkevel
  (call/cc (λ (break-cont)
             (set! *user-code* break-cont)  ;; save current cont before
             (display "    Going back to toplevel\n")
             (*top*))))

(define (continue)
  (if *user-code*                   ;; ⟺ *user-code* is not #f
      (let ((k *user-code*))
        (set! *user-code* #f)
        (k))
      (error "you cannot continue anymore!")))

(define (test n)
  (let Loop ((i n)
             (sum 0))
    (if (> i 0)
        (begin
          (breakpoint "i =" i "sum =" sum)
          (Loop (- i 1) (+ sum i)))
        (printf "⟹ sum is ~s\n" sum))))


;;POUR MOODLE
(define *top* #f)
(call/cc (λ (c) (set! *top* c)))

(define *user-code* #f)

(define (breakpoint . args)
  (display "*** Breakpoint: ")
  (for-each display args)
  (newline)
  (call/cc (λ (break-cont)
             (set! *user-code* break-cont)
             (display "    Going back to toplevel\n")
             (*top* 'NEEDED-BY-BIWA-SCHEME))))
    
(define (continue)
  (if *user-code*
      (let ((k *user-code*))
        (set! *user-code* #f)
        (k 'NEEDED-BY-BIWA-SCHEME))
      (error "you cannot continue anymore!")))

;;EXO3
(display "===============EXO3===============\n")

(define-macro (on-exit expr-sortie . expr)
  `(let ((resultat (call/cc (lambda (break) ,@expr))))
     ,expr-sortie
     resultat))

(on-exit (print "Fermer le fichier ouvert...")
     (print "Ouvrir un fichier")
     (print "Travailler avec le fichier")
     ;; Pas de break, le résultat est la dernière expression (17 ici)
     17)

(newline)

(on-exit (print "Fermer le fichier ouvert...")
         (print "Ouvrir un fichier")
         (print "Travailler avec le fichier 1")
         (when (> 1 0)
           (print "Ooops problème. On sort")
           (break 'oops))
         (print "Travailler avec le fichier 2")     ;; jamais exécuté
         17)

;;EXO4
(display "===============EXO4===============\n")
(define (print-all-solutions n)
  (call/cc
   (lambda (return)
     (set! fail return)
     (print (three-dices n))
     (fail))))

(define (all-solutions n)
  (let ((res '()))
    (call/cc
     (lambda (return)
       (set! fail return)
       (set! res (cons (three-dices n) res))
       (fail)))
    res))

(print-all-solutions 8)
(newline)
(all-solutions 8)

;;EXO5
(display "===============EXO5===============\n")

(define-macro (coroutine . body)
  `(let ()
     (define state (λ (unused) ,@body))
     (define (resume c)
       (call/cc (λ (resume-point)
          (set! state (λ (unused) (resume-point 'continue)))
          (c))))
     ;; Construire le résultat
     (λ ()                            ;; 1
       (let ((x (state 'useless)))
         (set! state (λ (unused) ,@body))
         x
        ))))            ;; 2

(define A (coroutine
           (display "State 1\n")
           (resume A)
           (display "State 2\n")
           (resume A)
           (display "State 3\n")
           17))

(A)
(A)

;;EXO6
(display "===============EXO6===============\n")
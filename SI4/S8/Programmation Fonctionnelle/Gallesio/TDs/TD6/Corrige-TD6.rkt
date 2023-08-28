;;;;                                                    -*- coding: utf-8 -*-
;;;; Corrige-TD6.stk    -- CorrigÃ© de la feuille 6
;;;;
;;;;           Author: Erick Gallesio [eg@essi.fr]
;;;;    Creation date: 28-May-2003 13:18 (eg)
;;;; Last file update: 18-Mar-2021 12:37 (eg)
;;;;


;; Racket stuff
(require mzlib/defmacro)

(define-macro (macro-expand m)
  `(syntax-object->datum (expand-once ,m)))

(define (pp form . width)
  (pretty-print-columns (if (null? width) 30 (car width)))
  (pretty-print form))

;; =============================================================================
;;                                       EXO 1
;; =============================================================================
(define-macro (time expr)
  `(let* ((start (current-milliseconds))
          (val   ,expr))
     ;; Afficher le message
     (printf "Elapsed time: ~ams\n" (- (current-milliseconds) start))
     ;; Renvoyer le rÃ©sultat
     val))

;; Pour la forme: une version hygiÃ©nique de la macro prÃ©cÃ©dente
(define-macro (time expr)
  (let ((start (gensym))
        (val   (gensym)))
    `(let* ((,start (current-milliseconds))
            (,val   ,expr))
       ;; Afficheer le message
       (printf "Elapsed time: ~ams\n" (- (current-milliseconds) start))
       ;; Renvoyer le rÃ©sultat
       ,val)))

;; =============================================================================
;                                       EXO 2
;; =============================================================================

;; Une premiÃ¨re version qui ne produit pas les tests
(define-macro (define-func proto . body)
  (let ((name (car proto))
        (args (cdr proto)))
    `(define (,name ,@(map car args))
       ,@body)))

;; La version finale
(define-macro (define-func proto . body)
  (let ((name (car proto))
        (args (cdr proto)))
    `(define (,name ,@(map car args))
       ;; Insertion des contrÃ´les de type
       ,@(map (Î» (x)
                `(unless ,(reverse x)
                   (error ,(format "incorrect type for ~a (expected ~a)"
                                   (car x) (cadr x)))))
              args)
       ;; Corps de la fonction
       ,@body)))

#|
Test
 (pp (macro-expand '(define-func (puissance (x number?) (y number?))
                        (let loop ((p 1) (y y))
                           (if (> y 0)
                              (loop (* p x) (- y 1))
                               p)))))

 ==>

(define (puissance x y)
  (if (not (number? x))
    (error "type incorrect pour" 'x))
  (if (not (number? y))
    (error "type incorrect pour" 'y))
  (let loop ((p 1) (y y))
    (if (> y 0) (loop (* p x) (- y 1)) p)))



> (define-func (puissance (x integer?) (y integer?))
     (expt x y))
> (puissance 2 4)
16
> (puissance 2 'a)
ERROR: incorrect type for y (expected integer?)

> (define-func (make-person (name string?) (age integer?) (mark real?))
    `((name ,name) (age , age) (mark , mark)))
> (make-person "Alice" 42 17.2)
((name "Alice") (age 42) (mark 17.2))
> (make-person 'Bob 50 14.5)
ERROR: incorrect type for name (expected string?)
|#


;; =============================================================================
;                                       EXO 3
;; =============================================================================

;;; ========== Version 1 (sans indentation) ==========
(define (simple-trace-function func name)
  (Î» l
    (printf "Calling ~a\n" `(, name ,@l))
    (let ((res (apply func l)))
      (printf "Result: ~a\n" res)
      res)))


;;; ========== Version 2 (avec indentation) ==========
(define (trace-function func name)
  (let ((n 0))
    (lambda l
      (printf "~a Calling ~a\n" (make-string n #\.) `(, name ,@l))
      (set! n (+ n 3))
      (let ((res (apply func l)))
        (set! n (- n 3))
        (printf "~a Result: ~a\n" (make-string n #\.) res)
        res))))

;; ========== Version 3 (avec untrace) ==========
(define *traced-func* '())   ; Une A-list des fonction tracÃ©es ou la clÃ©
                             ; est nom de la fonction et la valeur
                             ; associÃ©e est la fonction non tracÃ©e

;; TRACE
(define (trace-function name func)
  (let ((n 0))
    ;; Stocker la fonction avant trace
    (set! *traced-func* (cons (cons name func) *traced-func*))
    ;; Renvoyer la fonction tracÃ©e
    (lambda l
      (let ((res #f))
        (printf "~a Calling ~a\n" (make-string n #\.) `(, name ,@l))
        (set! n   (+ n 3))
        (set! res (apply func l))
        (set! n   (- n 3))
        (printf "~A Result: ~A\n" (make-string n #\.) res)
        res))))

(define-macro (trace f)
  `(set! ,f (trace-function ,f ',f)))

;; UNTRACE
(define (untrace-function name)
  (let ((old (assoc name *traced-func*)))
    (if old
        (cdr old)
        (error "function is not traced"))))

(define-macro (untrace f)
  `(set! ,f (untrace-function ',f)))



;;; ========== Version 4 (avec une pseudo static) ==========
(define trace-function   #f)
(define untrace-function #f)

;; DÃ©finition des 2 fonctions qui capturent la A-list traced
(let ((traced-func '()))
  (set! trace-function
    (Î» (func name)
      (let ((n 0))
        ;; Stocker la fonction avant trace
        (set! traced-func (cons (cons name func) traced-func))
        ;; Renvoyer la fonction tracÃ©e
        (lambda l
          (printf "~a Calling ~a\n" (make-string n #\.) `(, name ,@l))
          (set! n (+ n 3))
          (let ((res (apply func l)))
            (set! n (- n 3))
            (printf "~a Result: ~a\n" (make-string n #\.) res)
            res)))))

  (set! untrace-function
    (Î» (name)
      (let ((old (assoc name traced-func)))
        (if old
            (cdr old)
            (error "function is not traced"))))))

;; DÃ©finition des macros trace et untrace
(define-macro (trace f)
  `(set! ,f (trace-function ,f ',f)))

(define-macro (untrace f)
  `(set! ,f (untrace-function ',f)))


;; =============================================================================
;;                                       EXO 4
;; =============================================================================

;; Une expansion possible pour le let nommÃ© suivant
;;
;;    (let Loop ((i 0))
;;      (when (< i 10)
;;         (display i)
;;         (Loop (+ i 1))))
;;
;; ==>
;;    (letrec ((Loop (lambda (i)
;;                     (when (< i 10)
;;                       (display i)
;;                       (Loop (+ i 1))))))
;;      (Loop 0))

;; On commence avec une macro spcifique "named-let"
(define-macro (named-let name bindings . body)
    `(letrec ((,name (Î» ,(map car bindings) ,@body)))
       (,name ,@(map cadr bindings))))

#|
(named-let Loop ((i 0))
   (when (< i 10)
      (display i)
      (Loop (+ i 1))))
|#

;;
;; Un let qui intÃ¨gre  let normal et let nommÃ©
;;

(define-macro (let bindings . body)
  (if (pair? bindings)
      ;; On a un let "normal" (cf cours)
      `( (Î» ,(map car bindings) ,@body) ,@(map cadr bindings))
      ;; let nommÃ©
      `(named-let ,bindings ,(car body) ,@(cdr body))))

;;
;; Tout dans une seule macro
;;
(define-macro (let bindings . body)
  (if (pair? bindings)
      ;; On a un let "normal"
      `( (Î» ,(map car bindings) ,@body) ,@(map cadr bindings))
      ;; let nommÃ©
      (let ((name     bindings)
            (bindings (car body))
            (body     (cdr body)))
         `(letrec ((,name (Î» ,(map car bindings) ,@body)))
             (,name ,@(map cadr bindings))))))

;; =============================================================================
;;                                       EXO 5
;; =============================================================================

(define (make-hook where hook fct)
  (case where
    ((before) (Î» args
                (apply hook args)
                (apply fct args)))
    ((after) (Î» args
                (let ((res (apply fct args)))
                  (apply hook res args))))
    ((around) (Î» args
                (apply hook fct args)))
    (else     (error "WHERE is incorrect"))))


#|
;; Before Hook
> (define my-sqrt (make-hook 'before
                             (Î» (args) (printf "Call sqrt with ~a\n" args))
                             sqrt))
> (my-sqrt 10)
Call sqrt with 10
3.16227766016838

;; After Hook
> (define my-sin (add-hook 'after
                           (Î» (res . args) (abs res))
                           sin))

> (print (my-sin (+ (/ 3.14156 2))))  ;; rÃ©sultat toujours â‰¥ 0
0.9999999998667178
> (print (my-sin (- (/ 3.14156 2))))
0.9999999998667178
|#
;; Two after hooks
(define my-cos (make-hook 'after
                          (Î» (res . args) (abs res))
                          (make-hook 'after
                                     (Î» (res . args)
                                       (printf "First hook res = ~a\n" res)
                                       res)
                                     cos)))































;=============================================================================
;                                       EXO 3
;=============================================================================
(define (creer-carte val)
  (lambda (msg . args)
    (case msg
      ((decrementer!)   (if (= val 0) (error "Plus de credit"))
                        (set! val (- val 1)))
      ((afficher-unites) val)
      (else             (error "Message inconnu" msg)))))

;;
;; (define C1 (creer-carte 50))
;; (C1 'decrementer!)
;; (C1 'afficher-unites)
;;

(define (creer-carte-protegee val pass)
  (let ((mot-de-passe-ok #f)
        (carte           (creer-carte val)))
    (lambda (msg . args)
      (case msg
        ((password)     (if (equal? (car args) pass)
                            (set! mot-de-passe-ok #t)
                            (error "Mot de passe erronÃ©")))
        ((decrementer!) (if mot-de-passe-ok
                            (apply carte msg args)
                            (error "Vous n'avez pas donnÃ© de mot de passe")))
        ((raccrocher)   (set! mot-de-passe-ok #f) 'Salut)
        (else           (apply carte msg args))))))

;; (define C2 (creer-carte-protegee 50 1234))
;; (C2 'afficher-unites)
;; (C2 'password 12)
;; (C2 'password 1234)
;; (C2 'decrementer!)
;; (C2 'raccrocher)



;=============================================================================
;                                       EXO 3
;=============================================================================

;;PremiÃ¨re version (pas hygiÃ©nique et code pas terrible)
(define-macro (for variation . body)
  (let ((var    (car variation))
        (depart (cadr variation))
        (fin    (caddr variation))
        (pas    (if (= (length variation) 4)
                    (cadddr variation)
                    1)))
    ;; Attention le test du signe du pas doit se faire dans le letrec
    ;; et non pas avant car dans le cas gÃ©nÃ©ral (si le pas n'est pas
    ;; une constante) le test sur le signe du pas ne peut pas Ãªtre
    ;; fait.
    `(letrec ((loop (lambda (,var)
                      (if (> ,pas 0)
                          (unless (>= ,var ,fin)
                            ,@body
                            (loop (+ ,var ,pas)))
                          (unless (<= ,var ,fin)
                            ,@body
                            (loop (+ ,var ,pas)))))))
       (loop ,depart))))

;;(for (i 0 10) (print i))
;;(for (i 10 0 -3) (print i))


;; Test
;; (macro-expand '(for (i 0 100 (+ a b))))
;; ==>
;;      (letrec ((loop (lambda (i)
;;                       (if (> (+ a b) 0)
;;                         (unless (>= i 100) (loop (+ i (+ a b))))
;;                         (unless (<= i 100) (loop (+ i (+ a b))))))))
;;        (loop 0))



;; On peut factoriser le test sur le pas pour Ã©viter les 2 unless
;; quasi identiques (seul l'opÃ©ratuer de comparaison change). On obtient

(define-macro (for variation . body)
  (let ((var    (car variation))
        (depart (cadr variation))
        (fin    (caddr variation))
        (pas    (if (= (length variation) 4)
                    (cadddr variation)
                    1)))
    `(letrec ((loop (lambda (,var)
                       (unless ((if (> ,pas 0) >= <=) ,var ,fin)
                         ,@body
                         (loop (+ ,var ,pas))))))
       (loop ,depart))))


;; L'ajout de l'hygiÃ¨ne n'est pas difficile (et utile si un des
;; paramÃ¨tres s'appelle loop)
(define-macro (for variation . body)
  (let ((var    (car variation))
        (depart (cadr variation))
        (fin    (caddr variation))
        (pas    (if (= (length variation) 4)
                    (cadddr variation)
                    1))
        (loop   (gensym)))
    `(letrec ((,loop (lambda (,var)
                       (unless ((if (> ,pas 0) >= <=) ,var ,fin)
                         ,@body
                         (,loop (+ ,var ,pas))))))
       (,loop ,depart))))

;; Test
;; (macro-expand '(for (i 0 100 (+ a b))))
;; ==> (letrec ((|G454| (lambda (i)
;;                        (unless ((if (> (+ a b) 0) >= <=) i 100)
;;                           (|G454| (+ i 5 (+ a b)))))))
;;        (|G454| 0))
;;
;; Ici |G454| est les symbole construit par gensym



;=============================================================================
;                                       EXO 5
;=============================================================================

;; Pas de correction


;=============================================================================
;                                       EXO 7
;=============================================================================

;; Une expansion possible pour l'utilisation suivante de lambda*
;;    (define plus
;;      (lambda*
;;       (()    => 0)
;;       ((a)   => a)
;;       ((a b) => (+ a b))))
;; pourrait Ãªtre

(define plus
  (lambda lst
    (case (length lst)
      ((0) (apply (lambda () 0)     lst))
      ((1) (apply (lambda (a) a)         lst))
      ((2) (apply (lambda (a b) (+ a b)) lst))
      (else (error "pas de corps pour une liste de longueur"
                   (length lst))))))

;;; ou encore, de faÃ§on plus fonctionnelle:
(define plus
  (lambda lst
    (apply (case (length lst)
             ((0) (lambda () 0))
             ((1) (lambda (a) a))
             ((2) (lambda (a b) (+ a b)))
             (else (error "pas de corps pour une liste de longueur"
                          (length lst))))
           lst)))
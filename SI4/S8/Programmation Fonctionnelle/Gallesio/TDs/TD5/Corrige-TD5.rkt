;;;;                                                    -*- coding: utf-8 -*-
;;;; Corrigé.stk        -- Corrigés de la feuille 5
;;;;
;;;;           Author: Erick Gallesio [eg@unice.fr]
;;;;    Creation date: 28-Feb-2000 09:06 (eg)
;;;; Last file update: 17-Mar-2021 17:03 (eg)
;;;;

;; Spécificités DrScheme
(require mzlib/defmacro)

;; Un macro-expand qui correspond �  celui du cours
(define-macro (macro-expand m)
  `(syntax-object->datum (expand-once ,m)))

;; Un macro-expand "récursif"
(define-macro (macro-expand* m)
  `(syntax-object->datum (expand-to-top-form ,m)))

;; Une fonction pretty-print (avec largeur optionnelle)
(define (pp form . width)
  (pretty-print-columns (if (null? width) 30 (car width)))
  (pretty-print form))


(define-macro (when cond . body)
  `(if ,cond (begin ,@body)))

(define-macro (unless cond . body)
  `(when (not ,cond) ,@body))


;;=============================================================================
;;                                       EXO 1
;;=============================================================================

(define (create-accumulator k)
  (let ((accu k))
    (λ (x)
      (set! accu (+ accu x))
      accu)))

;;Autre version (sans let)
;; En effet, le let n'est pas obligatoire car on peut capturer le
;; paramètre de la fonction
(define (create-accumulator accu)
  (λ (x)
    (set! accu (+ accu x))
    accu))

#|
(define a (create-accumulator 100))
(a 10)
(a 20)
(a 30)
|#

;;=============================================================================
;;                                       EXO 2
;;=============================================================================
(define (create-accumulator k)
  (let ((accu k))
    (λ (x)
      (if (eq? x 'init)
          (set! accu k)
          (set! accu (+ accu x)))
      accu)))

;; Un écriture un peu plus fonctionnelle (± comme on le ferait en C avec
;; l'opérateur ternaire)

(define (create-accumulator k)
  (let ((accu k))
    (λ (x)
      (set! accu (if (eq? x 'init) k (+ accu x))))
    accu))

;;=============================================================================
;;                                       EXO 3
;;=============================================================================

;; Version pour des fonctions d'arité 1
(define (memo f)
  (let ((a-list '()))
    (λ (x)
      (let ((previous (assoc x a-list)))
        (if previous
            (cdr previous)
            (let ((res (f x)))
              (set! a-list (cons (cons x res) a-list))
              res))))))

;; Version génerale (pour des fonctions d'arité quelconque)
(define (memo f)
  (let ((a-list '()))
    (λ l
      (let ((previous (assoc l a-list)))
        (if previous
            (cdr previous)
            (let ((res (apply f l)))
              (set! a-list (cons (cons l res) a-list))
              res))))))

#|
(define  (fib n) (if (< n 2) 1 (+ (fib(- n 1))(fib (- n 2)))))
(define mfib (memo fib))
(mfib 25)

;; Encore plus fort !!!!!!!
(set! fib (memo fib))

(fib 1000) ;; et c'est instantané!! Ce qui est important de voir ici c'est
           ;; qu'on a rendu une fonction "impraticable" en une fonction
           ;; utilisable pour de grandes valeurs de n. Ce qu'on a fait, c'est
           ;; qu'on a changé les règles d'évaluation d'une fonction (i.e. si le
           ;; résultat est connu on ne le reconstruit pas, on utilise la valeur
           ;; que l'on a retenue). Quant �  la fonction de l'utilisateur (fib ici)
           ;; elle n'a pas été changée (i.e. c'est toujours la jolie fonction
           ;; avec ses deux appels récursifs).
|#

;;=============================================================================
;;                                       EXO 4
;;=============================================================================

(define-macro (prog1 prems . reste)
  `(let ((tmp ,prems))
     ,@reste
     tmp))

;; Si on veut éviter les problème d'hygiène, voil�  une version plus propre...
(define-macro (prog1 prems . reste)
  (let ((tmp (gensym)))
    `(let ((,tmp ,prems))
       ,@reste
       ,tmp)))

;;=============================================================================
;;                                       EXO 5
;;=============================================================================
(define-macro (push! l v)
  `(set! ,l (cons ,v ,l)))

(define-macro (pop! l)
  `(prog1 (car ,l) (set! ,l (cdr ,l))))

#| TEST
        (define s '())
        (push! s 1)
        (push! s 2)
        (pop!  s)    ; => 2
        (pop!  s)    ; => 1
|#

;;=============================================================================
;;                                       EXO 6
;;=============================================================================

(define-macro (for-all var in lst . body)
  ;; "in" ne sert �  rien. Il est juste l�  pour fair joli
  `(for-each (λ (,var) ,@body) ,lst))

(define-macro (for-all var in lst . body)
  ;; Une version où l'on vérifie que le second paramètre est bien le symbole 'in
  (unless (eq? in 'in)
    (error "second parameter must be 'in'"))
  `(for-each (λ (,var) ,@body) ,lst))

#|
(for-all x in '(10 20 30 8)
    (printf "carré de ~s = ~s\n" x (* x x)))

(for-all item in '((x . 10) (y . 20) (z . 30))
         (print item)
         (print (car item))
         (print (cdr item)))
|#


;;=============================================================================
;;                                       EXO 7
;;=============================================================================

;; while avec un letrec
(define-macro (while expr . body)
  `(letrec ((Loop (λ ()
                    (when ,expr
                      ,@body
                      (Loop)))))
     ;; Faire le premier appel pour démarrer la boucle
     (Loop)))


;; Version tenant compte de l'hygiène
(define-macro (while expr . body)
  (let ((Loop (gensym)))
    `(letrec ((,Loop (λ ()
                       (when ,expr
                         ,@body
                         (,Loop)))))
       (,Loop))))
#|
(let ((i 0))
  (while (< i 5)
     (print i)
     (set! i (+ i 1))))


;; Deux boucles imbriquées
(let ((i 0))
  (while (< i 3)
    (let ((j 0))
      (while (< j 4)
         (printf "i=~S et j=~s\n" i j)
         (set! j (+ j 1)))
      (set! i (+ i 1)))))

|#
;;=============================================================================
;;                                       EXO 8
;;=============================================================================
(define-macro (case expr . clauses)     ;; On utilise pas gensym ici pour simplifier
  `(let ((tmp ,expr))
     (cond
       ,@(map (λ (clause)
                (if (eq? (car clause) 'else)
                    clause
                    `((memv tmp ',(car clause)) ,@(cdr clause))))
              clauses))))

#|
(pp (macro-expand '(case (+ 2 3)
                     ((1 2 3 4) 'petit)
                     ((5 6 7 8) 'moyen)
                     (else      'grand))))
|#


;; La version higiénique:
(define-macro (case expr . clauses)
  (let ((tmp (gensym)))
    `(let ((,tmp ,expr))
       (cond
         ,@(map (λ (clause)
                  (if (eq? (car clause) 'else)
                      clause
                      `((memv ,tmp ',(car clause)) ,@(cdr clause))))
                clauses)))))
;;=============================================================================
;                                       EXO 9
;;=============================================================================
(define-macro (for bindings . body)
  (let ((var     (car bindings))
        (depart  (cadr bindings))
        (fin     (caddr bindings))
        (pas     (cadddr bindings))
        (loop    (gensym "Loop")))
    `(letrec ((,loop (λ (,var)
                       (unless (= ,var ,fin)
                         ,@body
                         (,loop (+ ,var ,pas))))))
       (,loop ,depart))))

#|
(for (i 5 0 -1) (display i) (newline))    ;; OK
(for (i 5 0 -2) (display i) (newline))    ;; Problème, car on «loupe» la fin
|#

;; Version correcte
(define-macro (for bindings . body)
  (let ((var     (car bindings))
        (depart  (cadr bindings))
        (fin     (caddr bindings))
        (pas     (cadddr bindings))
        (loop    (gensym "Loop")))
    `(let ((comp  (if (< ,pas 0) <= >=)))
       (letrec ((,loop (λ (,var)
                         (unless (comp ,var ,fin)
                           ,@body
                           (,loop (+ ,var ,pas))))))
         (,loop ,depart)))))
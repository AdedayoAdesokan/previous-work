#lang racket
;a helper function that creates a new list given a list and an item
(define (make-list lsts item)
    (if (empty? lsts)
        item
        (append lsts item)))

;the given function was not tail recursive because the last line was not a call to the function, but rather a cons list
;this new implementation utilizes tail recursion
(define (my-map fn elems lst)
  (if (empty? elems)  ;if the list of elements is empty
      lst  ;return the created list 
      (my-map fn (rest elems) (make-list lst (list (fn (first elems)))))))  ;else, call my-map on the rest of the elements

;test cases
(define test1 (my-map sqrt (list 1 4 9 16 25 36 49 64 81 100) '()))
(define test2 (my-map (lambda (x) (expt x 2)) (list 1 2 3 4 5) '()))
(define test3 (my-map (lambda (x) (+ (* x 0) 1)) (list 10 20 30 40 50) '()))

;-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

;a helper function that gets the left argument of the given expression
(define (get-left-arg expr)
      (cadr expr))

;a helper function that gets the right argument of the given expression
(define (get-right-arg expr)
    (caddr expr))

;a helper function that converts a list of strings to a single string
(define (string->append strng)
  (define (traverse strng output)
    (if (empty? strng)  ;if the list is empty, return the output 
        output
        (if (empty? (cdr strng))  ;if this is the last element in the list
            (traverse (cdr strng) (string-append output (car strng)))  ;traverse the rest of the list and add the last element to the sting without a space
            (traverse (cdr strng) (string-append output (car strng) " ")))))  ;else, traverse the rest of the list and add the current element to the sting with a space
  (traverse strng ""))  ;begin traversal with an empty string

;a helper method that cleans up a deeply nested list
(define (clean-up expr)
  (if (empty? expr)  ;if the expr is empty
      (list)  ;return an empty list
      (if (pair? expr)  ;if the expr is a pair
          (append (clean-up (first expr)) (clean-up (rest expr)))  ;use recursion to append the first and the rest of the expr
          (list expr))))  ;else list the expr

;creates a string of the arithmetic expression in postfix notation
(define (to-postfix expr)
  (define (evaluate expr)  ;evaluates the given epression
    (if (number? expr)  ;if the expression is a number
      (number->string expr)  ;convert the number to a string and return it
      (list (evaluate (get-left-arg expr)) (evaluate (get-right-arg expr)) (symbol->string (car expr)))))  ;else, list the evaluate of the left and right arguments followed by the operator
  (string->append (clean-up (evaluate expr))))  ;convert the evaluated list into a single string
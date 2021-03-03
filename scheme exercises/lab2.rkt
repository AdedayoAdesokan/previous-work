#lang racket

;a helper method that determines if the given number is divisible by 3
(define (div3 x)
  (if (= (remainder x 3) 0)  
      #t    ;if the number is divisible by 3, return true
      #f))  ;else return false

;a helper method that determines if the given number is divisible by 5
(define (div5 x)
  (if (= (remainder x 5) 0)
      #t    ;if the number is divisible by 5, return true
      #f))  ;else return false

;a helper method that determines if the given number is divisible by both 3 and 5
(define (both x)
  (if (and (div3 x) (div5 x))
      #t    ;if the number is divisible by 5 and 3, return true
      #f))  ;else return false

;outputs numbers 1 to 100
;if a number is divisible by 3, “fizz” is printed instead of the number
;if a number is divisible by 5, “buzz” is printed instead of the number
;if a number is divisible by both 3 and 5, “fizzbuzz” is printed instead of the number
(define (fizz-buzz)  
  (letrec ((count (lambda (x)
                  (when (<= x 100)  ;iterate until 100
                    (if (both x)  ;evaluate the number with the helper function 
                        (displayln "fizzbuzz")  ;print the appropriate word
                        (if (div5 x)  ;evaluate the number with the helper function 
                            (displayln "buzz")  ;print the appropriate word
                            (if (div3 x)  ;evaluate the number with the helper function 
                                (displayln "fizz")  ;print the appropriate word
                                (displayln x))))  ;else print the number
                    (count (+ x 1))))))  ;increment the counter
  (count 1)))  ;begin at 1

  
;------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


;a helper method that determines if the given numbers are negative
(define (isnegative m n)
  (if (or (< m 0) (< n 0))
      #t
      #f))

;a helper method that converts the given number to positive if it is negative
(define (convert x)
  (if (< x 0)
      (* -1 x)
      x))
      
;a helper method that determines the state of the given numbers
(define (getstate m n)
  (if (or (= m 0) (= n 0))  ;state 0 occurs when at least one of the numbers is zero
      0
      (if (= m n)  ;state 1 occurs when both numbers are equal to each other
          1
          (if (> m n)  ;state 2 occurs if m > n
              2
              3))))  ;state 3 occurs if n > m

;computes the greatest common denominator of m and n
(define (my-gcd m n)
  (if (isnegative m n)  ;checks if either number is negative
      (my-gcd (convert m) (convert n))  ;convert the negative number and recalculate the gcd
      (if (= (getstate m n) 0)  ;if m or n is zero, return 0
          0
          (if (= (getstate m n) 1)  ;if m and n are equal, return m
              m
              (if (= (getstate m n) 2) ;if m is greater than n
                  (my-gcd (- m n) n)  ;subtract n from m and recalculate
                  (my-gcd m (- n m)))))))  ;if n is greater than m, subtract and recalculate


;------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


;performs a linear search on the list items, determining whether key is among the items
(define (linear-search items key)
  (let loop ((subitems items))  ;begin the loop with the given list
    (if (null? subitems)  ;if the list is empty return false
        #f
        (if (= (first subitems) key)  ;if the first number is equal to the key, return true
            #t
            (loop (rest subitems))))))  ;else increment search the rest


;------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


;a helper function that deletes a single number from a list
(define (del-help items key)
  (let loop ((i 0) (subitems items))  ;begin the loop at zero with the given list
    (if (null? subitems)  ;if the list is empty return false
        subitems
        (if (= (first subitems) key)  ;if the first number is equal to the key, return the rest of the list
            (rest subitems)
            (cons (first subitems) (loop (+ i 1) (rest subitems)))))))  ;else increment i and search the rest

;returns a new list that does not have any elements that match the key
(define (my-remove items key)  ;accepts two arguments: items (a list) and key
  (if (linear-search items key)  ;utilize linear-search to see if the given list contains the key
      (my-remove (del-help items key) key)  ;if key is present, delete the key and use recursion to check if there is another occurrence of the key
      (del-help items key)))  ;else use the helper function to return the list 


;------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

;a helper function that creates a node made of a single item
(define (make-1node item)
  (list item))

;a helper function that creates a node with only 2 items
(define (make-2node item left-bst)
  (list item left-bst))

;a helper function that 
(define (make-3node item left-bst right-bst)
  (list item left-bst right-bst))

;a helper function that determines if the bst is terminal
(define (is-1node? bst)
  (if (= (length bst) 1)
      #t
      #f))

;a helper function that determines if the bst only has 1 left child
(define (is-2node? bst)
  (if (= (length bst) 2)
      #t
      #f))

;a helper function that determines if the bst has 2 children
(define (is-3node? bst)
  (if (= (length bst) 3)
      #t
      #f))

;;a helper function that gets the value of the bst
(define (value bst)
  (first bst))

;a helper function that gets the left child of the bst
(define (left bst)
  (when (or (is-2node? bst) (is-3node? bst))  ;ensure that the bst has a left child
      (first (rest bst))))

;a helper function that gets the right child of the bst
(define (right bst)
  (when (is-3node? bst)  ;ensure that the bst has 2 children
      (first (rest (rest bst)))))

;a helper function that determines if you need to traverse the left child
(define (go-left? item bst)
  (if (< item (value bst))  ;if the given item is less than the value of the bst, return true
      #t
      #f))

;inserts the given item into the given bst
(define (add-to-binary-search-tree bst item) 
  (if (search-binary-search-tree bst item)  ;utilize the search bst function to determine if the item is already in the bst
      bst  ;return the existing bst if the item is already present
      (if (is-1node? bst)  ;if the bst is terminal
          (if (go-left? item bst)  ;if the item needs to be placed  in the left subtree
              (make-2node (value bst) (list item))  ;make the item the left child
              (make-3node (value bst) (list) (list item)))  ;else make the item the right child
          (if (is-2node? bst)  ;if the bst only has a left child
              (if (go-left? item bst)  ;if the item needs to be placed  in the left subtree
                  (make-2node (value bst) (add-to-binary-search-tree (left bst) item))  ;use recursion to determine the placement of the item
                  (make-3node (value bst) (left bst) (list item)))  ;else make the item the right child
              (if (is-3node? bst)  ;if the bst has two children
                  (if (go-left? item bst)  ;if the item needs to be placed  in the left subtree
                      (make-3node (value bst) (add-to-binary-search-tree (left bst) item) (right bst))  ;use recursion to determine the placement of the item
                      (make-3node (value bst) (left bst) (add-to-binary-search-tree (right bst) item)))  ;use recursion to determine the placement of the item
                  (make-1node item))))))  ;if the bst is empty, make the item into a terminal node


;------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

;creates a bst from a given list
(define (create-binary-search-tree items)  ;items = the list of items to be inserted into the binary search tree
  (define (traverse items bst)  ;create local function that takes a list of items and a bst
    (if (empty? items)  ;if the given list is empty
        bst  ;return the bst
        (traverse (rest items) (add-to-binary-search-tree bst (first items)))))  ;recursively traverse the rest of the list using the add-to-bst function to create a new bst for each item
  (traverse items (list)))  ;call traverse on the given list with an empty bst


;------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

;searches the given bst for the key, returns true if found
(define (search-binary-search-tree bst key)  ;key = the item to search for in the binary search tree
  (if (empty? bst)  ;if the bst is empty, return false
      #f
      (if (is-1node? bst)  ;if the bst is terminal
          (if (= (value bst) key)  ;if the value is equal to the key, return true
              #t
              #f)
          (if (is-2node? bst)  ;if the bst only has a left child
                  (if (= (value bst) key)  ;if the value is equal to the key, return true
                      #t
                      (if (< key (value bst))  ;if the key is less than the value
                          (search-binary-search-tree (left bst) key)  ;search the left subtree for the key
                          #f))  ;else return false
                  (when (is-3node? bst)  ;if the bst has 2 children
                      (if (= (value bst) key)  ;if the value is equal to the key, return true
                          #t
                          (if (< key (value bst))  ;if the key is less than the value
                             (search-binary-search-tree (left bst) key)  ;search the left subtree for the key
                             (search-binary-search-tree (right bst) key))))))))  ;else search the left subtree for the key
              

;------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

;a helper method that cleans up a deeply nested list
(define (clean-up bst)
  (if (empty? bst)  ;if the bst is empty
      (list)  ;return an empty list
      (if (pair? bst)  ;if the bst is a pair
          (append (clean-up (first bst)) (clean-up (rest bst)))  ;use recursion to append the first and the rest of the bst
          (list bst))))  ;else list the bst

;creates a list of the given bst in the specified order
(define (binary-search-tree-to-list bst traversal)  ;traversal = symbol that indicates the traversal method
  (if (eqv? traversal 'preorder)  ;if the symbol is preorder
      (if (empty? bst)  ;if the bst is empty
          (list)  ;return an empty list
          (if (is-1node? bst)  ;if the bst is terminal
              (list (value bst))  ;return the value of the bst as a list
              (if (is-2node? bst)  ;if the bst only has a left child
                  (clean-up (list (value bst) (binary-search-tree-to-list (left bst) 'preorder)))  ;list the bst in preorder orientation
                  (when (is-3node? bst)  ;if the bst has two children
                    (clean-up (list (value bst) (binary-search-tree-to-list (left bst) 'preorder) (binary-search-tree-to-list (right bst) 'preorder))))))) ;list the bst in preorder

      (if (eqv? traversal 'inorder)  ;if the symbol is inorder
          (if (empty? bst)  ;if the bst is empty
          (list)  ;return an empty list
          (if (is-1node? bst)  ;if the bst is terminal
              (list (value bst))  ;return the value of the bst as a list
              (if (is-2node? bst)  ;if the bst only has a left child
                  (clean-up (list (binary-search-tree-to-list (left bst) 'inorder) (value bst))) ;list the bst in inorder orientation
                  (when (is-3node? bst)  ;if the bst has two children
                    (clean-up (list (binary-search-tree-to-list (left bst) 'inorder) (value bst) (binary-search-tree-to-list (right bst) 'inorder)))))))  ;list the bst in inorder

         (when (eqv? traversal 'postorder)  ;if the symbol is postorder
           (if (empty? bst)  ;if the bst is empty
               (list)  ;return an empty list
               (if (is-1node? bst)  ;if the bst is terminal
                (list (value bst))  ;return the value of the bst as a list
                (if (is-2node? bst)  ;if the bst only has a left child
                    (clean-up (list (binary-search-tree-to-list (left bst) 'postorder) (value bst)))  ;list the bst in postorder orientation
                    (when (is-3node? bst)  ;if the bst has two children
                      (clean-up (list (binary-search-tree-to-list (left bst) 'postorder) (binary-search-tree-to-list (right bst) 'postorder) (value bst)))))))))))  ;list bst in postorder

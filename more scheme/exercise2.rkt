#lang racket
;a helper function that creates a new list given a list and an item
(define (make-list lsts item)
    (if (empty? lsts)  ;if the list is empty
        item  ;return the item
        (append lsts item)))  ;else, append the item to the list

;a helper function that sets the initial distances of each vertex to infinity
(define (initialize-distances vertices)
  (define (traverse vertices distances)  ;traverses over the list of vertices and sets their distances to infinity
    (if (empty? vertices)  ;if vertices is empty
        distances  ;return distances
        (traverse (rest vertices) (make-list distances (list (cons (first vertices) 'infinity))))))  ;else, traverse the rest of the vertices
  (traverse vertices '()))  ;call traverse on the given list with an empty list of distances

;a helper function that sets the previous vertex of each vertex to undefined 
(define (initialize-previous-vertices vertices)  
  (define (traverse vertices prev-vertices)  ;traverses the list of vertices and sets their previous vertex to undefined
    (if (empty? vertices)  ;if vertices is empty
        prev-vertices  ;return prev-vertices
        (traverse (rest vertices) (make-list prev-vertices (list (cons (first vertices) 'undefined))))))  ;else, traverse the rest of the vertices
  (traverse vertices '()))  ;call traverse on the given list with an empty list of previous vertices

;a helper function that creates the list of unvisited nodes
(define (initialize-unvisited-vertices vertices)
  (define (traverse vertices unvisited-vertices)  ;creates a copy of the list of vertices
    (if (empty? vertices)  ;if vertices is empty
        unvisited-vertices  ;return unvisited-vertices
        (traverse (rest vertices) (make-list unvisited-vertices (list (first vertices))))))  ;else, traverse the rest of the vertices
  (traverse vertices '()))  ;call traverse on the given list with an empty list of unvisited vertices

;updates the list of distances given a vertex and a new distance
(define (update-distances vertex new-distance distances)
  (define (update-distance distance vertex-distance-pair)  ;updates the distance of a vertex
    (cons (car vertex-distance-pair) distance))  ;returns a pair with the vertex and the new distance
  (if (eqv? vertex (car (first distances)))  ;if the vertex matches the given vertex in the list
      (make-list (list (update-distance new-distance (first distances))) (rest distances))  ;return a list with the updated distance and the rest of the distances
      (make-list (list (first distances)) (update-distances vertex new-distance (rest distances)))))  ;else, make a list with the vertex and search the rest of the distances

;updates the list of previous vertices given a vertex and a previous vertex
(define (update-previous-vertices vertex prev-vertex previous-vertices)
  (define (update-prev-vertex previous-vertex previous-vertex-pair)  ;updates the previous vertex of a vertex
    (cons (car previous-vertex-pair) previous-vertex))  ;returns a pair with the vertex and the new previous vertex
  (if (eqv? vertex (car (first previous-vertices)))  ;if the vertex matches the given vertex in the list
      (make-list (list (update-prev-vertex prev-vertex (first previous-vertices))) (rest previous-vertices))  ;return a list with the updated previous vertex and the rest of the list
      (make-list (list (first previous-vertices)) (update-distances vertex prev-vertex (rest previous-vertices)))))  ;else, make a list with the vertex and search the rest of the list

;determines if the given list contains the vertex
(define (contains-vertex vertex unvisited-vertices)  
  (if (empty? unvisited-vertices)  ;if the list is empty
      #f  ;retrun false
      (if (eqv? vertex (first unvisited-vertices))  ;if the vertex matches the vertex in the list
          #t  ;return true
          (contains-vertex vertex (rest unvisited-vertices)))))  ;search the rest of the list

;gets the vertex with the minimum distance
(define (get-min-vertex distances unvisited-vertices) 
  (define (update-min min vertex-distance-pair)  ;updates the value of the minimum distance
    (if (contains-vertex (car vertex-distance-pair) unvisited-vertices)  ;if the vertex is in the list of unvisited vertices
        (if (symbol? min)
            (cdr vertex-distance-pair)
            (if (symbol? (cdr vertex-distance-pair))  ;if the distance of the vertex is undefined
                min  ;return min
                (if (< min (cdr vertex-distance-pair))  ;else, if min is less than the vertex's distance
                    min  ;return min
                    (cdr vertex-distance-pair))))  ;else, return the vertex's distance
        min))  ;if the vertex is not in the list of unvisited vertices, return min
    
      (define (get-min distances min)  ;gets the minimum distance in the list of distances
    (if (empty? distances)  ;if distances is empty
        min  ;return min
        (get-min (rest distances) (update-min min (first distances)))))  ;else, call get-min on the rest of the distances and update the value of min
  (define (get-vertex min-distance distances)  ;gets the vertex associated with the minimum distance
    (if (symbol? (cdr (first distances)))  ;if the vertex's distance is a symbol
        (get-vertex min-distance (rest distances))  ;search the rest of the vertices
        (if (and (= (cdr (first distances)) min-distance) (contains-vertex (car (first distances)) unvisited-vertices))  ;else, if the vertex's distance is equal to the given minimum distance
            (car (first distances))  ;return the vertex
            (get-vertex min-distance (rest distances)))))  ;else, search the rest of the vertices
  (get-vertex (get-min distances 'infinity) distances))  ;call get-vertex on the determined minimum distance and the list of distances

;removes the given vertex from the list of unvisited nodes
(define (remove-vertex vertex unvisited-vertices)
  (define (linear-search items key)   ;performs a linear search on the list items, determining whether key is among the items
    (let loop ((subitems items))  ;begin the loop with the given list
      (if (null? subitems)  ;if the list is empty return false
          #f
          (if (eqv? (first subitems) key)  ;if the first number is equal to the key, return true
              #t
              (loop (rest subitems))))))  ;else increment search the rest
  (if (linear-search unvisited-vertices vertex)  ;if the given vertex is within the list of unvisited-vertices
      (if (eqv? vertex (first unvisited-vertices))  ;if the given vertex equals the vertex in the list
          (rest unvisited-vertices)  ;return the rest of the list
          (make-list (list (first unvisited-vertices)) (remove-vertex vertex (rest unvisited-vertices))))  ;else, list the vertex and search the rest of the list
      unvisited-vertices))  ;if the given vertex is not in the list of unvisited-vertices, return the list

;gets a list of the neighbors of the given index
(define (get-neighbors vertex edges)
  (define (traverse edges neighbors)  ;iterates over the list of edges and builds the list ofneighbors
    (if (empty? edges)  ;if edges is empty
        neighbors  ;return the list of neighbors
        (if (eqv? (car (first edges)) vertex)  ;if the given vertex matches the first vertex in the edge
            (traverse (rest edges) (make-list neighbors (list (cdr (first edges)))))  ;add the vertex to the list of neighnors and traverse the rest of the edges
            (if (eqv? (cdr (first edges)) vertex)  ;if the given vertex matches the second vertes in the edge
                (traverse (rest edges) (make-list neighbors (list (car (first edges)))))  ;add the vertex to the list of neighnors and traverse the rest of the edges
                (traverse (rest edges) neighbors)))))  ;else, if the edge contains no matching vertices, traverse the rest of the edges
  (traverse edges '()))  ;call traverse on edges with an empty list of neighbors

;gets the distance of a vertex
(define (get-distance vertex distances)  
    (if (eqv? vertex (car (first distances)))  ;if the vertex in the list of distances matches the given vertex
        (cdr (first distances))  ;return the distance
        (get-distance vertex (rest distances))))  ;else, search the rest of the distances

;calculates the distance of the neighbor given the current vertex
(define (calculate-neighbor-distance vertex distances)
  (+ (get-distance vertex distances) 1))  ;add 1 to the found distance

;updates each neighbors distance and previous vertex values if the neighbor is on the shortest path
(define (update-neighbors vertex alternativeDistance neighbors distances previous-vertices)
  (if (empty? neighbors)  ;if the list of neighbors is empty
      (cons distances previous-vertices)  ;return a cons pair of the distance and previous vertex lists
      (if (symbol? (get-distance (first neighbors) distances))  ;if the distance of the neighbor is undefined
          (update-neighbors vertex alternativeDistance (rest neighbors) (update-distances (first neighbors) alternativeDistance distances) (update-previous-vertices (first neighbors) vertex previous-vertices))  ;update the distance and previous vertex lists
          (if (< alternativeDistance (get-distance (first neighbors) distances))  ;else, if the alternative distance is less than the neighbor's distance
              (update-neighbors vertex alternativeDistance (rest neighbors) (update-distances (first neighbors) alternativeDistance distances) (update-previous-vertices (first neighbors) vertex previous-vertices))  ;update the distance and previous vertex lists
              (update-neighbors vertex alternativeDistance (rest neighbors) distances previous-vertices)))))  ;else, search the rest of the neighbors

;a helper function that creates a new list given a list and an item
(define (make-path lsts item)
  (define (swap-order item)  ;swaps the order of the item
    (list (cons (cdr item) (car item))))  ;place items in a list in reverse order
  (if (empty? lsts)  ;if the list is empty
      (swap-order item)  ;return the item
      (append (swap-order item) lsts)))  ;else, append the list to the item

;builds the shortest path given a list of previous vertices 
(define (build-path previous-vertices end)
  (define (traverse lst path previous)  ;builds the path
    (if (and (eqv? 'undefined (cdr (first lst))) (eqv? previous (car (first lst))))  ;if the previous vertex is undefined and the vertex matches the value of previous
        path  ;return the path
        (if (eqv? previous (car (first lst)))  ;if the vertex matches the value of previous
            (traverse previous-vertices (make-path path (first lst)) (cdr (first lst)))  ;traverse the original list of previous-vertices, build the path, and update the value of previous
            (traverse (rest lst) path previous))))  ;else, traverse the rest of the list
  (traverse previous-vertices '() end))  ;traverse the previous vertices with an initially empty path and the value of end
    
;gets the shortes path from the start vertex to the end vertex
(define (get-shortest-path vertices edges start end)
  (define (loop vertex unvisited-vertices distances previous-vertices) ;performs Dijkstra's algorithm
    (if (eqv? end vertex)  ;if the vertex is equal to the end vertex
        (update-neighbors vertex (calculate-neighbor-distance vertex distances) (get-neighbors vertex edges) distances previous-vertices)  ;update the values of the neighbors and return a cons pair of the distances list and the previous-vertices list
        (loop (get-min-vertex (car (update-neighbors vertex (calculate-neighbor-distance vertex distances) (get-neighbors vertex edges) distances previous-vertices))  ;pick the next vertix with a minimum distance from the updated distacnes list
                              (remove-vertex vertex unvisited-vertices))
              (remove-vertex vertex unvisited-vertices)  ;remove the vertex from the list of unvisited vertices
              (car (update-neighbors vertex (calculate-neighbor-distance vertex distances) (get-neighbors vertex edges) distances previous-vertices))  ;update the list of distances
              (cdr (update-neighbors vertex (calculate-neighbor-distance vertex distances) (get-neighbors vertex edges) distances previous-vertices)))))  ;update the list of previous-vertices
     
  (if (and (contains-vertex start vertices) (contains-vertex end vertices))  ;if the start and end vertices are within the list of vertices
      (build-path (cdr (loop (get-min-vertex (update-distances start 0 (initialize-distances vertices)) (initialize-unvisited-vertices vertices))  ;build the path from lidt of previous-verticies obtained from Dijkstra's algorithm
                        (initialize-unvisited-vertices vertices)
                        (update-distances start 0 (initialize-distances vertices))
                        (initialize-previous-vertices vertices)))
                  end)
      "please input valid start and end vertices"))  ;else, return a message to the user




;example test cases 
(define vertices (list 'one 'two 'three 'four 'five 'six))
(define edges (list (cons 'one 'two) (cons 'one 'three) (cons 'one 'six)
                    (cons 'two 'three) (cons 'two 'four)
                    (cons 'three 'four) (cons 'three 'six)
                    (cons 'four 'five) (cons 'five 'six)))
(define start 'one)
(define end 'five)
;(get-shortest-path vertices edges start end)

;Source for Dijkstra's algorithm: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
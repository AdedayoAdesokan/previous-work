%%Question 1
triangle(0,0).
triangle(N,T) :- N >= 0, N1 is N-1, triangle(N1,T1), T is N+T1.

%%Question 2
% this program can only be used when V is a variable or to check if the
% given integer V is correct.
power(X,0,1).
power(X,N,V) :-  N >= 0, N1 is N-1, power(X,N1,V1), V is X*V1.


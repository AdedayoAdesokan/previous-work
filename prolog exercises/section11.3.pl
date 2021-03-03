%%Question 1
my_not_equal(X,Y) :- X == Y, !, fail.
my_not_equal(X,Y).

%%Question 2
my_nonvar(X) :- var(X), !, fail.
my_nonvar(X).








































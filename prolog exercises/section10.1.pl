%%Question 1
% Implementing range this way allows us to search for M, N, or [M|Ns]
% individually. It also allows us to search for M and M at the same
% time given a list. We can also search for specific elements of the
% list given M or N.
range(M,N,[M|Ns]) :-
    nonvar(M), nonvar(N),
    M < N, M1 is M+1,
    build_list(M1,N,Ns).
range(M,N,[M|Ns]) :- var(N),my_last(N,Ns).

build_list(M,N,[M|Ns]) :- M < N, M1 is M+1, build_list(M1,N,Ns).
build_list(N,N,[N]).

my_last(N,[M|Ns]) :- my_last(N,Ns).
my_last(N,[N]).



%%Question 2
% Implemented so that the partitions are always within the range of zero
% and the given number.
my_between(I,J,I) :- I =< J.
my_between(I,J,K) :- I < J, I1 is I+1, between(I1,J,K).

my_plus(X,Y,Z) :- nonvar(X), nonvar(Y), Z is X+Y.
my_plus(X,Y,Z) :- nonvar(X), nonvar(Z), Y is Z-X.
my_plus(X,Y,Z) :- nonvar(Y), nonvar(Z), X is Z-Y.
my_plus(X,Y,Z) :-
    var(X), var(Y), my_between(0,Z,X), Y is Z-X.
my_plus(X,Y,Z) :-
    var(X), var(Z), my_between(0,Y,X), Z is X+Y.
my_plus(X,Y,Z) :-
    var(Y), var(Z), my_between(0,X,Y), Z is X+Y.

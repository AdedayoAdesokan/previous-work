%%Question 1
quicksort([X|Xs],Ys) :-
    my_partition(Xs,X,Littles,Bigs),
    quicksort(Littles,Ls),
    quicksort(Bigs,Bs),
    append(Ls,[X|Bs],Ys).
quicksort([],[]).

my_partition([X|Xs],Y,[X|Ls],Bs) :-
    X =< Y, ! ,my_partition(Xs,Y,Ls,Bs).
my_partition([X|Xs],Y,Ls,[X|Bs]) :-
    X > Y,  ! ,my_partition(Xs,Y,Ls,Bs).
my_partition([],Y,[],[]).


%%Question 2
derivative(X,X,s(0)) :- !.
derivative(X^s(N),X,s(N)*X^N) :- !.
derivative(sin(X),X,cos(X)) :- !.
derivative(cos(X),X,-sin(X)) :- !.
derivative(e^X,X,e^X) :- !.
derivative(log(X),X,1/X) :- !.

derivative(F+G,X,DF+DG) :-
    ! ,derivative(F,X,DF), derivative(G,X,DG).
derivative(F-G,X,DF-DG) :-
    ! ,derivative(F,X,DF), derivative(G,X,DG).
derivative(F*G,X,F*DG+DF*G) :-
    ! ,derivative(F,X,DF), derivative(G,X,DG).
derivative(1/F,X,-DF/(F*F)) :-
    ! ,derivative(F,X,DF).
derivative(F/G,X,(G*DF-F*DG)/(G*G)) :-
    ! ,derivative(F,X,DF), derivative(G,X,DG).


%%Question 3
my_sort([],[]) :- !.
my_sort([X|Xs],Ys) :- my_sort(Xs,Zs), insert(X,Zs,Ys).

insert(X,[],[X]) :- !.
insert(X,[Y|Ys],[Y|Zs]) :- X > Y, !, insert(X,Ys,Zs).
insert(X,[Y|Ys],[X,Y|Ys]) :- X =< Y, !.

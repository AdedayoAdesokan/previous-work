%%Question 1
not_equal(Arg1,Arg2).

occurences_list(Sub,[],0).
occurences_list(Sub,[Sub|Args],N) :-
    occurences_list(Sub,Args,N1), N is N1+1.
occurences_list(Sub,[Arg|Args],N) :-
    not_equal(Sub,Arg), occurences_list(Sub,Args,N).
occurences(Sub,Term,N) :- compound(Term), Term =.. Args,
    occurences_list(Sub,Args,N).

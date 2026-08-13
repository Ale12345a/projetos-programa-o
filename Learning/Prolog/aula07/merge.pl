mergelist([],[],[]).
mergelist([X],[],[X]).
mergelist([],[Y],[Y]).
mergelist([X|L1],[Y|L2],[X|L]) :-
X =< Y,
mergelist(L1,[Y|L2],L).
mergelist([X|L1],[Y|L2],[Y|L]) :-
X > Y, mergelist([X|L1],L2,L).

minimum(X,Y,X) :- X=<Y, !.
minimum(_,Y,Y).


naf(Goal):-call(Goal),!,fail.
naf(_).

disjoint(L1,L2):-
naf( (member(X,L1),member(X,L2)) ).

writelist([]).
writelist([X|L]):-
write(X),write(' '),writelist(L).

writelist2(L):-
member(X,L),write(X),write(' '),fail.
writelist2(_).
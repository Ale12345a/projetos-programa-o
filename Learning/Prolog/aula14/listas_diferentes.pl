flatten(X,[X]):-atomic(X),X\=[].
flatten([],[]).
flatten([X|L],FL2):-
flatten(X,FX),
flatten(L,FL),
append(FX,FL,FL2).

flatten2(X,FX):-flatten_dl(X,FX-[]).
flatten_dl(X,[X|R]-R):-atomic(X),X\=[].
flatten_dl([],R-R).
flatten_dl([X|L],FX-R2):-
flatten_dl(X,FX-R),
flatten_dl(L,R-R2).

rev([],[]).
rev([X|L],RL2):-rev(L,RL),append(RL,[X],RL2).

rev_dl([],R-R).
rev_dl([X|L],RL-R):-rev_dl(L,RL-[X|R]).

quicksort([],[]).
quicksort([A|R],SL):-
partition(A,R,L1,L2),
quicksort(L1,SL1),
quicksort(L2,SL2),
append(SL1,[A|SL2],SL).
partition(A,[],[],[]).
partition(A,[X|L1],[X|R1],R2):-
X=<A,partition(A,L1,R1,R2),!.
partition(A,[X|L1],R1,[X|R2]):-
X>A,partition(A,L1,R1,R2),!.

quicksort2(L,SL):-quicksort_dl(L,SL-[]).
quicksort_dl([],L-L).
quicksort_dl([A|R],SL-RSL):-
partition(A,R,L1,L2),
quicksort_dl(L1,SL-[A|R2]),
quicksort_dl(L2,R2-RSL).

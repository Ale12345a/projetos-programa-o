words([four, sour, soul, foul, saul, sail, nail, rail, raul, fail, fain, rain, gain, gait, grit, grid, arid, aria]).

% diferença de letras
nequal([], [], 0).
nequal([X|L1],[Y|L2],N2):-
    binequal(X,Y,B),
    nequal(L1,L2,N),
    N2 is N+B.

binequal(X,X,1).
binequal(X,Y,0):-X\=Y.

% transição: duas palavras diferem exactamente por 1 letra
transition(A,B) :-
    words(W),
    member(A,W), member(B,W),
    atom_chars(A,LA), atom_chars(B,LB),
    nequal(LA,LB,1).

bestfirst(Goal,[h([Goal|Path],_)|_],[Goal|Path]).
bestfirst(Goal,[h(Path,_)|Paths],Sol):-
expandh(Path,Goal,HExpPaths),
insert(HExpPaths,Paths,Paths2),
bestfirst(Goal,Paths2,Sol),!.

expandh([First|Path],Goal,ExpPaths):-
findall(h([Next,First|Path],H),(
transition(First,Next),
not(member(Next,[First|Path])),
heuristic(Next,Goal,H)
),
ExpPaths).

heuristic(Next,Goal,H):-
atom_chars(Next,LA),atom_chars(Goal,LB),
nequal(LA,LB,H).

insert([],L,L).
insert([HPath|HPaths],HExpPaths,HExpPaths3):-
aux_insert(HPath,HExpPaths,HExpPaths2),
insert(HPaths,HExpPaths2,HExpPaths3),!.

aux_insert(HPath,[],[HPath]).
aux_insert(h(Path,H),[h(Path2,H2)|HExpPaths],
[h(Path,H),h(Path2,H2)|HExpPaths]):-
H>=H2,!.
aux_insert(h(Path,H),[h(Path2,H2)|HExpPaths],
[h(Path2,H2)|HExpPaths2]):-
H<H2,aux_insert(h(Path,H),HExpPaths,HExpPaths2).
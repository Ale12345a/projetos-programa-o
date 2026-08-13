words([four, sour, soul, foul, saul, sail, nail,
rail, raul, fail, fain, rain, gain, gait, grit, grid,
arid, aria, asia, vain, bait, bail, ball, fall, bali,
mall, male, tale, tail, sale, sane, sans, sand,
send, mend, menu, mole, role, sole, tile,
mile, tide, ride, rise, boil, soil, foil, coil, coin,
coat, cost, lost, lose, lust, gust, must, sent,
bent, rent, beni, soup, soap, snap, snag, slag,
flag, flat, flan, mice, rice, vice, rich, rime,
lime, time, tame, lame, same, late, gate,
mate, made, fade, fate, math, moth, path,
mith, mash, rash, rush]).


transition(A,B):-
words(W),member(A,W),member(B,W),
atom_chars(A,LA),atom_chars(B,LB),
nequal(LA,LB,3). 

nequal([ ], [ ], 0).
nequal([X|L1],[Y|L2],N2):-
binequal(X,Y,B),
nequal(L1,L2,N),
N2 is N+B,!.
binequal(X,X,1).
binequal(X,Y,0):-X\=Y,!.

ladder(A,B,_,[B]):-
transition(A,B).
ladder(A,B,Mem,[C|Ladder]):-
transition(A,C),
not(member(C,Mem)),
ladder(C,B,[C|Mem],Ladder).

bfs(Goal,[[Goal|Path]|_],[Goal|Path]).
bfs(Goal,[Path|Paths],Sol):-
expand(Path,ExpPaths),
append(Paths,ExpPaths,Paths2),
bfs(Goal,Paths2,Sol),!.

expand([First|Path],ExpPaths):-
findall([Next,First|Path],(
transition(First,Next),
not(member(Next,[First|Path]))
),
ExpPaths).
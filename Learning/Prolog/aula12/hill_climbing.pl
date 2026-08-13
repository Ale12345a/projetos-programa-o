hillclimbing(Goal,h([Goal|Path],_),[Goal|Path]).
hillclimbing(Goal,h(Path,_),Sol):-
expandh(Path,Goal,HExpPaths),
bestpath(HExpPaths,BestPath),
hillclimbing(Goal,BestPath,Sol),!.

bestpath([Path],Path).
bestpath([Path|HPaths],BestPath2):-
bestpath(HPaths,BestPath1),
aux_bestpath(Path,BestPath1,BestPath2),!.
aux_bestpath(h(P1,H1),h(_,H2),h(P1,H1)):-H1>H2,!.
aux_bestpath(h(_,H1),h(P2,H2),h(P2,H2)):-H1=<H2,!.


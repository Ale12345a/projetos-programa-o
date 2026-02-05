% Atribuições de valores às variáveis proposicionais
propvar(p,true).
propvar(q,true).
propvar(r,false).

% Avaliação de fórmulas
formulavalue(X,V) :- propvar(X,V), !.

% Conjunção verdadeira
formulavalue(A/\B,true) :-
    formulavalue(A,true),
    formulavalue(B,true), !.

% Conjunção falsa (catch-all)
formulavalue(_/\_, false) :- !.

% Disjunção verdadeira
formulavalue(A\/B,true) :-
    ( formulavalue(A,true) ; formulavalue(B,true) ), !.

% Disjunção falsa (catch-all)
formulavalue(_\/_, false) :- !.

% Negação verdadeira
formulavalue(not(A),true) :-
    formulavalue(A,false), !.

% Negação falsa (catch-all)
formulavalue(not(_), false) :- !.

pvars([x,y,z]).
pvar(X):-pvars(V),member(X,V).

power(X):-pvar(X),!.
power(X^Y):-pvar(X),integer(Y),Y>1,!.
coefficient(K):-number(K).

monomial(N):-number(N),!.
monomial(X):-power(X),!.
monomial(K*X):-coefficient(K),power(X),!.

polynomial(M):-monomial(M),!.
polynomial(P+M):-monomial(M),polynomial(P),!.
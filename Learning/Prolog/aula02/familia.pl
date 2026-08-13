father(homer,bart).
father(homer,lisa).
father(homer,maggie).
mother(marge,bart).
mother(marge,lisa).
mother(marge,maggie).

offspring(X, Y) :- mother(Y, X).  % X é filho(a) de Y se Y for mãe de X
offspring(X, Y) :- father(Y, X).  % X é filho(a) de Y se Y for pai de X

sibling(X,Y):-
    mother(M,X),mother(M,Y),
    father(F,X),father(F,Y),
    not(X==Y).

grandfather(X,Y):-father(X,A), father(B,Y), A=B.
grandfather(X,Y):-father(X,A), mother(B,Y), A=B.

% Uma pessoa X é tio de Y se X é irmão de um dos pais de Y
uncle(X, Y) :-
    father(P, Y),   % P é pai de Y
    brother(X, P).  % X é irmão de P

uncle(X, Y) :-
    mother(P, Y),   % P é mãe de Y
    brother(X, P).  % X é irmão de P

% Definindo irmão (compartilham pelo menos um pai ou uma mãe)
brother(X, Y) :-
    father(F, X), father(F, Y), X \= Y.
brother(X, Y) :-
    mother(M, X), mother(M, Y), X \= Y.

% X é avô ou avó de Y se X é pai ou mãe de Z e Z é pai ou mãe de Y
grandparent(X, Y) :-
    father(X, Z), father(Z, Y).

grandparent(X, Y) :-
    father(X, Z), mother(Z, Y).

grandparent(X, Y) :-
    mother(X, Z), father(Z, Y).

grandparent(X, Y) :-
    mother(X, Z), mother(Z, Y).

% Caso base: pai ou mãe é ancestor
ancestor(X, Y) :-
    father(X, Y).
ancestor(X, Y) :-
    mother(X, Y).

% Caso recursivo: X é ancestor de Y se X é ancestor de Z e Z é pai ou mãe de Y
ancestor(X, Y) :-
    father(Z, Y),
    ancestor(X, Z).

ancestor(X, Y) :-
    mother(Z, Y),
    ancestor(X, Z).


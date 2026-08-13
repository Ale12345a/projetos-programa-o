natural(0).
natural(s(X)):-natural(X).

sum(0,X,X).
sum(s(X),Y,s(Z)):-sum(X,Y,Z).

pair(X):- sum(Y,Y,X).

mult(0,_,0).
mult(s(X),Y,Z):-mult(X,Y,A),sum(Y,A,Z).

% EXPONENCIAÇÃO
% -------------------------
% X^0 = 1
exp(_, 0, s(0)).

% 0^N = 0 para N>0
exp(0, s(_), 0).

% X^(s(N)) = X * (X^N)
exp(X, s(N), Y) :-
    exp(X, N, Z),
    mul(X, Z, Y).

% Fatorial em números de Peano
% fact(N, F) é verdadeiro se F = N!

% Caso base: 0! = 1
fact(0, s(0)).

% Caso recursivo: (s(N))! = (s(N)) * (N!)
fact(s(N), F) :-
    fact(N, FN),        % calcula N!
    mult(s(N), FN, F).  % multiplica (s(N)) * N!

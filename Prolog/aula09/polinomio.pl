% ==============================
% POLINÓMIOS COM SIMPLIFICAÇÃO
% ==============================

% Simplificação de somas
simpoly(P+0, P) :- !.
simpoly(0+P, P) :- monomial(P), !.

% Caso especial: combinar monómios semelhantes
simpoly(P+M, P2+M3) :-
    monparts(M, _, XExp),              % ignora coeficiente (não é usado aqui)
    delmonomial(P, XExp, M2, P2), !,
    addmonomial(M, M2, M3).             % soma coeficientes

% Caso geral
simpoly(P+M, P2+M2) :-
    simpoly(P, P2),
    simmon(M, M2).
simpoly(M, M2) :- simmon(M, M2).

% -------------------------------------
% Monómios
% -------------------------------------

simmon(1*P, P) :- power(P), !.
simmon(0*_, 0) :- !.
simmon(M, M).

monomial(N) :- number(N), !.
monomial(X) :- power(X), !.
monomial(K*X) :- coefficient(K), power(X), !.

coefficient(K) :- number(K).
power(X) :- atom(X).
power(X^N) :- atom(X), integer(N), N > 0.

% -------------------------------------
% Partes de monómio
% -------------------------------------

% monparts(Mon, Coef, XExp)

% Case 1: power (x^n) → coefficient 1
monparts(X^N, 1, X^N) :-
    atom(X), integer(N), N > 0, !.

% Case 2: coefficient * power
monparts(K*P, K, P) :-
    number(K), power(P), !.

% Case 3: constant (independent term)
monparts(K, K, indep) :-
    number(K), !.

% Case 4: variable only (x)
monparts(X, 1, X) :-
    atom(X), !.


% -------------------------------------
% Operações sobre monómios
% -------------------------------------

% delmonomial(Poly, XExp, M, PolyRest)
% remove de Poly o monómio M com variável-expoente XExp, deixando PolyRest

% Caso 1: Poly é exatamente o monómio a remover
delmonomial(M, XExp, M, 0) :-
    monomial(M),
    monparts(M, _, XExp), !.

% Caso 2: Poly = M + Rest, e M é o monómio a remover
delmonomial(M+Rest, XExp, M, Rest) :-
    monomial(M),
    monparts(M, _, XExp), !.

% Caso 3: Poly = Rest + M, e M é o monómio a remover
delmonomial(Rest+M, XExp, M, Rest) :-
    monomial(M),
    monparts(M, _, XExp), !.

% Caso 4: Nenhum dos anteriores → recursão
delmonomial(P+M2, XExp, M, P2+M2) :-
    delmonomial(P, XExp, M, P2).


% Soma de monómios iguais
addmonomial(M1, M2, M3) :-
    monparts(M1, K1, XExp),
    monparts(M2, K2, XExp),
    K is K1 + K2,
    ( K =:= 0 -> 
        M3 = 0
    ; XExp == indep ->
        M3 = K
    ; K =:= 1 ->
        M3 = XExp
    ; K =:= -1 ->
        M3 = -1*XExp
    ; 
        M3 = K*XExp
    ).

    % Auxiliar que constrói o monómio resultante
    aux_addmonomial(0, _, 0) :- !.        % coeficiente zero → monómio eliminado
    aux_addmonomial(K, indep, K) :- !.   % constante (termo independente)
    aux_addmonomial(1, XExp, XExp) :- !. % coeficiente 1 → só a variável
    aux_addmonomial(-1, XExp, -1*XExp) :- !. % coeficiente -1 → -XExp
    aux_addmonomial(K, XExp, K*XExp).    % caso geral

    closesimpoly(P,P):-
        simpoly(P,P2),
        P==P2,!.
    closesimpoly(P,P3):-
        simpoly(P,P2),
        closesimpoly(P2,P3),!.

    % Caso base: monómio único
    mulKpoly(M, K, M2) :-
        monparts(M, Coef, XExp),
        NewCoef is Coef * K,
        (NewCoef =:= 0 -> M2 = 0 ; M2 = NewCoef*XExp).

% Soma de monómios
    mulKpoly(P+M, K, P2+M2) :-
        mulKpoly(P, K, P2),
        mulKpoly(M, K, M2).

    % Canonical form of a monomial
canonmon(M, K*XExp) :-
    monparts(M, K, P),
    (P = indep -> XExp = indep^1
    ; P = X^N -> XExp = X^N
    ; atom(P) -> XExp = P^1
    ; XExp = P).

% Canonical form of a polynomial
    canon(M+N, CM+CN) :-
        canon(M, CM),
        canon(N, CN).
    canon(M, CM) :-
        canonmon(M, CM).




% monparts(Mon, Coef, XExp)

% Case 1: power (x^n) → coefficient 1
%monparts(X^N, 1, X^N) :-
 %   atom(X), integer(N), N > 0, !.

% Case 2: coefficient * power
%monparts(K*P, K, P) :-
 %   number(K), power(P), !.

% Case 3: constant (independent term)
%monparts(K, K, indep) :-
  %  number(K), !.

% Case 4: variable only (x)
%monparts(X, 1, X) :-
 %   atom(X), !.


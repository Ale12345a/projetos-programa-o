list([]).
list([_|Y]):-list(Y).


member(X,[X|_]).
member(X,[_|Y]):-member(X,Y).

allmember([],_).
allmember([X|Y],L):-
member(X,L),
allmember(Y,L).

contained([],_).
contained([X|RX],[X|RY]):-contained(RX,RY).
contained(L,[_|RY]):-contained(L,RY).

prefix([],_).
prefix([X|RX],[X|RY]):-prefix(RX,RY).

suffix(L1, L2) :- append(_,L1,L2).

sublist(L1, L2) :- prefix(L1, L2).
sublist(L1, [_|L2]) :- sublist(L1, L2).

append([], L, L).
append([X|L1], L2, [X|L3]) :- append(L1, L2, L3).

% Caso base
reverse([], []).

% Caso recursivo
reverse([H|T], R) :-
    reverse(T, RevT),
    append(RevT, [H], R).

% Caso: os dois primeiros elementos da lista são X e Y
adjacent(X, Y, [X,Y|_]).

% Caso recursivo: descarta a cabeça e continua a procurar nos restantes
adjacent(X, Y, [_|T]) :-
    adjacent(X, Y, T).

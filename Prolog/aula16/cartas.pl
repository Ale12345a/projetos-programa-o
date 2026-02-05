card(c(R,S)):-
ranks(LR),suits(LS),
member(R,LR),member(S,LS).
ranks([2,3,4,5,6,7,queen,jack,king,ace]).
suits([hearts,diamonds,spades,clubs]).

deck(D):-
findall(c(R,S),card(c(R,S)),D).

%findall( Var , Goal, ListOfValues).

pai(homer,bart).
pai(homer,lisa).
pai(homer,maggie).
pai(abraham,homer).
pai(abraham,herb).
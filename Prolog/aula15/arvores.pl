tree2listapp(void,[]).
tree2listapp(t(A,LT,RT),L4):-
tree2listapp(LT,L1),
tree2listapp(RT,L2),
append(L1,[A],L3),
append(L3,L2,L4).

tree2list(T,L):-tree2listdl(T,L-[]).
tree2listdl(void,L-L).
tree2listdl(t(A,LT,RT),L-RL):-
tree2listdl(LT,L-[A|RL1]),
tree2listdl(RT,RL1-RL).

preeorder(T,L):-preeorder2(T,L-[]).
preeorder2(void,L-L).
preeorder2(t(A,LT,RT),[A|L]-RL):-
preeorder2(LT,L-RL1),
preeorder2(RT,RL1-RL).

postorder(T,L):-postorder2(T,L-[]).
postorder2(void,L-L).
postorder2(t(A,LT,RT),L-RL):-
postorder2(LT,L-RL1),
postorder2(RT,RL1-[A|RL]).

append_dl(C-B,B-D,C-D).

lookup(Key,[entry(Key,Value)|_],Value).
lookup(Key,[entry(Key1,_)|D],Value):-
Key\==Key1,lookup(Key,D,Value).

lookupt(Key,dict(Key,X,Left,Right),Value):-
!, X=Value.
lookupt(Key,dict(Key1,_,Left,Right),Value):-
Key @< Key1, lookupt(Key,Left,Value).
lookupt(Key,dict(Key1,_,Left,Right),Value):-
Key @> Key1, lookupt(Key,Right,Value).
node(a). node(b). node(c). node(d). node(e).

edge(a,b). edge(a,c). edge(b,d). edge(c,d). edge(d,e).

path(A,B):-edge(A,B).
path(A,C):-edge(A,B), path(B,C).
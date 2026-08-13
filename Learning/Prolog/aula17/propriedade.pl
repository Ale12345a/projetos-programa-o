has_property([X|R],P):-
    Goal =.. [P,X],call(Goal),
    has_property(R,P).
has_property([],_).

even(X):-0 is X mod 2.

double(X,Y):-Y is X*2.

map([X|R],P,[Y|RY]):-
    Goal=..[P,X,Y],call(Goal),
    map(R,P,RY).
map([],_,[]).

path(a,b).
path(b,c).
path(c,d).
path(a,X) :- path(a,Y), path(Y,X).

solve(true):-!.
solve((A,B)):-!,solve(A),solve(B).
solve(A):-clause(A,B),solve(B).

solvew(true):-!.
solvew((A,B)):-!,solvew(A),writeln(conj:A),
    solvew(B),writeln(conj:B).
solvew(A):-
    clause(A,B),solvew(B),writeln((A:-B)).

solvep(true,true):-!.
solvep((A,B),(ProofA,ProofB)):-!,
    solvep(A,ProofA),solvep(B,ProofB).
solvep(A,(A:-ProofB)):-
    clause(A,B),solvep(B,ProofB).

solve_d(true,0):-!.
solve_d((A,B),D):-!,
    solve_d(A,D1),solve_d(B,D2),D is max(D1,D2).
solve_d(A,D2):-
    clause(A,B),solve_d(B,D1),D2 is D1+1.


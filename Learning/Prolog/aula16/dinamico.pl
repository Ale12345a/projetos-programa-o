:-dynamic(solution/1).

myfindall(X,Goal,_):-
assert(solution([])),
call(Goal),solution(L),append(L,[X],L2),
retract(solution(_)),assert(solution(L2)),
fail.
myfindall(_,_,L):-
solution(L),retract(solution(L)).

myfindall_dl(X,Goal,_):-
assert(solution(L1-L1)),
call(Goal),solution(L-[X|L2]),
retract(solution(_)),assert(solution(L-L2)),
fail.

myfindall_dl(_,_,L):-
solution(L-[]),retract(solution(_)).
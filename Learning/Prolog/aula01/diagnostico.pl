febre(maria).
espirros(maria).
tosse(maria).
gripe(X) :- febre(X), espirros(X), tosse(X).
alergia(X):- espirros(X), not(febre(X)).
gripe(X) :- gripe(Y), contactou(X,Y), not(imune(X)).
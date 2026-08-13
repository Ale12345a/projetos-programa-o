homem(adao).
homem(abel).
homem(caim).
mulher(eva).
numa_relacao(adao,eva).
numa_relacao(X,Y):- numa_relacao(Y,X), !.
pai(adao,abel).
pai(adao,caim).
mae(eva, adao).
mae(eva, caim).
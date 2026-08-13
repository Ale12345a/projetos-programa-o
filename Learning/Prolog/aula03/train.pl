% =========================================================
% Diretivas para evitar warnings
% =========================================================
:- discontiguous train/1.
:- discontiguous car/2.
:- discontiguous open/1.
:- discontiguous closed/1.
:- discontiguous small/1.
:- discontiguous big/1.
:- discontiguous medium/1.
:- discontiguous load/2.
:- discontiguous square/1.
:- discontiguous triangle/1.
:- discontiguous round/1.

% =========================================================
% COMBOIOS
% =========================================================

train(t1).
car(t1,c1). car(t1,c2). car(t1,c3). car(t1,c4).
...


open(c1). open(c3). open(c4).
closed(c2). small(c2). big(c1). medium(c3). medium(c4).

load(c1,o1). load(c1,o2). load(c1,o3).
square(o1). square(o2). square(o3).

% Comboio 2
train(t2).
car(t2,d1). car(t2,d2). car(t2,d3).

open(d1). closed(d2). closed(d3).
small(d2). big(d1). medium(d3).

load(d1,p1). load(d2,p2). load(d3,p3).
round(p1). triangle(p2). square(p3).

% Comboio 3
train(t3).
car(t3,e1). car(t3,e2).

closed(e1). closed(e2).
small(e1). big(e2).

load(e1,q1). load(e2,q2).
triangle(q1). round(q2).

% =========================================================
% DIREÇÃO DOS COMBOIOS
% =========================================================

% Comboios que vão para leste: têm pelo menos uma carruagem pequena e fechada
eastbound(T) :- car(T,C), closed(C), small(C).

% Comboios que vão para oeste: todos os outros
westbound(T) :- train(T), \+ eastbound(T).

% =========================================================
% EXEMPLOS DE CONSULTAS
% =========================================================

% Quais comboios vão para leste?
% ?- eastbound(T).

% Quais comboios vão para oeste?
% ?- westbound(T).

% Quais carruagens de um comboio estão abertas?
% ?- car(t1,C), open(C).

% Quais objetos estão numa carruagem fechada?
% ?- car(T,C), closed(C), load(C,O).

% Quais objetos são quadrados?
% ?- square(O).

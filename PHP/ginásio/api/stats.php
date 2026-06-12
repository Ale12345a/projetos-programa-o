<?php

require "db.php";

$stats = [];

/* membros */

$stats['total_membros'] =
$pdo->query("
SELECT COUNT(*) FROM membros
")->fetchColumn();

/* aulas */

$stats['total_aulas'] =
$pdo->query("
SELECT COUNT(*) FROM aulas
")->fetchColumn();

/* treinos */

$stats['total_treinos'] =
$pdo->query("
SELECT COUNT(*) FROM treinos
")->fetchColumn();

/* peso médio */

$stats['peso_medio'] =
round(
$pdo->query("
SELECT AVG(peso) FROM membros
")->fetchColumn(),
1
);

/* receita */

$stats['receita_total'] =
$pdo->query("
SELECT IFNULL(SUM(valor),0)
FROM pagamentos
")->fetchColumn();

/* idade média */

$stats['idade_media'] =
round(
$pdo->query("
SELECT AVG(idade)
FROM membros
")->fetchColumn(),
1
);

echo json_encode($stats);
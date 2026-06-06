<?php
require_once __DIR__ . "/../db.php";
require_once "auth.php";

auth();

// dados base
$total = $pdo->query("SELECT COUNT(*) FROM alunos")->fetchColumn();
$media = $pdo->query("SELECT AVG(idade) FROM alunos")->fetchColumn();
$min = $pdo->query("SELECT MIN(idade) FROM alunos")->fetchColumn();
$max = $pdo->query("SELECT MAX(idade) FROM alunos")->fetchColumn();
$menores23 = $pdo->query("SELECT COUNT(*) FROM alunos WHERE idade < 23")->fetchColumn();

echo json_encode([
  "total_alunos" => (int)$total,
  "media_idade" => round($media, 1),
  "idade_minima" => (int)$min,
  "idade_maxima" => (int)$max,
  "alunos_jovens" => (int)$menores23
]);
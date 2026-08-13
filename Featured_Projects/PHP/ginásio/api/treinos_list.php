<?php
require "db.php";

$stmt = $pdo->query("
  SELECT treinos.*, membros.nome
  FROM treinos
  JOIN membros ON membros.id = treinos.membro_id
");

echo json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));
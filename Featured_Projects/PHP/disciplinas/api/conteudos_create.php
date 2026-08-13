<?php
require "../auth/admin_check.php";
require "db.php";

$stmt = $pdo->prepare("INSERT INTO conteudos (disciplina_id, descricao, periodo) VALUES (?, ?, ?)");
$stmt->execute([
  $_POST['disciplina_id'],
  $_POST['descricao'],
  $_POST['periodo']
]);

echo json_encode(["success" => true]);
<?php
require "../auth/admin_check.php";
require "db.php";

$stmt = $pdo->prepare("
  UPDATE conteudos
  SET descricao=?, periodo=?
  WHERE id=?
");

$stmt->execute([
  $_POST['descricao'],
  $_POST['periodo'],
  $_POST['id']
]);

echo json_encode(["success" => true]);
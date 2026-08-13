<?php
require "db.php";
require "../auth/admin_check.php";

$stmt = $pdo->prepare("
  INSERT INTO treinos(membro_id, descricao)
  VALUES (?, ?)
");

$stmt->execute([
  $_POST['membro_id'],
  $_POST['descricao']
]);
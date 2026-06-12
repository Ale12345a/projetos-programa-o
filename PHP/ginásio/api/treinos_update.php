<?php
require "db.php";
require "../auth/admin_check.php";

$stmt = $pdo->prepare("
  UPDATE treinos
  SET descricao=?
  WHERE id=?
");

$stmt->execute([
  $_POST['descricao'],
  $_POST['id']
]);
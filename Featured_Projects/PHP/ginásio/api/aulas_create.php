<?php
require "db.php";
require "../auth/admin_check.php";

$stmt = $pdo->prepare("
  INSERT INTO aulas(nome, horario, vagas)
  VALUES (?, ?, ?)
");

$stmt->execute([
  $_POST['nome'],
  $_POST['horario'],
  $_POST['vagas']
]);
<?php
require "db.php";
require "../auth/admin_check.php";

$stmt = $pdo->prepare("
  UPDATE aulas
  SET nome=?, horario=?, vagas=?
  WHERE id=?
");

$stmt->execute([
  $_POST['nome'],
  $_POST['horario'],
  $_POST['vagas'],
  $_POST['id']
]);
<?php

require "db.php";
require "../auth/admin_check.php";

$stmt = $pdo->prepare("
  INSERT INTO membros(nome, idade, peso, objetivo)
  VALUES (?, ?, ?, ?)
");

$stmt->execute([
  $_POST['nome'],
  $_POST['idade'],
  $_POST['peso'],
  $_POST['objetivo']
]);

echo json_encode(["success" => true]);
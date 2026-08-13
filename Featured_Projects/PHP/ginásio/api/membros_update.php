<?php

require "db.php";
require "../auth/admin_check.php";

$stmt = $pdo->prepare("
  UPDATE membros
  SET nome=?, idade=?, peso=?, objetivo=?
  WHERE id=?
");

$stmt->execute([
  $_POST['nome'],
  $_POST['idade'],
  $_POST['peso'],
  $_POST['objetivo'],
  $_POST['id']
]);

echo json_encode(["success" => true]);
<?php

require "db.php";
require "../auth/admin_check.php";

$stmt = $pdo->prepare("
  DELETE FROM membros WHERE id=?
");

$stmt->execute([$_GET['id']]);

echo json_encode(["success" => true]);
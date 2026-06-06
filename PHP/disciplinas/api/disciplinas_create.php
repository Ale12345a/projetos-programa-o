<?php
require "../auth/admin_check.php";
require "db.php";

$stmt = $pdo->prepare("INSERT INTO disciplinas (nome) VALUES (?)");
$stmt->execute([$_POST['nome']]);

echo json_encode(["success" => true]);
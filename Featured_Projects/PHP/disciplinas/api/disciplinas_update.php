<?php
require "../auth/admin_check.php";
require "db.php";

$stmt = $pdo->prepare("UPDATE disciplinas SET nome=? WHERE id=?");
$stmt->execute([$_POST['nome'], $_POST['id']]);

echo json_encode(["success" => true]);
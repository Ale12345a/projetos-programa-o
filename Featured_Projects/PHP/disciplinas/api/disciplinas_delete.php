<?php
require "../auth/admin_check.php";
require "db.php";

$stmt = $pdo->prepare("DELETE FROM disciplinas WHERE id=?");
$stmt->execute([$_GET['id']]);

echo json_encode(["success" => true]);
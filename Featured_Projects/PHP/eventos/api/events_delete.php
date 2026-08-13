<?php
include "db.php";
require "auth.php";

$id = $_GET['id'];

$stmt = $pdo->prepare("DELETE FROM events WHERE id = ?");
$stmt->execute([$id]);

echo json_encode(["success" => true]);
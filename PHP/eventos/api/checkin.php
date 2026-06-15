<?php
include "db.php";
require "auth.php";

$qr = $_POST['qr_code'] ?? '';

$stmt = $pdo->prepare("SELECT id FROM tickets WHERE qr_code = ?");
$stmt->execute([$qr]);

$ticket = $stmt->fetch();

if (!$ticket) {
  echo json_encode(["success" => false]);
  exit;
}

$stmt = $pdo->prepare("INSERT INTO checkins (ticket_id) VALUES (?)");
$stmt->execute([$ticket['id']]);

echo json_encode(["success" => true]);
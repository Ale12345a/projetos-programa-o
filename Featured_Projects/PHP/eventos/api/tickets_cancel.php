<?php

session_start();
require "db.php";

header("Content-Type: application/json");

try {

  if (!isset($_SESSION["user_id"])) {
    echo json_encode(["success" => false]);
    exit;
  }

  $qr = $_POST["qr_code"] ?? null;

  if (!$qr) {
    echo json_encode(["success" => false, "msg" => "QR inválido"]);
    exit;
  }

  $stmt = $pdo->prepare("
    UPDATE tickets
    SET status = 'cancelled'
    WHERE qr_code = ? AND user_name = ?
  ");

  $stmt->execute([$qr, $_SESSION["username"]]);

  echo json_encode(["success" => true]);

} catch (Exception $e) {

  echo json_encode([
    "success" => false,
    "msg" => $e->getMessage()
  ]);
}
<?php

session_start();
require "db.php";

header("Content-Type: application/json");

/* PERMISSÕES */
if (
  !isset($_SESSION["role"]) ||
  !in_array($_SESSION["role"], ["admin", "staff"])
) {
  echo json_encode([
    "success"=>false,
    "msg"=>"Sem permissão"
  ]);
  exit;
}

$qr = $_POST["qr_code"] ?? "";

if ($qr === "") {
  echo json_encode([
    "success"=>false
  ]);
  exit;
}

$stmt = $pdo->prepare("
SELECT *
FROM tickets
WHERE qr_code=?
");

$stmt->execute([$qr]);

$ticket = $stmt->fetch(PDO::FETCH_ASSOC);

if (!$ticket) {
  echo json_encode([
    "success"=>false,
    "msg"=>"Bilhete não existe"
  ]);
  exit;
}

if (
  $ticket["checked_in"]==1
  ||
  $ticket["status"]==="cancelled"
) {

  echo json_encode([
    "success"=>false,
    "msg"=>"Bilhete inválido"
  ]);

  exit;
}

$upd=$pdo->prepare("
UPDATE tickets
SET checked_in=1
WHERE qr_code=?
");

$upd->execute([$qr]);

echo json_encode([
 "success"=>true
]);
<?php
include "db.php";
require "auth.php";

$id = $_POST['id'] ?? null;
$name = $_POST['name'] ?? '';
$location = $_POST['location'] ?? '';
$event_date = $_POST['event_date'] ?? '';
$capacity = $_POST['capacity'] ?? 0;
$price = $_POST['price'] ?? 0;

if (!$id) {
  echo json_encode(["success" => false, "msg" => "ID em falta"]);
  exit;
}

$stmt = $pdo->prepare("
  UPDATE events 
  SET name = ?, location = ?, event_date = ?, capacity = ?, price = ?
  WHERE id = ?
");

$stmt->execute([$name, $location, $event_date, $capacity, $price, $id]);

echo json_encode(["success" => true]);
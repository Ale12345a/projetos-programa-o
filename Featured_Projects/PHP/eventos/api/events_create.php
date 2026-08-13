<?php

session_start();
require "db.php";

header("Content-Type: application/json");

if ($_SESSION["role"] !== "admin") {
  echo json_encode(["success" => false, "msg" => "Sem permissão"]);
  exit;
}

$name = $_POST["name"] ?? "";
$location = $_POST["location"] ?? "";
$date = $_POST["event_date"] ?? "";
$capacity = $_POST["capacity"] ?? 0;
$price = $_POST["price"] ?? 0;

if ($name === "" || $date === "") {
  echo json_encode(["success" => false, "msg" => "Campos obrigatórios"]);
  exit;
}

$stmt = $pdo->prepare("
  INSERT INTO events (name, location, event_date, capacity, price)
  VALUES (?, ?, ?, ?, ?)
");

$stmt->execute([$name, $location, $date, $capacity, $price]);

echo json_encode(["success" => true]);
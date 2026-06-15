<?php

$host = "127.0.0.1";
$db   = "eventos";
$user = "root";
$pass = "1234";

try {

  $pdo = new PDO(
    "mysql:host=$host;dbname=$db;charset=utf8",
    $user,
    $pass
  );

  $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

} catch (PDOException $e) {

  echo json_encode([
    "success" => false,
    "msg" => "DB ERROR: " . $e->getMessage()
  ]);

  exit;
}
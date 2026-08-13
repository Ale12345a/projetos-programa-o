<?php

session_start();
require "db.php";

header("Content-Type: application/json");

ini_set('display_errors', 0);
error_reporting(0);

try {

  $stmt = $pdo->query("SELECT * FROM events ORDER BY id DESC");
  $events = $stmt->fetchAll(PDO::FETCH_ASSOC);

  echo json_encode($events);

} catch (Exception $e) {

  echo json_encode([]);
}
<?php

session_start();
require "db.php";

header("Content-Type: application/json");

try {

  if (!isset($_SESSION["user_id"]) || $_SESSION["role"] !== "admin") {
    echo json_encode(["success" => false]);
    exit;
  }

  // eventos
  $events = $pdo->query("SELECT COUNT(*) FROM events")->fetchColumn();

  // 🎟️ bilhetes válidos + usados (SEM cancelados)
  $tickets = $pdo->query("
    SELECT COUNT(*) 
    FROM tickets 
    WHERE status != 'cancelled'
  ")->fetchColumn();

  // check-ins
  $checkins = $pdo->query("
    SELECT COUNT(*) 
    FROM tickets 
    WHERE checked_in = 1 
    AND status != 'cancelled'
  ")->fetchColumn();

  // ocupação
  $occupancy = $pdo->query("
    SELECT 
      ROUND(
        (SELECT COUNT(*) 
         FROM tickets 
         WHERE checked_in = 1 
         AND status != 'cancelled')
        /
        NULLIF((SELECT SUM(capacity) FROM events), 0)
        * 100
      ,2)
  ")->fetchColumn();

  echo json_encode([
    "success" => true,
    "events" => $events,
    "tickets" => $tickets,
    "checkins" => $checkins,
    "occupancy" => $occupancy ?? 0
  ]);

} catch (Exception $e) {

  echo json_encode([
    "success" => false,
    "msg" => $e->getMessage()
  ]);
}
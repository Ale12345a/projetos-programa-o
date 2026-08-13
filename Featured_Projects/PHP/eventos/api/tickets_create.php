<?php

session_start();

require "db.php";

header("Content-Type: application/json");

try {
  if (!isset($_SESSION["user_id"]) || $_SESSION["role"]==="staff") {
    echo json_encode(["success"=>false,"msg"=>"Sem permissão"]);
    exit;
  }

  $event_id = $_POST["event_id"]??null;
  if(!$event_id){
    echo json_encode([
      "success"=>false,
      "msg"=>"Evento inválido"
    ]);
    exit;
  }

  /* CAPACIDADE */

  $stmt= $pdo->prepare("
  SELECT capacity,

  (SELECT COUNT(*)
  FROM tickets
  WHERE event_id=? AND  status!='cancelled')
  sold
  FROM events
  WHERE id=?
  ");

  $stmt->execute([$event_id,$event_id]);

  $event= $stmt->fetch(PDO::FETCH_ASSOC);

  if(!$event){
    echo json_encode([
      "success"=>false,
      "msg"=>"Evento não existe"
    ]);
    exit;
  }

  if($event["sold"] >= $event["capacity"]){
    echo json_encode([
      "success"=>false,
      "msg"=>"Evento esgotado"
    ]);
    exit;
  }

  /* QR */

  $qr= bin2hex(random_bytes(10));

  /* INSERIR */

  $stmt= $pdo->prepare("

  INSERT INTO tickets(event_id, user_name, email, qr_code, status, checked_in)
  VALUES(?, ?, ?, ?, 'reserved', 0)
  ");

  $stmt->execute([$event_id, $_SESSION["username"], "", $qr]);

  echo json_encode(["success"=>true,"qr"=>$qr]);
}

catch(Exception $e){
  echo json_encode(["success"=>false,"msg"=>$e->getMessage()]);
}
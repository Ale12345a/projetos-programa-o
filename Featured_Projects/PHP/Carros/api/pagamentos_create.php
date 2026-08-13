<?php
include "db.php";

$reserva_id = $_POST['reserva_id'];
$valor = $_POST['valor'];
$data = $_POST['data_pagamento'];

$stmt = $conn->prepare("
  INSERT INTO pagamentos (reserva_id, valor, data_pagamento)
  VALUES (?, ?, ?)
");

$stmt->bind_param("ids", $reserva_id, $valor, $data);

if ($stmt->execute()) {
  echo json_encode(["success" => true]);
} else {
  echo json_encode(["success" => false]);
}
?>
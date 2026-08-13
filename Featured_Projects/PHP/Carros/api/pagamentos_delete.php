<?php
include "db.php";

header('Content-Type: application/json');

$id = $_GET['id'] ?? null;

if (!$id) {
  echo json_encode([
    "success" => false,
    "msg" => "ID inválido"
  ]);
  exit;
}

$stmt = $conn->prepare("DELETE FROM pagamentos WHERE id = ?");
$stmt->bind_param("i", $id);

if ($stmt->execute()) {

  echo json_encode([
    "success" => true
  ]);

} else {

  echo json_encode([
    "success" => false,
    "msg" => "Erro ao eliminar pagamento"
  ]);
}
?>
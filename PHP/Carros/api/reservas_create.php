<?php
include "db.php";

$cliente_id = $_POST['cliente_id'];
$veiculo_id = $_POST['veiculo_id'];
$data_inicio = $_POST['data_inicio'];
$data_fim = $_POST['data_fim'];

// VERIFICAR CONFLITO
$sql = "
SELECT * FROM reservas
WHERE veiculo_id = ?
AND (
  (data_inicio <= ? AND data_fim >= ?)
)
";

$stmt = $conn->prepare($sql);
$stmt->bind_param("iss", $veiculo_id, $data_fim, $data_inicio);
$stmt->execute();

$result = $stmt->get_result();

if ($result->num_rows > 0) {
  echo json_encode([
    "success" => false,
    "msg" => "Este veículo já está reservado nestas datas"
  ]);
  exit;
}

// INSERIR RESERVA
$stmt = $conn->prepare("
INSERT INTO reservas (cliente_id, veiculo_id, data_inicio, data_fim)
VALUES (?, ?, ?, ?)
");

$stmt->bind_param("iiss", $cliente_id, $veiculo_id, $data_inicio, $data_fim);
$stmt->execute();

echo json_encode(["success" => true]);
?>
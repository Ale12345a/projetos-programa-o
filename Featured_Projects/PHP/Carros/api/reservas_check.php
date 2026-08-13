<?php
include "db.php";

$veiculo_id = $_GET['veiculo_id'];
$data_inicio = $_GET['data_inicio'];
$data_fim = $_GET['data_fim'];

$res = $conn->query("
SELECT * FROM reservas
WHERE veiculo_id = $veiculo_id
AND (
  data_inicio <= '$data_fim'
  AND data_fim >= '$data_inicio'
)
");

if ($res->num_rows > 0) {
  echo json_encode([
    "available" => false
  ]);
} else {
  echo json_encode([
    "available" => true
  ]);
}
?>
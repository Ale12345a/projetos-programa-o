<?php
include "auth.php";
include "db.php";

if ($role !== "admin") {
  echo json_encode(["error"=>"forbidden"]);
  exit;
}

$id = $_POST['id'];
$cliente_id = $_POST['cliente_id'];
$veiculo_id = $_POST['veiculo_id'];
$inicio = $_POST['data_inicio'];
$fim = $_POST['data_fim'];

$conn->query("
UPDATE reservas SET
cliente_id='$cliente_id',
veiculo_id='$veiculo_id',
data_inicio='$inicio',
data_fim='$fim'
WHERE id=$id
");

echo json_encode(["success"=>true]);
?>
<?php
include "auth.php";
include "db.php";

if ($role !== "admin") {
  echo json_encode(["error"=>"forbidden"]);
  exit;
}

$id = $_POST['id'];
$reserva_id = $_POST['reserva_id'];
$valor = $_POST['valor'];

$conn->query("
UPDATE pagamentos SET
reserva_id='$reserva_id',
valor='$valor'
WHERE id=$id
");

echo json_encode(["success"=>true]);
?>
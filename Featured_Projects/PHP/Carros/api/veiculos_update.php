<?php
include "db.php";

$id = $_POST['id'];
$marca = $_POST['marca'];
$modelo = $_POST['modelo'];
$matricula = $_POST['matricula'];
$preco = $_POST['preco'];

$stmt = $conn->prepare("
  UPDATE veiculos 
  SET marca=?, modelo=?, matricula=?, preco_dia=? 
  WHERE id=?
");

$stmt->bind_param("sssdi", $marca, $modelo, $matricula, $preco, $id);
$stmt->execute();

echo json_encode(["success" => true]);
?>
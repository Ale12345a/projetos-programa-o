<?php
include "auth.php";
include "db.php";

if ($role !== "admin") {
  echo json_encode(["error"=>"forbidden"]);
  exit;
}

$marca = $_POST['marca'];
$modelo = $_POST['modelo'];
$matricula = $_POST['matricula'];
$preco = $_POST['preco'];

$conn->query("INSERT INTO veiculos (marca,modelo,matricula,preco_dia)
VALUES ('$marca','$modelo','$matricula','$preco')");

echo json_encode(["success"=>true]);
?>
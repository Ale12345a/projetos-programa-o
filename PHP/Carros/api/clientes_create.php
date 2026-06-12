<?php
include "auth.php";
include "db.php";

if ($role !== "admin") {
  echo json_encode(["error"=>"forbidden"]);
  exit;
}

$nome = $_POST['nome'];
$email = $_POST['email'];
$telefone = $_POST['telefone'];

$conn->query("INSERT INTO clientes (nome,email,telefone)
VALUES ('$nome','$email','$telefone')");

echo json_encode(["success"=>true]);
?>
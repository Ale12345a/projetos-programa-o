<?php
include "db.php";

$id = $_POST['id'];
$nome = $_POST['nome'];
$email = $_POST['email'];
$telefone = $_POST['telefone'];

$stmt = $conn->prepare("
  UPDATE clientes 
  SET nome=?, email=?, telefone=? 
  WHERE id=?
");

$stmt->bind_param("sssi", $nome, $email, $telefone, $id);
$stmt->execute();

echo json_encode(["success" => true]);
?>
<?php
include "auth.php";
include "db.php";

$clientes = $conn->query("SELECT COUNT(*) as t FROM clientes")->fetch_assoc();
$veiculos = $conn->query("SELECT COUNT(*) as t FROM veiculos")->fetch_assoc();
$reservas = $conn->query("SELECT COUNT(*) as t FROM reservas")->fetch_assoc();
$receita = $conn->query("SELECT SUM(valor) as t FROM pagamentos")->fetch_assoc();

echo json_encode([
  "clientes"=>$clientes['t'],
  "veiculos"=>$veiculos['t'],
  "reservas"=>$reservas['t'],
  "receita"=>$receita['t'] ?? 0
]);
?>
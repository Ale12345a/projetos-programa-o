<?php
include "db.php";

header('Content-Type: application/json');

$res = $conn->query("
SELECT 
  p.id,
  p.valor,
  p.data_pagamento,

  r.id AS reserva_id,
  c.nome AS cliente_nome,
  v.marca,
  v.modelo

FROM pagamentos p

JOIN reservas r ON r.id = p.reserva_id
JOIN clientes c ON c.id = r.cliente_id
JOIN veiculos v ON v.id = r.veiculo_id
");

$data = [];

while ($row = $res->fetch_assoc()) {
  $data[] = $row;
}

echo json_encode($data);
?>
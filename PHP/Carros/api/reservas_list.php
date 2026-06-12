<?php

include "db.php";

$sql = "
SELECT 
  reservas.id,
  reservas.veiculo_id,
  reservas.cliente_id,
  clientes.nome AS cliente_nome,
  veiculos.marca,
  veiculos.modelo,
  reservas.data_inicio,
  reservas.data_fim
FROM reservas
JOIN clientes ON clientes.id = reservas.cliente_id
JOIN veiculos ON veiculos.id = reservas.veiculo_id
";

$res =
$conn->query($sql);

$data=[];

while(
$row=
$res->fetch_assoc()
){

$data[]=
$row;

}

echo json_encode(
$data
);
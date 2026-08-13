<?php
include "auth.php";
include "db.php";

$res = $conn->query("SELECT * FROM clientes");

$data = [];
while ($row = $res->fetch_assoc()) {
  $data[] = $row;
}

echo json_encode([
  "role"=>$role,
  "data"=>$data
]);
?>
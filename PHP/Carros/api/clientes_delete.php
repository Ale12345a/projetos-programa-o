<?php
include "db.php";

$id = $_GET['id'];

$conn->query("DELETE FROM clientes WHERE id=$id");

echo json_encode(["success"=>true]);
?>
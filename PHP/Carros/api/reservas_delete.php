<?php
include "db.php";

$id = $_GET['id'];

$conn->query("DELETE FROM reservas WHERE id=$id");

echo json_encode(["success"=>true]);
?>
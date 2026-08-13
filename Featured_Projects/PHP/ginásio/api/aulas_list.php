<?php
require "db.php";

$stmt = $pdo->query("SELECT * FROM aulas");
echo json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));
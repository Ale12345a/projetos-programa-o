<?php

require "db.php";

$stmt = $pdo->query("SELECT * FROM membros");

echo json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));
<?php
require_once __DIR__ . "/../db.php";
require_once "auth.php";

auth();

$stmt = $pdo->query("SELECT * FROM alunos");
echo json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));
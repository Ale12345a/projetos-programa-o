<?php
require "../auth/check.php";
require "db.php";

$stmt = $pdo->prepare("SELECT * FROM conteudos WHERE disciplina_id=?");
$stmt->execute([$_GET['disciplina_id']]);

echo json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));
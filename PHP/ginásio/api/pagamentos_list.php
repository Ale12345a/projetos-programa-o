<?php
require "db.php";

$stmt = $pdo->query("
  SELECT pagamentos.*, membros.nome
  FROM pagamentos
  JOIN membros ON membros.id = pagamentos.membro_id
");

echo json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));
<?php
require "db.php";

$total = $pdo->query("SELECT COUNT(*) as total FROM disciplinas")->fetch();

echo json_encode([
  "total" => $total["total"]
]);
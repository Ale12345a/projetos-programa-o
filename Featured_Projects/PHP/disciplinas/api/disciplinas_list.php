<?php
require "../auth/check.php";
require "db.php";

echo json_encode(
  $pdo->query("SELECT * FROM disciplinas")->fetchAll(PDO::FETCH_ASSOC)
);
<?php
require "db.php";
require "../auth/admin_check.php";

$stmt = $pdo->prepare("
  INSERT INTO pagamentos(membro_id, valor, data_pagamento)
  VALUES (?, ?, ?)
");

$stmt->execute([
  $_POST['membro_id'],
  $_POST['valor'],
  $_POST['data']
]);
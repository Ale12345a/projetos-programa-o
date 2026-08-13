<?php
require "db.php";
require "../auth/admin_check.php";

$stmt = $pdo->prepare("
  UPDATE pagamentos
  SET valor=?, data_pagamento=?
  WHERE id=?
");

$stmt->execute([
  $_POST['valor'],
  $_POST['data'],
  $_POST['id']
]);
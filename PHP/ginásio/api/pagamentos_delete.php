<?php
require "db.php";
require "../auth/admin_check.php";

$stmt = $pdo->prepare("DELETE FROM pagamentos WHERE id=?");
$stmt->execute([$_GET['id']]);
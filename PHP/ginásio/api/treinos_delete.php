<?php
require "db.php";
require "../auth/admin_check.php";

$stmt = $pdo->prepare("DELETE FROM treinos WHERE id=?");
$stmt->execute([$_GET['id']]);
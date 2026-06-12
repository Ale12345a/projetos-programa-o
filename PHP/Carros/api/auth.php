<?php
session_start();

if (!isset($_SESSION['user_id'])) {
  echo json_encode(["error"=>"not_logged"]);
  exit;
}

$role = $_SESSION['role']; // admin ou user
$user_id = $_SESSION['user_id'];
?>
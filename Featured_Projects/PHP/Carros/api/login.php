<?php
session_start();
include "db.php";

$username = $_POST['user'];
$password = $_POST['pass'];

$res = $conn->query("SELECT * FROM users WHERE username='$username'");
$user = $res->fetch_assoc();

if ($user && password_verify($password, $user['password'])) {

  $_SESSION['user_id'] = $user['id'];
  $_SESSION['role'] = $user['role'];

  echo json_encode([
    "success" => true,
    "role" => $user['role']
  ]);

} else {
  echo json_encode(["success"=>false]);
}
?>
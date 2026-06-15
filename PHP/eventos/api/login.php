<?php

session_start();

require "db.php";

header("Content-Type: application/json");

ini_set('display_errors', 1);
error_reporting(E_ALL);

$username = trim(strtolower($_POST["username"] ?? ""));
$password = trim($_POST["password"] ?? "");

$stmt = $pdo->prepare("
SELECT id, username, password_hash, role
FROM users
WHERE username = ?
LIMIT 1
");

$stmt->execute([$username]);

$user = $stmt->fetch(PDO::FETCH_ASSOC);

if (!$user) {
  echo json_encode([
    "success" => false,
    "msg" => "Utilizador não existe"
  ]);
  exit;
}

if (!password_verify($password, $user["password_hash"])) {
  echo json_encode([
    "success" => false,
    "msg" => "Password incorreta"
  ]);
  exit;
}

$_SESSION["user_id"] = $user["id"];
$_SESSION["username"] = $user["username"];
$_SESSION["role"] = $user["role"];

echo json_encode([
  "success" => true,
  "role" => $user["role"],
  "username" => $user["username"]
]);

exit;
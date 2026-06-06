<?php
require_once __DIR__ . "/../db.php";
require_once __DIR__ . "/../vendor/autoload.php";

use Firebase\JWT\JWT;

header("Content-Type: application/json");

$data = json_decode(file_get_contents("php://input"));

$username = $data->username;
$password = $data->password;

$stmt = $pdo->prepare("SELECT * FROM users WHERE username=?");
$stmt->execute([$username]);
$user = $stmt->fetch();

if (!$user || !password_verify($password, $user['password'])) {
  echo json_encode(["success" => false, "message" => "Login inválido"]);
  exit;
}

$secret = "aula18_super_secret_key_2026_very_secure_key_123456";

$payload = [
  "iss" => "aula18",
  "iat" => time(),
  "exp" => time() + 3600,
  "user" => $user["username"],
  "role" => $user["role"]
];

$jwt = JWT::encode($payload, $secret, "HS256");

echo json_encode([
  "success" => true,
  "token" => $jwt,
  "user" => $user["username"],
  "role" => $user["role"]
]);
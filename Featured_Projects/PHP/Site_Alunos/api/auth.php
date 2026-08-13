<?php
require_once __DIR__ . "/../vendor/autoload.php";

use Firebase\JWT\JWT;
use Firebase\JWT\Key;

function auth() {

  $headers = getallheaders();
  $token = str_replace("Bearer ", "", $headers["Authorization"] ?? "");

  if (!$token) {
    http_response_code(401);
    exit;
  }

  $secret = "aula18_super_secret_key_2026_very_secure_key_123456";

  return JWT::decode($token, new Key($secret, "HS256"));
}
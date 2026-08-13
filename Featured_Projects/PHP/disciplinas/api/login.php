<?php
session_start();
require "db.php";

header('Content-Type: application/json');

$user = $_POST['user'] ?? '';
$pass = $_POST['pass'] ?? '';

$stmt = $pdo->prepare("SELECT * FROM users WHERE username = ?");
$stmt->execute([$user]);
$u = $stmt->fetch(PDO::FETCH_ASSOC);

if ($u && password_verify($pass, $u['password'])) {

    $_SESSION['user'] = $u['username'];
    $_SESSION['role'] = $u['role'];

    echo json_encode([
        "success" => true,
        "user" => $u['username'],
        "role" => $u['role']
    ]);

} else {

    echo json_encode([
        "success" => false,
        "message" => "Login inválido"
    ]);
}
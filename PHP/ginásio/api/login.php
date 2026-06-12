<?php

session_start();

require "db.php";

$username = $_POST['user'] ?? '';
$password = $_POST['pass'] ?? '';

$stmt = $pdo->prepare("
    SELECT *
    FROM users
    WHERE username = ?
");

$stmt->execute([$username]);

$user = $stmt->fetch(PDO::FETCH_ASSOC);

if ($user && password_verify($password, $user['password'])) {

    $_SESSION['user'] = $user['username'];
    $_SESSION['role'] = $user['role'];

    echo json_encode([
        "success" => true,
        "role" => $user['role']
    ]);

} else {

    echo json_encode([
        "success" => false
    ]);
}
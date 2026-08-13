<?php
require_once __DIR__ . "/../db.php";
require_once "auth.php";

$user = auth();

if ($user->role !== "admin") {
  http_response_code(403);
  exit;
}

$data = json_decode(file_get_contents("php://input"));

$stmt = $pdo->prepare("DELETE FROM alunos WHERE id=?");
$stmt->execute([$data->id]);

echo json_encode(["success" => true]);
<?php
include "api/db.php";

$hash = password_hash("admin123", PASSWORD_BCRYPT);

$conn->query("
INSERT INTO users (username, password, role)
VALUES ('admin', '$hash', 'admin')
");

echo "Admin criado com sucesso";
?>
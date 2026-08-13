<?php

session_start();

header("Content-Type: application/json");

echo json_encode([

 "logged" =>
 isset($_SESSION["user_id"]),

 "role" =>
 $_SESSION["role"] ?? null

]);
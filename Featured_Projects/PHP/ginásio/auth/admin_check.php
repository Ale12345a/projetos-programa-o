<?php
session_start();

if (!isset($_SESSION['user'])) {
    http_response_code(401);
    exit;
}

if ($_SESSION['role'] !== 'admin') {
    http_response_code(403);
    exit;
}
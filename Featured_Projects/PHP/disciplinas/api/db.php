<?php
$pdo = new PDO(
  "mysql:host=localhost;dbname=disciplinas;charset=utf8",
  "root",
  "1234",
  [
    PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION
  ]
);
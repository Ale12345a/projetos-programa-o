<?php

$pdo = new PDO(
  "mysql:host=localhost;dbname=aula18;charset=utf8",
  "root",
  "1234",
  [
    PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION
  ]
);
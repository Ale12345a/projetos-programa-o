<?php
$conn = new mysqli(
  "localhost",
  "root",
  "1234",
  "car_rental"
);

if ($conn->connect_error) {
  die("DB error: " . $conn->connect_error);
}
?>
<?php

echo "<pre>";

$passwords = [
    "admin" => "Admin123!",
    "staff" => "Staff123!",
    "user"  => "User123!"
];

foreach ($passwords as $role => $pass) {
    $hash = password_hash($pass, PASSWORD_DEFAULT);

    echo strtoupper($role) . "\n";
    echo "Password: " . $pass . "\n";
    echo "Hash: " . $hash . "\n";
    echo "----------------------\n\n";
}

echo "</pre>";
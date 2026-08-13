<?php

echo "ADMIN: " . password_hash("admin123", PASSWORD_DEFAULT);
echo "<br>";
echo "USER: " . password_hash("user123", PASSWORD_DEFAULT);
<?php

session_start();
require "db.php";

header("Content-Type: application/json");

try {if ($_SESSION["role"]==="user") {

$stmt=$pdo->prepare("

SELECT
t.*,
e.name event_title,
e.location,
e.event_date

FROM tickets t

JOIN events e
ON e.id=t.event_id

WHERE
t.user_name=?
AND
t.status!='cancelled'

ORDER BY t.id DESC

");

$stmt->execute([
$_SESSION["username"]
]);

}

else{

$stmt=$pdo->query("

SELECT
t.*,
e.name event_title,
e.location,
e.event_date

FROM tickets t

JOIN events e
ON e.id=t.event_id

WHERE
t.status!='cancelled'

ORDER BY t.id DESC

");

}

echo json_encode(
$stmt->fetchAll(PDO::FETCH_ASSOC)
);

} catch (Exception $e) {

  echo json_encode([
    "error" => true,
    "msg" => $e->getMessage()
  ]);
}
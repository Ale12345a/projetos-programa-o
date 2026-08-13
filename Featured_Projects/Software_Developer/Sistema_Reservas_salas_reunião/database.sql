CREATE TABLE rooms (
room_id INTEGER PRIMARY KEY,
name TEXT,
capacity INTEGER
);

CREATE TABLE bookings (
booking_id INTEGER PRIMARY KEY,
employee_name TEXT,
room_id INTEGER,
date TEXT,
time TEXT,
FOREIGN KEY(room_id) REFERENCES rooms(room_id)
);

--perguntas:
--Sala mais reservada
SELECT
r.name,
COUNT(b.booking_id) AS total_bookings
FROM rooms r
JOIN bookings b
ON r.room_id = b.room_id
GROUP BY r.name
ORDER BY total_bookings DESC;

--Número de reservas por dia
SELECT
date,
COUNT(*) AS total_bookings
FROM bookings
GROUP BY date;

--Reservas de hoje
SELECT *
FROM bookings
WHERE date = CURRENT_DATE;
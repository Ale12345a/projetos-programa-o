const bookingForm = document.getElementById("bookingForm");
const bookingList = document.getElementById("bookingList");

let bookings = [];

// Renderizar reservas
function renderBookings() {
    bookingList.innerHTML = "";

    bookings.forEach(booking => {
        const li = document.createElement("li");
        li.textContent = `${booking.room} - ${booking.time} - ${booking.user}`;
        bookingList.appendChild(li);
    });
}

// Submeter reserva
bookingForm.addEventListener("submit", function (e) {
    e.preventDefault();

    const user = userName.value;
    const room = document.getElementById("room").value;
    const time = document.getElementById("time").value;

    // Verificar se a sala já está reservada nessa hora
    const conflict = bookings.find(
        b => b.room === room && b.time === time
    );

    if (conflict) {
        alert("Esta sala já está reservada para essa hora.");
        return;
    }

    bookings.push({ user, room, time });
    renderBookings();
    bookingForm.reset();
});

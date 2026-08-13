const form = document.getElementById("bookingForm");
const table = document.getElementById("bookingTable");

let bookings = [];

function renderBookings(){

table.innerHTML="";

bookings.forEach(booking=>{

const row=document.createElement("tr");

row.innerHTML=`

<td>${booking.employee}</td>
<td>${booking.room}</td>
<td>${booking.date}</td>
<td>${booking.time}</td>
<td>
<button onclick="deleteBooking(${booking.id})">Cancel</button>
</td>

`;

table.appendChild(row);

});

}

form.addEventListener("submit",function(e){

e.preventDefault();

const employee=document.getElementById("employee").value;
const room=document.getElementById("room").value;
const date=document.getElementById("date").value;
const time=document.getElementById("time").value;

const conflict=bookings.find(
b=>b.room===room && b.date===date && b.time===time
);

if(conflict){

alert("This room is already booked at this time.");
return;

}

bookings.push({

id:Date.now(),
employee,
room,
date,
time

});

form.reset();

renderBookings();

});

function deleteBooking(id){

bookings=bookings.filter(b=>b.id!==id);

renderBookings();

}
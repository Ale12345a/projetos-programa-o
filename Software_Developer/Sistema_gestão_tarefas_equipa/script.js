const form = document.getElementById("taskForm");
const table = document.getElementById("taskTable");

let tasks = [];

function render(){

table.innerHTML="";

tasks.forEach(task=>{

const row=document.createElement("tr");

row.innerHTML=`

<td>${task.title}</td>
<td>${task.employee}</td>
<td>${task.priority}</td>
<td>${task.status}</td>
<td>
<button onclick="completeTask(${task.id})">Done</button>
</td>

`;

table.appendChild(row);

});

}

form.addEventListener("submit",function(e){

e.preventDefault();

const task={

id:Date.now(),
title:title.value,
employee:employee.value,
priority:priority.value,
status:"Pending"

};

tasks.push(task);

form.reset();

render();

});

function completeTask(id){

tasks=tasks.map(t=>
t.id===id ? {...t,status:"Done"} : t
);

render();

}
const form = document.getElementById("employeeForm");
const table = document.getElementById("employeeTable");
const search = document.getElementById("search");

let employees = [];

function calculateSalary(hours, rate){
    return hours * rate;
}

function renderEmployees(filter=""){

table.innerHTML="";

employees
.filter(e=>e.name.toLowerCase().includes(filter.toLowerCase()))
.forEach((emp,index)=>{

const salary = calculateSalary(emp.hours, emp.rate);

const row=document.createElement("tr");

row.innerHTML=`

<td>${index+1}</td>
<td>${emp.name}</td>
<td>${emp.department}</td>
<td>${emp.hours}</td>
<td>${emp.rate.toFixed(2)}€</td>
<td>${salary.toFixed(2)}€</td>

`;

table.appendChild(row);

});

}

form.addEventListener("submit",function(e){

e.preventDefault();

const employee={

name:document.getElementById("name").value,
department:document.getElementById("department").value,
hours:Number(document.getElementById("hours").value),
rate:Number(document.getElementById("rate").value)

};

employees.push(employee);

form.reset();

renderEmployees();

});

search.addEventListener("input",function(){

renderEmployees(search.value);

});
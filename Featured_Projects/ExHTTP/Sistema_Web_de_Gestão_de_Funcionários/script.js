const form = document.getElementById("employeeForm");
const table = document.getElementById("employeeTable");
const filterDepartment = document.getElementById("filterDepartment");
const totalSalaryElem = document.getElementById("totalSalary");

let employees = JSON.parse(localStorage.getItem("employees")) || [];
let editingId = null;

// Guardar no LocalStorage
function save() {
    localStorage.setItem("employees", JSON.stringify(employees));
}

// Renderizar tabela
function render() {
    table.innerHTML = "";
    let totalSalary = 0;

    employees
        .filter(e => filterDepartment.value === "ALL" || e.department === filterDepartment.value)
        .forEach(employee => {
            totalSalary += employee.salary;

            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${employee.name}</td>
                <td>${employee.department}</td>
                <td>${employee.salary}€</td>
                <td>
                    <button onclick="editEmployee(${employee.id})">✏️</button>
                    <button onclick="deleteEmployee(${employee.id})">🗑</button>
                </td>
            `;
            table.appendChild(row);
        });

    totalSalaryElem.textContent = totalSalary;
}

// Submeter formulário
form.addEventListener("submit", function (e) {
    e.preventDefault();

    const name = document.getElementById("name").value;
    const department = document.getElementById("department").value;
    const salary = Number(document.getElementById("salary").value);

    if (salary <= 0) {
        alert("Salário inválido");
        return;
    }

    if (editingId) {
        employees = employees.map(emp =>
            emp.id === editingId
                ? { ...emp, name, department, salary }
                : emp
        );
        editingId = null;
    } else {
        employees.push({
            id: Date.now(),
            name,
            department,
            salary
        });
    }

    save();
    render();
    form.reset();
});

// Editar funcionário
function editEmployee(id) {
    const emp = employees.find(e => e.id === id);
    if (!emp) return;

    name.value = emp.name;
    department.value = emp.department;
    salary.value = emp.salary;
    editingId = id;
}

// Remover funcionário
function deleteEmployee(id) {
    if (!confirm("Remover funcionário?")) return;

    employees = employees.filter(e => e.id !== id);
    save();
    render();
}

// Filtro
filterDepartment.addEventListener("change", render);

// Inicial
render();

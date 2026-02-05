const taskForm = document.getElementById("taskForm");
const taskList = document.getElementById("taskList");
const filters = document.querySelectorAll(".filters button");

let tasks = JSON.parse(localStorage.getItem("tasks")) || [];

// Guardar no LocalStorage
function saveTasks() {
    localStorage.setItem("tasks", JSON.stringify(tasks));
}

// Criar tarefa
taskForm.addEventListener("submit", function (e) {
    e.preventDefault();

    const title = taskTitle.value;
    const priority = taskPriority.value;

    tasks.push({
        id: Date.now(),
        title,
        priority,
        done: false
    });

    saveTasks();
    renderTasks("ALL");
    taskForm.reset();
});

// Renderizar tarefas
function renderTasks(filter) {
    taskList.innerHTML = "";

    tasks
        .filter(task => {
            if (filter === "DONE") return task.done;
            if (filter === "OPEN") return !task.done;
            return true;
        })
        .forEach(task => {
            const li = document.createElement("li");
            li.className = `priority-${task.priority} ${task.done ? "done" : ""}`;

            li.innerHTML = `
                <span>${task.title}</span>
                <div>
                    <button onclick="toggleTask(${task.id})">✔</button>
                    <button onclick="deleteTask(${task.id})">🗑</button>
                </div>
            `;

            taskList.appendChild(li);
        });
}

// Concluir tarefa
function toggleTask(id) {
    tasks = tasks.map(task =>
        task.id === id ? { ...task, done: !task.done } : task
    );
    saveTasks();
    renderTasks("ALL");
}

// Apagar tarefa
function deleteTask(id) {
    tasks = tasks.filter(task => task.id !== id);
    saveTasks();
    renderTasks("ALL");
}

// Filtros
filters.forEach(button => {
    button.addEventListener("click", () => {
        renderTasks(button.dataset.filter);
    });
});

// Inicial
renderTasks("ALL");

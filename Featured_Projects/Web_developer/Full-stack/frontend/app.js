const api = "http://127.0.0.1:8000/tasks";

const form = document.getElementById("taskForm");
const list = document.getElementById("taskList");

async function loadTasks(){

    const res = await fetch(api);
    const tasks = await res.json();

    list.innerHTML = "";

    tasks.forEach(task => {

        const li = document.createElement("li");

        li.className = "list-group-item d-flex justify-content-between align-items-center";

        li.innerHTML = `
            <div>
                <strong>${task.title}</strong><br>
                <small>${task.description}</small>
            </div>

            <button class="btn btn-danger btn-sm" onclick="deleteTask(${task.id})">
                Delete
            </button>
        `;

        list.appendChild(li);

    });

}

form.addEventListener("submit", async (e)=>{

    e.preventDefault();

    const title = document.getElementById("title").value;
    const description = document.getElementById("description").value;

    await fetch(api,{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify({
            title,
            description,
            completed:false
        })
    });

    form.reset();
    loadTasks();

});

async function deleteTask(id){

    await fetch(`${api}/${id}`,{
        method:"DELETE"
    });

    loadTasks();

}

loadTasks();
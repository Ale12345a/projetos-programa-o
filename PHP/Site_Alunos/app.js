let token = "";

function login() {
  fetch("/api/login.php", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      username: document.getElementById("user").value,
      password: document.getElementById("pass").value
    })
  })
  .then(r => r.json())
  .then(data => {

    if (!data.success) {
      alert("Login inválido");
      return;
    }

    token = data.token;

    document.getElementById("login").style.display = "none";
    document.getElementById("app").style.display = "block";

    loadStats();
    loadAlunos();

    if (data.role !== "admin") {
      document.getElementById("adminPanel").style.display = "none";
    }
  });
}

function logout() {
  token = "";
  location.reload();
}

function loadStats() {
  fetch("/api/stats.php", {
    headers: { Authorization: "Bearer " + token }
  })
  .then(r => r.json())
  .then(data => {

    document.getElementById("total").innerHTML =
      "👨‍🎓 Total alunos: " + data.total_alunos;

    document.getElementById("media").innerHTML =
      "📊 Média idade: " + data.media_idade;

    let extra = document.getElementById("extraStats");

    extra.innerHTML = `
      <div class="card">🧒 Mais novo: ${data.idade_minima}</div>
      <div class="card">👴 Mais velho: ${data.idade_maxima}</div>
      <div class="card">🎯 Menores de 23: ${data.alunos_jovens}</div>
    `;
  });
}

function loadAlunos() {
  fetch("/api/alunos.php", {
    headers: { Authorization: "Bearer " + token }
  })
  .then(r => r.json())
  .then(data => {

    alunos.innerHTML = data.map(a => `
  <div class="row">
    <input id="nome-${a.id}" value="${a.nome}">
    <input id="idade-${a.id}" value="${a.idade}">

    <button onclick="updateAluno(${a.id})">Editar</button>
    <button onclick="del(${a.id})">X</button>
  </div>
`).join("");
  });
}

function createAluno() {
  fetch("/api/alunos_create.php", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + token
    },
    body: JSON.stringify({
      nome: nome.value,
      idade: idade.value
    })
  }).then(() => {
    loadAlunos();
    loadStats();
  });
}

function del(id) {
  fetch("/api/alunos_delete.php", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + token
    },
    body: JSON.stringify({ id })
  })
  .then(() => {
    loadAlunos();
    loadStats();
  });
}

function updateAluno(id) {

  const nome = document.getElementById("nome-" + id).value;
  const idade = document.getElementById("idade-" + id).value;

  fetch("/api/alunos_update.php", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + token
    },
    body: JSON.stringify({
      id,
      nome,
      idade
    })
  })
  .then(() => {
    loadAlunos();
    loadStats();
  });
}
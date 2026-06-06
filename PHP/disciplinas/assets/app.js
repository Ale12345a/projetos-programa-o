let role = null;
let disciplinaAtual = null;

// ---------------- LOGIN ----------------
function login() {

  const user = document.getElementById("user").value;
  const pass = document.getElementById("pass").value;

  fetch("api/login.php", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    },
    body: `user=${user}&pass=${pass}`
  })
  .then(r => r.json())
  .then(data => {

    if (data.success) {

      role = data.role;

      document.getElementById("login").style.display = "none";
      document.getElementById("app").style.display = "block";

      loadDisciplinas();

    } else {
      alert("Login inválido");
    }
  });
}

// ---------------- LOGOUT ----------------
function logout() {
  fetch("api/logout.php")
    .then(() => location.reload());
}

// ---------------- DISCIPLINAS ----------------
function loadDisciplinas() {

  fetch("api/disciplinas_list.php")
    .then(r => r.json())
    .then(data => {

      let html = "<ul>";

      data.forEach(d => {

        html += `
          <li class="row">
            <span class="text">
              ${d.nome}
            </span>

            <span class="actions">
              <button onclick="loadConteudos(${d.id})">ver</button>
        `;

        if (role === "admin") {
          html += `
              <button onclick="deleteDisciplina(${d.id})" class="delete">apagar</button>
          `;
        }

        html += `
            </span>
          </li>
        `;
      });

      html += "</ul>";

      document.getElementById("lista").innerHTML = html;
    });
}

// ---------------- CRIAR DISCIPLINA ----------------
function add() {

  const nome = document.getElementById("nome").value;

  fetch("api/disciplinas_create.php", {
    method: "POST",
    headers: {"Content-Type":"application/x-www-form-urlencoded"},
    body: `nome=${nome}`
  })
  .then(() => loadDisciplinas());
}

// ---------------- APAGAR DISCIPLINA ----------------
function deleteDisciplina(id) {

  fetch("api/disciplinas_delete.php?id=" + id)
    .then(() => loadDisciplinas());
}

// ---------------- CONTEÚDOS ----------------
function loadConteudos(id) {

  disciplinaAtual = id;

  fetch("api/conteudos_list.php?disciplina_id=" + id)
    .then(r => r.json())
    .then(data => {

      let html = "<h3>Conteúdos</h3>";

      html += `
        <button onclick="addConteudo(${id})">+ adicionar conteúdo</button>
      `;

      html += "<ul>";

      data.forEach(c => {

        html += `
          <li class="row">
            <span class="text">
              ${c.descricao} (P${c.periodo})
            </span>

            <span class="actions">

              <button onclick="verConteudo(${c.id})">ver</button>
        `;

        if (role === "admin") {
          html += `
              <button onclick="editConteudo(${c.id}, '${c.descricao}', ${c.periodo})">editar</button>
              <button onclick="deleteConteudo(${c.id})" class="delete">apagar</button>
          `;
        }

        html += `
            </span>
          </li>
        `;
      });

      html += "</ul>";

      document.getElementById("conteudos").innerHTML = html;
    });
}

// ---------------- CRIAR CONTEÚDO ----------------
function addConteudo(disciplina_id) {

  const descricao = prompt("Descrição:");
  const periodo = prompt("Período (1,2,3):");

  if (!descricao || !periodo) return;

  fetch("api/conteudos_create.php", {
    method: "POST",
    headers: {"Content-Type":"application/x-www-form-urlencoded"},
    body: `disciplina_id=${disciplina_id}&descricao=${descricao}&periodo=${periodo}`
  })
  .then(() => loadConteudos(disciplina_id));
}

// ---------------- EDITAR CONTEÚDO ----------------
function editConteudo(id, descricao, periodo) {

  const nova = prompt("Descrição:", descricao);
  const novoP = prompt("Período:", periodo);

  if (!nova || !novoP) return;

  fetch("api/conteudos_update.php", {
    method: "POST",
    headers: {"Content-Type":"application/x-www-form-urlencoded"},
    body: `id=${id}&descricao=${nova}&periodo=${novoP}`
  })
  .then(() => loadConteudos(disciplinaAtual));
}

// ---------------- APAGAR CONTEÚDO ----------------
function deleteConteudo(id) {

  fetch("api/conteudos_delete.php?id=" + id)
    .then(() => loadConteudos(disciplinaAtual));
}

// ---------------- VER CONTEÚDO ----------------
function verConteudo(id) {

  fetch("api/conteudos_list.php?disciplina_id=" + disciplinaAtual)
    .then(r => r.json())
    .then(data => {

      const c = data.find(item => item.id == id);

      document.getElementById("conteudoDetalhe").innerHTML = `
        <div class="card">
          <h3>${c.descricao}</h3>
          <p>Período: ${c.periodo}</p>
        </div>
      `;
    });
}
let role = null;
let membrosCache = [];

// ---------------- LOGIN ----------------
function login() {

  fetch("api/login.php", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    },
    body:
      "user=" + encodeURIComponent(user.value) +
      "&pass=" + encodeURIComponent(pass.value)
  })

  .then(r => r.json())

  .then(data => {

    if (data.success) {

      role = data.role;

      document.getElementById("login").style.display = "none";
      document.getElementById("app").style.display = "block";

      // Dashboard
      loadStats();
      loadMembros();   // TEM de vir primeiro
      loadAulas();
      loadTreinos();
      loadPagamentos();

      setTimeout(preencherDropdowns, 300);

    } else {

      alert("Login inválido");

    }

  })

  .catch(err => {

    console.error(err);
    alert("Erro ao ligar ao servidor");

  });

}

// ---------------- LOGOUT ----------------
function logout() {
  fetch("api/logout.php").then(() => location.reload());
}

// ---------------- MEMBROS ----------------
function loadMembros() {

  fetch("api/membros_list.php")
  .then(r => r.json())
  .then(data => {

    membrosCache = data;

    let html = "<h3>Membros</h3>";

    if (role === "admin") {
      html += `<button onclick="addMembro()">+ adicionar membro</button>`;
    }

    html += "<ul>";

    data.forEach(m => {

      html += `
      <li>
        <b>${m.nome}</b>
        - ${m.idade} anos
        - ${m.peso}kg
        - ${m.objetivo}
      </li>
      `;

    });

    html += "</ul>";

    document.getElementById("membros").innerHTML = html;

  })

  .then(() => {

    preencherDropdowns();

  })

  .catch(console.error);

}


// ---------------- CREATE ----------------
function addMembro() {

  const nome = prompt("Nome:");
  const idade = prompt("Idade:");
  const peso = prompt("Peso:");
  const objetivo = prompt("Objetivo:");

  if (!nome || !idade || !peso || !objetivo) {
    alert("Preenche tudo");
    return;
  }

  fetch("api/membros_create.php", {
    method: "POST",
    headers: {"Content-Type":"application/x-www-form-urlencoded"},
    body: `nome=${nome}&idade=${idade}&peso=${peso}&objetivo=${objetivo}`
  })
  .then(() => {
    refreshEverything(); // 🔥 IMPORTANTE
  });
}

// ---------------- DELETE ----------------
function deleteMembro(id) {

  fetch("api/membros_delete.php?id=" + id)
  .then(() => loadMembros());
}

// ---------------- EDIT ----------------
function editMembro(id, nome, idade, peso, objetivo) {

  const n = prompt("Nome:", nome);
  const i = prompt("Idade:", idade);
  const p = prompt("Peso:", peso);
  const o = prompt("Objetivo:", objetivo);

  fetch("api/membros_update.php", {
    method: "POST",
    headers: {"Content-Type":"application/x-www-form-urlencoded"},
    body: `id=${id}&nome=${n}&idade=${i}&peso=${p}&objetivo=${o}`
  })
  .then(() => loadMembros());
}

// ---------------- VER ----------------
function verMembro(id) {

  fetch("api/membros_list.php")
  .then(r => r.json())
  .then(data => {

    const m = data.find(x => x.id == id);

    alert(
      `Nome: ${m.nome}\nIdade: ${m.idade}\nPeso: ${m.peso}\nObjetivo: ${m.objetivo}`
    );
  });
}

function loadStats() {

fetch("api/stats.php")
.then(r => r.json())
.then(data => {

document.getElementById("stats").innerHTML = `

<div class="cards">

<div class="card">
<h3>Membros</h3>
<p>${data.total_membros}</p>
</div>

<div class="card">
<h3>Aulas</h3>
<p>${data.total_aulas}</p>
</div>

<div class="card">
<h3>Treinos</h3>
<p>${data.total_treinos}</p>
</div>

<div class="card">
<h3>Peso Médio</h3>
<p>${data.peso_medio} kg</p>
</div>

<div class="card">
<h3>Idade Média</h3>
<p>${data.idade_media}</p>
</div>

<div class="card">
<h3>Receita</h3>
<p>${data.receita_total} €</p>
</div>

</div>

`;

});
}

function loadAulas() {

  fetch("api/aulas_list.php")
  .then(r => r.json())
  .then(data => {

    let html = "<h3>Aulas</h3>";

    if (role === "admin") {
      html += `
        <button onclick="addAula()">
          + adicionar aula
        </button>
      `;
    }

    html += "<ul>";

    data.forEach(a => {

      html += `
        <li>

          ${a.nome}
          | ${a.horario}
          | ${a.vagas} vagas

          <span>
      `;

      if (role === "admin") {

        html += `
          <button onclick="editAula(
            ${a.id},
            '${a.nome}',
            '${a.horario}',
            ${a.vagas}
          )">
            editar
          </button>

          <button onclick="deleteAula(${a.id})">
            apagar
          </button>
        `;
      }

      html += `
          </span>

        </li>
      `;
    });

    html += "</ul>";

    document.getElementById("aulas").innerHTML = html;
  });
}

function addAula() {

  const nome = prompt("Nome da aula");
  const horario = prompt("Horário");
  const vagas = prompt("Número de vagas");

  if (!nome || !horario || !vagas) {
    alert("Preenche tudo");
    return;
  }

  fetch("api/aulas_create.php", {
    method: "POST",
    headers: {"Content-Type":"application/x-www-form-urlencoded"},
    body: `nome=${nome}&horario=${horario}&vagas=${vagas}`
  })
  .then(() => {
    refreshEverything(); // 🔥 FIX
  });
}

function editAula(id,nome,horario,vagas) {

  const n = prompt("Nome", nome);

  const h = prompt("Horário", horario);

  const v = prompt("Vagas", vagas);

  fetch("api/aulas_update.php", {

    method: "POST",

    headers: {
      "Content-Type":
      "application/x-www-form-urlencoded"
    },

    body:
      `id=${id}` +
      `&nome=${n}` +
      `&horario=${h}` +
      `&vagas=${v}`

  })

  .then(() => {

    loadAulas();
    loadStats();

  });

}

function deleteAula(id) {

  if (!confirm("Apagar aula?")) return;

  fetch("api/aulas_delete.php?id=" + id)

  .then(() => {

    loadAulas();
    loadStats();

  });

}

function loadTreinos() {

  fetch("api/treinos_list.php")
  .then(r=>r.json())
  .then(data=>{
    let html=`
    <h3>Treinos</h3>

    <select id="membroSelect"></select>

    <button onclick="addTreino()">
    + adicionar treino
    </button>

    <ul>`;

    data.forEach(t=>{
      html += `
      <li>
      <div class="content"><b>${t.nome}</b>- ${t.descricao}</div>

      <div class="actions">${role==="admin"?`
      <button onclick="editTreino(${t.id},'${t.descricao}')">
        editar
      </button>
      <button class="delete" onclick="deleteTreino(${t.id})">
        apagar
      </button>`:""}</div>
      </li>`;
    });

  html+="</ul>";

  document.getElementById("treinos").innerHTML=html;

/* ESTA LINHA É O FIX */
preencherDropdowns();

});

}

function addTreino() {

  const select = document.getElementById("membroSelect");

  if (!select) return alert("Erro dropdown");

  const membro_id = select.value;

  if (!membro_id) return alert("Escolhe um membro");

  const descricao = prompt("Descrição do treino");

  if (!descricao) return alert("Escreve descrição");

  fetch("api/treinos_create.php", {
    method: "POST",
    headers: {"Content-Type":"application/x-www-form-urlencoded"},
    body: `membro_id=${membro_id}&descricao=${descricao}`
  })
  .then(() => {

    loadTreinos();

    loadStats();

    preencherDropdowns();

  });
}

function editTreino(id, descricao) {

  const d = prompt("Descrição", descricao);

  fetch("api/treinos_update.php", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    },
    body:
      `id=${id}&descricao=${d}`
  })

  .then(() => {
    loadTreinos();
    loadStats();
  });
}

function deleteTreino(id) {

  if (!confirm("Apagar treino?")) return;

  fetch("api/treinos_delete.php?id=" + id)
  .then(() => {
    loadTreinos();
    loadStats();
  });
}

function loadPagamentos(){

  fetch("api/pagamentos_list.php")
  .then(r=>r.json())
  .then(data=>{

    let html=`
    <h3>Pagamentos</h3>

    <select id="membroSelectPagamentos"></select>

    <button onclick="addPagamento()">
    + registar pagamento
    </button>

    <ul>`;

    data.forEach(p=>{
      html += `
      <li>
      <div class="content"><b>${p.nome}</b>-${p.valor}€-${p.data_pagamento}</div>
      <div class="actions">${role==="admin"?`
      <button onclick="editPagamento(${p.id},${p.valor},'${p.data_pagamento}')">
        editar
      </button>
      <button class="delete"onclick="deletePagamento(${p.id})">
        apagar
      </button>`:""
      }
      </div>
      </li>`;
    });

    html+="</ul>";
    document.getElementById(
      "pagamentos"
    ).innerHTML=html;

    /* FIX */
    preencherDropdowns();

  });

}

function addPagamento() {

  const select = document.getElementById("membroSelectPagamentos");

  if (!select) return alert("Erro dropdown");

  const membro_id = select.value;

  if (!membro_id) return alert("Escolhe um membro");

  const valor = prompt("Valor");
  const data = prompt("Data");

  fetch("api/pagamentos_create.php", {
    method: "POST",
    headers: {"Content-Type":"application/x-www-form-urlencoded"},
    body: `membro_id=${membro_id}&valor=${valor}&data=${data}`
  })
  .then(() => {

  loadPagamentos();

  loadStats();

  preencherDropdowns();

  });
}

function editPagamento(id, valor, data) {

  const v = prompt("Valor", valor);
  const d = prompt("Data", data);

  fetch("api/pagamentos_update.php", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    },
    body:
      `id=${id}&valor=${v}&data=${d}`
  })

  .then(() => {
    loadPagamentos();
    loadStats();
  });
}

function deletePagamento(id) {

  if (!confirm("Apagar pagamento?")) return;

  fetch("api/pagamentos_delete.php?id=" + id)
  .then(() => {
    loadPagamentos();
    loadStats();
  });
}

function preencherDropdowns(){

  const t=document.getElementById("membroSelect");
  const p=document.getElementById("membroSelectPagamentos");

  if(t){
    t.innerHTML="";
    membrosCache.forEach(m=>{
      t.innerHTML+=`<option value="${m.id}">${m.nome}</option>`;

    });

  }

  if(p){
    p.innerHTML="";
    membrosCache.forEach(m=>{p.innerHTML+=`<option value="${m.id}">${m.nome}</option>`;});
  }
}

function refreshEverything() {

  loadMembros();     
  loadTreinos();
  loadPagamentos();
  loadStats();
}
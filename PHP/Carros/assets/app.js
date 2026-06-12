let role = null;
console.log("APP.JS CARREGADO");
let filtroInicio = "";
let filtroFim = "";
let clientesCache = [];
let veiculosCache = [];
let reservasCache = [];

/* ================= LOGIN ================= */

function login() {
  fetch("api/login.php", {
    method: "POST",
    headers: {"Content-Type":"application/x-www-form-urlencoded"},
    body:
      "user=" + encodeURIComponent(user.value) +
      "&pass=" + encodeURIComponent(pass.value)
  })
  .then(r => r.json())
  .then(data => {

    if (!data.success) {
      alert("Login inválido");
      return;
    }

    role = data.role;

    document.getElementById("login").style.display = "none";
    document.getElementById("app").style.display = "block";

    refreshAll();
  });
}

/* ================= LOGOUT ================= */

function logout() {
  fetch("api/logout.php").then(() => location.reload());
}

/* ================= REFRESH GLOBAL ================= */

function refreshAll() {
  loadClientes();
  loadVeiculos();
  loadReservas();
  loadPagamentos();
  loadStats();
}

/* ================= STATS ================= */

function loadStats() {
  fetch("api/stats.php")
  .then(r => r.json())
  .then(data => {

    document.getElementById("stats").innerHTML = `
      <div class="cards">
        <div class="card"><h3>Clientes</h3><p>${data.clientes}</p></div>
        <div class="card"><h3>Veículos</h3><p>${data.veiculos}</p></div>
        <div class="card"><h3>Reservas</h3><p>${data.reservas}</p></div>
        <div class="card"><h3>Receita</h3><p>${data.receita} €</p></div>
      </div>
    `;
  });
}

/* ================= CLIENTES ================= */

function loadClientes() {

  fetch("api/clientes_list.php")
  .then(r => r.json())
  .then(res => {

    clientesCache = res.data;

    let html = "<h3>Clientes</h3>";

    if (role === "admin") {
      html += `<button onclick="addCliente()">+ Cliente</button>`;
    }

    html += "<ul>";

    res.data.forEach(c => {

      html += `
        <li>
          <div class="content">
            <b>${c.nome}</b> | ${c.email} | ${c.telefone}
          </div>

          <div class="actions">

            ${role === "admin" ? `
              
              <button onclick="editCliente(
                ${c.id},
                '${c.nome}',
                '${c.email}',
                '${c.telefone}'
              )">
                editar
              </button>

              <button class="delete" onclick="deleteCliente(${c.id})">
                apagar
              </button>

            ` : ""}

          </div>
        </li>
      `;
    });

    html += "</ul>";

    document.getElementById("clientes").innerHTML = html;
  });
}

function addCliente() {
  const nome = prompt("Nome");
  const email = prompt("Email");
  const telefone = prompt("Telefone");

  fetch("api/clientes_create.php", {
    method: "POST",
    headers: {"Content-Type":"application/x-www-form-urlencoded"},
    body: `nome=${nome}&email=${email}&telefone=${telefone}`
  }).then(refreshAll);
}

function deleteCliente(id) {
  fetch("api/clientes_delete.php?id=" + id)
  .then(refreshAll);
}

function editCliente(id, nome, email, telefone) {

  const n = prompt("Nome", nome);
  const e = prompt("Email", email);
  const t = prompt("Telefone", telefone);

  if (!n || !e || !t) return;

  fetch("api/clientes_update.php", {
    method: "POST",
    headers: {"Content-Type":"application/x-www-form-urlencoded"},
    body: `id=${id}&nome=${n}&email=${e}&telefone=${t}`
  })
  .then(() => refreshAll());
}

/* ================= VEÍCULOS ================= */

function loadVeiculos() {

  fetch("api/veiculos_list.php")
  .then(r => r.json())
  .then(res => {

    veiculosCache = res.data;

    let html = "<h3>Veículos</h3>";

    html += `
      <input 
        id="searchVeiculo" 
        placeholder="Pesquisar marca ou modelo..." 
        oninput="filtrarVeiculos()"
      >
    `;

    if (role === "admin") {
      html += `<button onclick="addVeiculo()">+ Veículo</button>`;
    }

    html += "<ul id='listaVeiculos'>";

    res.data.forEach(v => {

      html += `
        <li>
          <div class="content">
            <b>${v.marca} ${v.modelo}</b> | ${v.matricula} | ${v.preco_dia}€/dia
          </div>

          <div class="actions">
            ${role === "admin" ? `
              <button onclick="editVeiculo(
                ${v.id},
                '${v.marca}',
                '${v.modelo}',
                '${v.matricula}',
                ${v.preco_dia}
              )">editar</button>

              <button class="delete" onclick="deleteVeiculo(${v.id})">
                apagar
              </button>
            ` : ""}
          </div>
        </li>
      `;
    });

    html += "</ul>";

    document.getElementById("veiculos").innerHTML = html;
  });
}

function filtrarVeiculos() {
  const termo = document.getElementById("searchVeiculo").value.toLowerCase();

  const filtrados = veiculosCache.filter(v =>
    v.marca.toLowerCase().includes(termo) ||
    v.modelo.toLowerCase().includes(termo)
  );

  renderVeiculos(filtrados);
}

function renderVeiculos(lista) {

  let html = "<h3>Veículos</h3>";

  html += `<input id="searchVeiculo" oninput="filtrarVeiculos()">`;

  if (role === "admin") {
    html += `<button onclick="addVeiculo()">+ Veículo</button>`;
  }

  html += "<ul>";

  lista.forEach(v => {
    html += `
      <li>
        <div class="content">
          <b>${v.marca} ${v.modelo}</b>
        </div>
      </li>
    `;
  });

  html += "</ul>";

  document.getElementById("veiculos").innerHTML = html;
}

function addVeiculo() {
  const marca = prompt("Marca");
  const modelo = prompt("Modelo");
  const matricula = prompt("Matrícula");
  const preco = prompt("Preço/dia");

  fetch("api/veiculos_create.php", {
    method: "POST",
    headers: {"Content-Type":"application/x-www-form-urlencoded"},
    body: `marca=${marca}&modelo=${modelo}&matricula=${matricula}&preco=${preco}`
  }).then(refreshAll);
}

function deleteVeiculo(id) {
  fetch("api/veiculos_delete.php?id=" + id)
  .then(refreshAll);
}

/* ================= RESERVAS ================= */

function loadReservas() {

  fetch("api/reservas_list.php")
  .then(r => r.json())
  .then(data => {

    reservasCache = data;

    let html = "<h3>Reservas</h3>";

    html += `
      <select id="clienteSelect"></select>
      <select id="veiculoSelect"></select>

      <input type="date" id="inicio">
      <input type="date" id="fim">

      <button onclick="addReserva()">+ Reservar</button>
    `;

    html += "<ul>";

    data.forEach(r => {
      html += `
        <li>
          <b>${r.cliente_nome}</b>
          → ${r.marca} ${r.modelo}
          | ${r.data_inicio} → ${r.data_fim}

          ${role === "admin" ? `
            <button class="delete" onclick="deleteReserva(${r.id})">
              apagar
            </button>
          ` : ""}
        </li>
      `;
    });

    html += "</ul>";

    document.getElementById("reservas").innerHTML = html;

    preencherDropdowns();
    preencherReservasDropdown();

  });
}

function addReserva() {
  const cliente_id = clienteSelect.value;
  const veiculo_id = veiculoSelect.value;
  const inicio = document.getElementById("inicio").value;
  const fim = document.getElementById("fim").value;

  fetch("api/reservas_create.php", {
    method: "POST",
    headers: {"Content-Type":"application/x-www-form-urlencoded"},
    body: `cliente_id=${cliente_id}&veiculo_id=${veiculo_id}&data_inicio=${inicio}&data_fim=${fim}`
  }).then(r => r.json())
    .then(res => {
      if (!res.success) {
        alert(res.msg);
        return;
      }

      refreshAll();
    });
}
function deleteReserva(id) {
  fetch("api/reservas_delete.php?id=" + id)
  .then(refreshAll);
}

function editVeiculo(id, marca, modelo, matricula, preco) {

  const m = prompt("Marca", marca);
  const mo = prompt("Modelo", modelo);
  const ma = prompt("Matrícula", matricula);
  const p = prompt("Preço/dia", preco);

  if (!m || !mo || !ma || !p) return;

  fetch("api/veiculos_update.php", {
    method: "POST",
    headers: {"Content-Type":"application/x-www-form-urlencoded"},
    body: `id=${id}&marca=${m}&modelo=${mo}&matricula=${ma}&preco=${p}`
  })
  .then(() => refreshAll());
}
/* ================= PAGAMENTOS ================= */

function loadPagamentos() {

  fetch("api/pagamentos_list.php")
  .then(r => r.json())
  .then(data => {

    let html = "<h3>Pagamentos</h3>";

    html += `
      <select id="reservaSelect"></select>
      <input id="valor" placeholder="Valor (€)">
      <input type="date" id="data_pagamento">
      <button onclick="addPagamento()">+ Pagamento</button>
    `;

    html += "<ul>";

    data.forEach(p => {

      html += `
        <li>
          <div class="content">
            <b>${p.cliente_nome}</b>
            → ${p.marca} ${p.modelo}
            | ${p.valor}€
            | ${p.data_pagamento}
          </div>

          <div class="actions">
            <button class="delete" onclick="deletePagamento(${p.id})">
              apagar
            </button>
          </div>
        </li>
      `;
    });

    html += "</ul>";

    document.getElementById("pagamentos").innerHTML = html;

    preencherReservasDropdown();
  });
}

function addPagamento() {

  const reserva_id = document.getElementById("reservaSelect").value;
  const valor = document.getElementById("valor").value;
  const data_pagamento = document.getElementById("data_pagamento").value;

  if (!reserva_id || !valor || !data_pagamento) {
    alert("Preenche todos os campos");
    return;
  }

  fetch("api/pagamentos_create.php", {
    method: "POST",
    headers: {"Content-Type":"application/x-www-form-urlencoded"},
    body: `reserva_id=${reserva_id}&valor=${valor}&data_pagamento=${data_pagamento}`
  })
  .then(r => r.json())
  .then(res => {

    if (!res.success) {
      alert(res.msg || "Erro ao criar pagamento");
      return;
    }

    loadPagamentos();
    loadStats();
  });
}

function deletePagamento(id) {

  if (!confirm("Tens a certeza que queres apagar este pagamento?")) return;

  fetch("api/pagamentos_delete.php?id=" + id)
  .then(r => r.json())
  .then(res => {

    if (!res.success) {
      alert("Erro ao apagar pagamento");
      return;
    }

    loadPagamentos();
    loadStats();
  })
  .catch(err => {
    console.error(err);
    alert("Erro no servidor");
  });
}

/* ================= DROPDOWNS ================= */

function preencherDropdowns() {

  const c = document.getElementById("clienteSelect");
  const v = document.getElementById("veiculoSelect");

  if (c) {
    c.innerHTML = "";
    clientesCache.forEach(x => {
      c.innerHTML += `<option value="${x.id}">${x.nome}</option>`;
    });
  }

  if (v) {
    v.innerHTML = "";
    veiculosCache.forEach(x => {
      v.innerHTML += `<option value="${x.id}">${x.marca} ${x.modelo}</option>`;
    });
  }
}

function preencherReservasDropdown() {

  const select = document.getElementById("reservaSelect");

  if (!select) return;

  fetch("api/reservas_list.php")
  .then(r => r.json())
  .then(data => {

    select.innerHTML = "";

    data.forEach(r => {

      select.innerHTML += `
        <option value="${r.id}">
          ${r.cliente_nome} → ${r.marca} ${r.modelo} (${r.data_inicio} a ${r.data_fim})
        </option>
      `;
    });

  });
}

function loadCalendar() {

  console.log("LOAD CALENDAR OK");

  const inicio = filtroInicio;
  const fim = filtroFim;

  console.log("INICIO:", inicio);
  console.log("FIM:", fim);
  console.log("VEICULOS:", veiculosCache.length);
  console.log("RESERVAS:", reservasCache.length);

  const result = document.getElementById("calendarResult");

  if (!result) return;

  // validação de datas
  if (!inicio || !fim) {
    result.innerHTML = "<p>Seleciona as datas primeiro.</p>";
    return;
  }

  // validação de dados
  if (!Array.isArray(veiculosCache) || veiculosCache.length === 0) {
    result.innerHTML = "<p>Veículos ainda não carregados.</p>";
    return;
  }

  if (!Array.isArray(reservasCache)) {
    result.innerHTML = "<p>Reservas inválidas.</p>";
    return;
  }

  const inicioDate = new Date(inicio);
  const fimDate = new Date(fim);

  let html = `<p><b>${inicio} → ${fim}</b></p>`;
  html += "<div class='calendar-grid'>";

  veiculosCache.forEach(v => {

    const ocupado = reservasCache.some(r => {

      if (Number(r.veiculo_id) !== Number(v.id)) return false;

      const rInicio = new Date(r.data_inicio);
      const rFim = new Date(r.data_fim);

      // overlap de datas
      return (rInicio <= fimDate && rFim >= inicioDate);
    });

    html += `
      <div class="calendar-card">
        <h3>${v.marca} ${v.modelo}</h3>
        <p>${ocupado ? "🔴 Ocupado" : "🟢 Disponível"}</p>
      </div>
    `;
  });

  html += "</div>";

  result.innerHTML = html;
}

function isVeiculoDisponivel(veiculo_id, inicio, fim, reservas) {

  const inicioC = new Date(inicio);
  const fimC = new Date(fim);

  return !reservas.some(r => {

    if (Number(r.veiculo_id) !== Number(veiculo_id)) return false;

    const inicioR = new Date(r.data_inicio);
    const fimR = new Date(r.data_fim);

    return (inicioR <= fimC && fimR >= inicioC);
  });
}

function tryLoadCalendar() {
  if (veiculosCache.length > 0 && reservasCache.length > 0) {
    loadCalendar();
  }
}

window.loadCalendar = loadCalendar;

document.addEventListener("DOMContentLoaded", () => {

  const inicioEl = document.getElementById("inicio");
  const fimEl = document.getElementById("fim");

  if (inicioEl) {
    inicioEl.addEventListener("input", (e) => {
      filtroInicio = e.target.value;
    });
  }

  if (fimEl) {
    fimEl.addEventListener("input", (e) => {
      filtroFim = e.target.value;
    });
  }

});
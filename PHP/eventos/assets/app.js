let state = {
  events: [],
  tickets: []
};

let role = null;

window.onload = () => checkAuth();

/* AUTH */
function checkAuth() {

  fetch("api/me.php")

  .then(r => r.json())

  .then(data => {

    console.log("ME:", data);

    if (!data.logged) {

      document.getElementById("login").style.display = "block";

      document.getElementById("app").style.display = "none";

      return;
    }

    role = data.role;

    document.getElementById("login").style.display = "none";

    document.getElementById("app").style.display = "block";

    document.getElementById("roleInfo").innerText =
      "👤 " + role;

    /* ADMIN */
    document.getElementById("adminPanel").style.display =
      role === "admin"
        ? "block"
        : "none";

    /* STATS */
    document.getElementById("statsPanel").style.display =
      role === "admin"
        ? "block"
        : "none";

    loadAll();

  })

  .catch(err => {

    console.error("AUTH ERROR:", err);

  });

}

function login() {

  const username =
    document.getElementById("username").value;

  const password =
    document.getElementById("password").value;

  fetch("api/login.php", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    },
    body:
      "username=" + encodeURIComponent(username) +
      "&password=" + encodeURIComponent(password)
  })

  .then(r => r.text())   // 👈 MUITO IMPORTANTE
  .then(text => {

    console.log("RAW LOGIN RESPONSE:", text);

    let data;

    try {
      data = JSON.parse(text);
    } catch (e) {
      console.error("JSON inválido:", text);
      return;
    }

    console.log("LOGIN:", data);

    if (!data.success) {
      alert(data.msg || "Login inválido");
      return;
    }

    checkAuth();

  })

  .catch(err => {
    console.error("LOGIN ERROR:", err);
  });

}

function applyRoleUI() {

  document.getElementById("roleInfo").innerText = role;

  const adminPanel =
    document.getElementById("adminPanel");

  const statsPanel =
    document.getElementById("statsPanel");

  const scannerPanel =
    document.getElementById("scannerPanel");

  // ADMIN
  if (role === "admin") {

    if (adminPanel)
      adminPanel.style.display = "block";

    if (statsPanel)
      statsPanel.style.display = "block";
  }

  // STAFF
  else if (role === "staff") {

    if (scannerPanel)
      scannerPanel.style.display = "block";
  }

  // USER
  else if (role === "user") {

    if (adminPanel)
      adminPanel.style.display = "none";

    if (statsPanel)
      statsPanel.style.display = "none";
  }
}

function loginUI(state) {
  document.getElementById("login").style.display = state ? "none" : "block";
  document.getElementById("app").style.display = state ? "block" : "none";
}

function logout() {

  fetch("api/logout.php", {
    credentials: "include"
  })
  .then(() => location.reload());
}
/* LOAD */
function loadAll() {
  loadEvents();
  loadTickets();
  loadStats(); // 🔥 novo
}

/* EVENTS */
function loadEvents() {

  fetch("api/events_list.php")
    .then(r => r.text())
    .then(text => {

      console.log("RAW EVENTS:", text);

      let data;

      try {
        data = JSON.parse(text);
      } catch (e) {
        console.error("JSON FAIL:", text);
        return;
      }

      state.events = data;

      const html = data.length === 0
        ? "<p>Sem eventos</p>"
        : data.map(ev => `
            <div class="card">
              <h3>${ev.name}</h3>
              <p>${ev.event_date}</p>

              <button onclick="buyTicket(${ev.id})">
                🎟 Comprar bilhete
              </button>

              <button onclick="openEvent(${ev.id})">
                ℹ Ver detalhes
              </button>
            </div>
          `).join("");

      document.getElementById("events").innerHTML = html;
    });
}

/* BUY */
function buyTicket(event_id){

  if(role==="staff"){
    alert("Staff não compra bilhetes");
    return;
  }

  fetch("api/tickets_create.php",{
    method:"POST",
    headers:{"Content-Type":"application/x-www-form-urlencoded"}, 
    body:"event_id="+event_id
  })
  .then(r=>r.json())
  .then(res=>{
    if(!res.success){
      alert(res.msg);
      return;
    }

  alert("Bilhete criado!");
  loadTickets();
  loadEvents();

  if(role==="admin")
    loadStats();
  });
}

/* TICKETS */
function loadTickets() {

  fetch("api/tickets_list.php?nocache=" + Date.now())
    .then(r => r.json())
    .then(data => {

      let html = "";

      data.forEach(t => {

        const isUsed = t.checked_in == 1;

        html += `
          <div class="ticket">

            <p><b>Evento:</b> ${t.event_title}</p>
            <p><b>QR:</b> ${t.qr_code}</p>

            <p>
              <b>Status:</b>
              ${isUsed ? "✅ Usado" : "⏳ Válido"}
            </p>

            <button onclick="checkIn('${t.qr_code}')">
              Check-in
            </button>

            <button onclick="cancelTicket('${t.qr_code}')"
              style="background:red;margin-top:5px;">
              Cancelar
            </button>

          </div>
        `;
      });

      document.getElementById("tickets").innerHTML = html;
    });
}

/* CHECKIN */
function checkIn(qr_code){
  if(role!=="admin" && role!=="staff"){
    alert("Sem permissão");
  return;
  }

  fetch("api/checkin_qr.php",{
    method:"POST",
    headers:{"Content-Type":"application/x-www-form-urlencoded"},
    body:"qr_code="+encodeURIComponent(qr_code)
  })

  .then(r=>r.json())
  .then(res=>{
    if(!res.success){
      alert(res.msg);
      return;
    }
    alert("Check-in OK!");
    loadTickets();
    if(role==="admin")
      loadStats();
  });

}

function loadStats() {

  fetch("api/stats.php")
    .then(r => r.json())
    .then(s => {

      if (!s.success) {
        document.getElementById("stats").innerHTML = "Erro stats";
        return;
      }

      document.getElementById("stats").innerHTML = `
        <p>📅 Eventos: ${s.events}</p>
        <p>🎟️ Bilhetes: ${s.tickets}</p>
        <p>✅ Check-ins: ${s.checkins}</p>
        <p>📊 Ocupação: ${s.occupancy ?? 0}%</p>
      `;
    });
}

function toggleCreateEvent() {
  const box = document.getElementById("createEventBox");
  box.style.display = box.style.display === "none" ? "block" : "none";
}

function submitCreateEvent() {

  const payload = new URLSearchParams();

  payload.append("name", document.getElementById("ev_name").value);
  payload.append("location", document.getElementById("ev_location").value);
  payload.append("event_date", document.getElementById("ev_date").value);
  payload.append("capacity", document.getElementById("ev_capacity").value);
  payload.append("price", document.getElementById("ev_price").value);

  console.log("A enviar:", Object.fromEntries(payload));

  fetch("api/events_create.php", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    },
    body: payload.toString()
  })
  .then(r => r.text())
  .then(text => {

    console.log("RAW RESPONSE:", text);

    let res;

    try {
      res = JSON.parse(text);
    } catch (e) {
      console.error("Resposta inválida:", text);
      alert("Erro no servidor (resposta inválida)");
      return;
    }

    if (!res.success) {
      alert(res.msg || "Erro ao criar evento");
      return;
    }

    alert("Evento criado!");

    // 🔥 FECHA FORM
    document.getElementById("createEventBox").style.display = "none";

    // 🔥 ATUALIZA TUDO
    loadEvents();  // eventos atualizados
    loadStats();   // estatísticas atualizadas
    loadTickets?.(); // segurança (caso exista tickets dependentes)

  })
  .catch(err => {
    console.error("Erro fetch:", err);
    alert("Erro de ligação ao servidor");
  });
}

function openEvent(id) {

  const ev = state.events.find(e => e.id == id);

  if (!ev) return;

  document.getElementById("eventDetail").innerHTML = `
    <h3>${ev.name}</h3>
    <p><b>Local:</b> ${ev.location}</p>
    <p><b>Data:</b> ${ev.event_date}</p>
    <p><b>Preço:</b> ${ev.price}€</p>
    <p><b>Capacidade:</b> ${ev.capacity}</p>
  `;
}

function cancelTicket(qr_code) {

  if (!confirm("Cancelar este bilhete?")) return;

  fetch("api/tickets_cancel.php", {
    method: "POST",
    headers: {"Content-Type":"application/x-www-form-urlencoded"},
    body: "qr_code=" + encodeURIComponent(qr_code)
  })
  .then(r => r.json())
  .then(res => {

    console.log("CANCEL:", res);

    if (!res.success) {
      alert(res.msg || "Erro ao cancelar");
      return;
    }

    alert("Bilhete cancelado!");

    loadTickets(); // desaparece da lista
    loadStats();   // atualiza stats
  })
  .catch(console.error);
}

function cancelEvent(id){

  if(role!=="admin"){
    alert("Só admin");
    return;
  }
  fetch("api/events_delete.php",{
    method:"GET"
  })
  .then(()=>{
    loadEvents();
    loadStats();
  });
}
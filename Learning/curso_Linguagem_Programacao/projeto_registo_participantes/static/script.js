
const formulario = document.getElementById("formulario");
const mensagem = document.getElementById("mensagem");
const lista = document.getElementById("lista-participantes");


async function carregarParticipantes() {
    try {
        const resposta = await fetch("/participantes");

        if (!resposta.ok) {
            throw new Error("Erro ao carregar participantes.");
        }

        const participantes = await resposta.json();

        lista.replaceChildren();

        participantes.forEach(participante => {
            const item = document.createElement("li");

            item.textContent =
                `${participante.nome} | ${participante.email} | ` +
                `${participante.codigo_postal} | ${participante.localidade}`;

            lista.appendChild(item);
        });

    } catch (erro) {
        mensagem.textContent = erro.message;
    }
}


formulario.addEventListener("submit", async function(evento) {
    evento.preventDefault();

    const participante = {
        nome: document.getElementById("nome").value,
        email: document.getElementById("email").value,
        codigo_postal: document.getElementById("codigo_postal").value
    };

    try {
        const resposta = await fetch("/registar", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(participante)
        });

        const resultado = await resposta.json();

        if (!resposta.ok) {
            throw new Error(resultado.erro || "Erro no registo.");
        }

        mensagem.textContent = resultado.mensagem;

        formulario.reset();

        await carregarParticipantes();

    } catch (erro) {
        mensagem.textContent = erro.message;
    }
});


carregarParticipantes();

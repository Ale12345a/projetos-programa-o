/**
 * CONTEXTO
 *
 * Este contador funciona em modo cliente-servidor.
 *
 * - Os botões enviam comandos HTTP (POST) para o servidor
 * - O servidor mantém o estado do contador
 * - O valor é recebido em tempo real via Server-Sent Events (SSE)
 *
 * Objetivos de estudo:
 * - Classes em JavaScript
 * - Manipulação dinâmica do DOM
 * - Uso de bind(this) para preservar contexto
 * - Comunicação HTTP (fetch)
 * - Programação reativa com EventSource
 */

class Counter {
    constructor(parentId, host, port) {
        this.host = host;
        this.port = port;

        const parent = document.getElementById(parentId);

        // Elementos DOM
        const counter = document.createElement("div");
        const incr = document.createElement("div");
        const reset = document.createElement("div");
        this.display = document.createElement("div");

        // Estrutura
        parent.appendChild(counter);
        counter.appendChild(incr);
        counter.appendChild(reset);
        counter.appendChild(this.display);

        // Classes CSS
        counter.className = "counter";
        incr.className = "button";
        reset.className = "button";
        this.display.className = "display";

        // Texto
        incr.innerText = "Incr";
        reset.innerText = "Reset";
        this.display.innerText = "0";

        // Eventos (bind preserva o this)
        incr.onclick = this.increment.bind(this);
        reset.onclick = this.reset.bind(this);

        // Server-Sent Events
        const url = `http://${this.host}:${this.port}/update`;
        const source = new EventSource(url);

        source.onmessage = event => {
            this.display.innerText = event.data;
        };
    }

    increment() {
        this.do("incr");
    }

    reset() {
        this.do("reset");
    }

    do(command) {
        const url = `http://${this.host}:${this.port}/${command}`;

        fetch(url, { method: "POST" })
            .catch(console.error);
    }
}

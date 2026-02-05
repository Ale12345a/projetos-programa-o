// ==================================================
// TELA 1 — FUNDAMENTOS
// ==================================================
const tela1 = document.getElementById("tela1");
const gc1 = tela1.getContext("2d");

function desenharTela1(gc) {
    // Texto
    gc.fillStyle = "#228";
    gc.font = "32px serif";
    gc.fillText("Olá mundo!", 50, 60);

    // Retângulos
    gc.strokeStyle = "#228";
    gc.strokeRect(20, 20, 100, 40);
    gc.fillRect(20, 80, 100, 40);
    gc.fillRect(140, 20, 100, 100);
    gc.clearRect(160, 40, 60, 60);

    // Centro
    gc.fillRect(140, 90, 20, 20);

    // Paleta
    for (let hue = 0; hue < 240; hue++) {
        gc.fillStyle = `hsl(${hue},100%,50%)`;
        gc.fillRect(10 + hue, 10, 1, 140);
    }
    gc.strokeStyle = "#228";
    gc.strokeRect(10, 10, 240, 140);
    gc.strokeStyle = "orange";
    gc.strokeRect(11, 11, 239, 139);

    // TextAlign
    gc.font = "24px Arial";
    gc.textAlign = "left";
    gc.fillStyle = "#228";
    gc.fillText("the quick", 100, 60);
    gc.strokeText("brown fox", 100, 100);
    gc.textAlign = "center";
    gc.fillStyle = "orange";
    gc.fillText("jumped over", 100, 140);
    gc.textAlign = "right";
    gc.strokeText("the lazy dog", 100, 180);
}

desenharTela1(gc1);

// ==================================================
// TELA 2 — CURVAS E ARCOS
// ==================================================
const tela2 = document.getElementById("tela2");
const gc2 = tela2.getContext("2d");

function desenharTela2(gc) {
    gc.lineWidth = 3;

    // arcTo
    gc.strokeStyle = "purple";
    gc.beginPath();
    gc.moveTo(20, 20);
    gc.arcTo(140, 20, 140, 140, 100);
    gc.stroke();

    // Quadrática
    gc.strokeStyle = "green";
    gc.beginPath();
    gc.moveTo(20, 80);
    gc.quadraticCurveTo(180, 20, 240, 140);
    gc.stroke();

    // Bezier
    gc.strokeStyle = "orange";
    gc.beginPath();
    gc.moveTo(20, 80);
    gc.bezierCurveTo(180, 20, 140, 100, 240, 140);
    gc.stroke();

    // Arcos
    gc.strokeStyle = "blue";
    gc.beginPath();
    gc.arc(60, 60, 40, 0, 2 * Math.PI);
    gc.stroke();

    gc.fillStyle = "red";
    gc.beginPath();
    gc.arc(160, 60, 40, 0, Math.PI, true);
    gc.fill();

    gc.fillStyle = "orange";
    gc.beginPath();
    gc.arc(160, 120, 40, 0, Math.PI, false);
    gc.fill();
}

desenharTela2(gc2);

// ==================================================
// TELA 3 — TEMPORIZAÇÃO + ANIMAÇÃO
// ==================================================
const tela3 = document.getElementById("tela3");
const gc3 = tela3.getContext("2d");

class Watch {
    constructor(gc, x, y) {
        this.gc = gc;
        this.x = x;
        this.y = y;
        this.start = Date.now();
        setInterval(this.show.bind(this), 1000);
    }

    show() {
        const t = Math.floor((Date.now() - this.start) / 1000);
        this.gc.clearRect(this.x, this.y - 20, 200, 30);
        this.gc.font = "20px Arial";
        this.gc.fillStyle = "blue";
        this.gc.fillText(`Tempo: ${t}s`, this.x, this.y);
    }
}

new Watch(gc3, 20, 40);

class WindMill {
    constructor(gc) {
        this.gc = gc;
        this.angle = 0;
        this.play();
    }

    play() {
        this.gc.clearRect(0, 0, tela3.width, tela3.height);

        const cx = 200;
        const cy = 100;

        for (let i = 0; i < 4; i++) {
            const a = this.angle + i * Math.PI / 2;
            this.gc.beginPath();
            this.gc.moveTo(cx, cy);
            this.gc.lineTo(cx + 50 * Math.cos(a), cy + 50 * Math.sin(a));
            this.gc.stroke();
        }

        this.angle += 0.05;
        requestAnimationFrame(this.play.bind(this));
    }
}

new WindMill(gc3);

// ==================================================
// TELA 4 — SOBREPOSIÇÃO
// ==================================================
const tela4 = document.getElementById("tela4");
const gc4 = tela4.getContext("2d");

function desenharTela4() {
    // Fundo semi-transparente
    gc4.fillStyle = "rgba(255,0,0,0.2)";
    gc4.fillRect(20, 20, 250, 60);

    // Linha horizontal
    gc4.strokeStyle = "blue";
    gc4.lineWidth = 3;
    gc4.beginPath();
    gc4.moveTo(10, 120);
    gc4.lineTo(340, 120);
    gc4.stroke();

    // Texto
    gc4.font = "24px Arial";
    gc4.fillStyle = "green";
    gc4.fillText("Canvas por cima!", 60, 200);
}

desenharTela4();

// Adicionar linhas horizontais (altura de linha = 22px)
function lines(canvas, gc) {
    gc.strokeStyle = "orange";
    for (let y = 22; y < canvas.height; y += 22) {
        gc.beginPath();
        gc.moveTo(0, y);
        gc.lineTo(canvas.width, y);
        gc.stroke();
    }
}

lines(tela4, gc4);

// ==================================================
// TELA 5 — AMOSTRAS E TRANSFORMAÇÕES
// ==================================================
const tela5 = document.getElementById("tela5");
const gc5 = tela5.getContext("2d");

function sample(text) {
    gc5.font = "40px Arial";
    gc5.textBaseline = "top";
    gc5.strokeStyle = "orange";
    gc5.fillStyle = "#228";

    const m = gc5.measureText(text);
    gc5.fillText(text, 0, 0);
    gc5.strokeRect(0, 0, m.width, 40);
}

// Limpar canvas
gc5.clearRect(0, 0, tela5.width, tela5.height);

// Exemplos de transformações
gc5.save();
gc5.translate(20, 20);
sample("Isto é um teste");
gc5.restore();

gc5.save();
gc5.translate(50, 120);
gc5.rotate(-Math.PI / 12);
sample("Rotação");
gc5.restore();

gc5.save();
gc5.translate(220, 40);
gc5.scale(0.7, 0.7);
sample("Escala");
gc5.restore();

gc5.save();
gc5.translate(220, 160);
gc5.rotate(Math.PI / 10);
gc5.scale(0.6, 0.6);
sample("Composto");
gc5.restore();

// Homotetias
gc5.save();
gc5.translate(20, 220);
sample("teste 1");
gc5.translate(0, 40);
gc5.scale(2, 2);
sample("teste 2");
gc5.translate(60, -20);
gc5.scale(0.5, 0.5);
sample("teste 3");
gc5.translate(-120, 120);
gc5.scale(1, 0.5);
sample("teste 4");
gc5.translate(240, 0);
gc5.scale(-0.5, 2);
sample("teste 5");
gc5.restore();

// ==================================================
// TELA 6 — RELÓGIO ANALÓGICO
// ==================================================
class Watch6 {
    static numberSize = 14;
    static largeMarkSize = 10;
    static smallMarkSize = 5;

    constructor(canvasId) {
        this.base = document.getElementById(canvasId);
        this.gc = this.base.getContext("2d");
        this.center = { x: this.base.width / 2, y: this.base.height / 2 };
        this.length = Math.min(this.center.x, this.center.y);

        this.show();
        setInterval(this.show.bind(this), 1000);
    }

    show() {
        const gc = this.gc;
        this.base.width = this.base.width; // reset canvas
        gc.translate(this.center.x, this.center.y);
        gc.textAlign = "center";
        gc.textBaseline = "middle";
        gc.font = Watch6.numberSize + "pt Arial bold";

        this.face(gc);
        this.hands(gc);
    }

    face(gc) {
        const len = this.length - Watch6.numberSize - Watch6.largeMarkSize;
        gc.strokeStyle = "#228";
        gc.fillStyle = "#228";

        for (let minutes = 1; minutes <= 60; minutes++) {
            const angle = minutes * Math.PI / 30 - Math.PI / 2;
            const isHour = (minutes % 5) === 0;

            if (isHour) {
                const hour = minutes / 5;
                gc.save();
                gc.fillStyle = "orange";
                gc.fillText(hour, len * Math.cos(angle), len * Math.sin(angle));
                gc.restore();
            }

            // Marcas
            gc.save();
            gc.rotate(angle);
            gc.beginPath();
            gc.moveTo(this.length, 0);
            gc.lineTo(this.length - (isHour ? Watch6.largeMarkSize : Watch6.smallMarkSize), 0);
            gc.stroke();
            gc.restore();
        }
    }

    hands(gc) {
        const now = new Date();
        const seconds = now.getSeconds();
        const minutes = now.getMinutes() + seconds / 60;
        const hours = now.getHours() % 12 + minutes / 60;

        // Segundo
        this.hand(gc, seconds * Math.PI / 30 - Math.PI / 2, this.length * 0.9, 1, "red");
        // Minuto
        this.hand(gc, minutes * Math.PI / 30 - Math.PI / 2, this.length * 0.75, 3, "#228");
        // Hora
        this.hand(gc, hours * Math.PI / 6 - Math.PI / 2, this.length * 0.5, 5, "#228");

        // Centro
        gc.fillStyle = "#228";
        gc.beginPath();
        gc.arc(0, 0, 5, 0, 2 * Math.PI);
        gc.fill();
    }

    hand(gc, angle, length, width, color) {
        gc.save();
        gc.rotate(angle);
        gc.strokeStyle = color;
        gc.lineWidth = width;
        gc.beginPath();
        gc.moveTo(0, 0);
        gc.lineTo(length, 0);
        gc.stroke();
        gc.restore();
    }
}

new Watch6("tela6");

// ==================================================
// TELA 7 — CATAVENTO ANIMADO
// ==================================================
const tela7 = document.getElementById("tela7");

class WindMill7 {
    // Campos estáticos
    static radialVelocity = Math.PI / 100;

    // Campos da instância
    constructor(canvasId) {
        this.base = document.getElementById(canvasId);
        this.gc = this.base.getContext('2d');

        this.center = { 
            x: this.base.width / 2, 
            y: this.base.height / 2 
        };
        this.length = Math.min(this.center.x, this.center.y);
        this.alpha = 0; // ângulo inicial

        this.gc.fillStyle = 'orange';

        this.play();
    }

    // Método de animação
    play() {
        // Reset canvas
        this.base.width = this.base.width;

        // Mostrar frame
        this.show(this.gc);

        // Atualizar ângulo
        this.alpha -= WindMill7.radialVelocity;

        requestAnimationFrame(this.play.bind(this));
    }

    // Desenhar um frame
    show(gc) {
        this.stick(gc);

        for (let beta = this.alpha; beta < this.alpha + 2 * Math.PI; beta += Math.PI / 2) {
            this.sail(gc, beta);
        }

        this.centerCircle(gc, this.length / 5, '#228');
        this.centerCircle(gc, this.length / 9, 'orange');
        this.centerCircle(gc, this.length / 30, '#228');
    }

    // Stick central
    stick(gc) {
        gc.beginPath();
        gc.moveTo(this.center.x, 2 * this.center.y);
        gc.lineTo(this.center.x, this.center.y);
        gc.strokeStyle = '#228';
        gc.lineWidth = 10;
        gc.stroke();
    }

    // Desenhar vela
    sail(gc, beta) {
        const center = this.center;
        const length = this.length;

        const a = WindMill7.inLine(center, beta, length);
        const b = WindMill7.inLine(center, beta - Math.PI / 2, length / 2);

        const c1 = WindMill7.inLine(a, beta - 3 * Math.PI / 4, length / 2);
        const c2 = WindMill7.inLine(b, beta, length / 2);
        const c3 = WindMill7.inLine(center, beta - Math.PI / 4, length / 2);

        // Exterior
        gc.beginPath();
        gc.moveTo(center.x, center.y);
        gc.lineTo(a.x, a.y);
        gc.bezierCurveTo(c1.x, c1.y, c2.x, c2.y, b.x, b.y);
        gc.closePath();
        gc.fillStyle = '#228';
        gc.fill();

        // Interior
        gc.beginPath();
        gc.moveTo(center.x, center.y);
        gc.lineTo(a.x, a.y);
        gc.bezierCurveTo(c1.x, c1.y, c3.x, c3.y, center.x, center.y);
        gc.fillStyle = 'orange';
        gc.fill();
    }

    // Centro com círculos
    centerCircle(gc, radius, color) {
        gc.beginPath();
        gc.arc(this.center.x, this.center.y, radius, 0, 2 * Math.PI);
        gc.fillStyle = color;
        gc.fill();
    }

    // Método estático: ponto a distância l e ângulo a partir de p
    static inLine(p, a, l) {
        return { x: p.x + l * Math.cos(a), y: p.y + l * Math.sin(a) };
    }
}

// Inicializar catavento
new WindMill7("tela7");

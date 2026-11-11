
class Point {
    constructor(x, y) {
        this.x = x;
        this.y = y;
        this.type = "point";
    }
}

class Line {
    constructor(coordinates) {
        this.type = "line";
        this.points = coordinates.map(([x, y]) => new Point(x, y));
    }
}

class Figure {
    constructor(elements = []) {
        this.elements = {
            points: [],
            lines: []
        };

        elements.forEach(element => {
            if (element.type === "point") {
                this.elements.points.push(new Point(element.x, element.y));
            } else if (element.type === "line") {
                let coordinates = element.points.map(p => [p.x, p.y]);
                this.elements.lines.push(new Line(coordinates));
            }
        });

        this.removeDuplicates();
    }

    addPoint(x, y) {
        this.elements.points.push(new Point(x, y));
        this.removeDuplicates();
    }

    addLine(coordinates) {
        this.elements.lines.push(new Line(coordinates));
        this.removeDuplicates();
    }

    toJSON() {
        return JSON.stringify(this.elements);
    }

    fromJSON(json, add = false) {
        let data = JSON.parse(json);

        if (!add) {
            this.deleteAll();
        }

        if (data.points) {
            data.points.forEach(point => {
                this.elements.points.push(new Point(point.x, point.y));
            });
        }

        if (data.lines) {
            data.lines.forEach(line => {
                let coordinates = line.points.map(p => [p.x, p.y]);
                this.elements.lines.push(new Line(coordinates));
            });
        }

        this.removeDuplicates();
    }

    deleteAll() {
        this.elements.points = [];
        this.elements.lines = [];
    }

    sort() {
        // Ordenar pontos por x e depois por y
        this.elements.points.sort((a, b) =>
            a.x - b.x || a.y - b.y
        );

        // Ordenar linhas pelo primeiro ponto
        this.elements.lines.sort((a, b) => {
            let pointA = a.points[0];
            let pointB = b.points[0];

            return pointA.x - pointB.x || pointA.y - pointB.y;
        });
    }

    removeDuplicates() {
        // Remover pontos duplicados
        this.elements.points = this.elements.points.filter((point, index, array) =>
            index === array.findIndex(p =>
                p.x === point.x && p.y === point.y
            )
        );

        // Remover linhas duplicadas
        this.elements.lines = this.elements.lines.filter((line, index, array) =>
            index === array.findIndex(l =>
                l.points.length === line.points.length &&
                l.points.every((p, i) =>
                    p.x === line.points[i].x &&
                    p.y === line.points[i].y
                )
            )
        );
    }
}

// TESTES

let f = new Figure();

// Adicionar pontos
f.addPoint(10, 20);
f.addPoint(10, 20);
f.addPoint(5, 10);
f.addPoint(15, 5);

// Adicionar linhas
f.addLine([[0, 0], [10, 10]]);
f.addLine([[0, 0], [10, 10]]);
f.addLine([[5, 5], [20, 20]]);

// Mostrar coleção inicial (sem duplicados)
console.log("Pontos:", f.elements.points);
console.log("Linhas:", f.elements.lines);

// Ordenar
f.sort();

console.log("Pontos ordenados:", f.elements.points);
console.log("Linhas ordenadas:", f.elements.lines);

// Converter para JSON
let json = f.toJSON();
console.log("JSON:", json);

// Testar fromJSON
f.fromJSON(json, true);

console.log("Pontos depois de fromJSON:", f.elements.points.length);
console.log("Linhas depois de fromJSON:", f.elements.lines.length);

// Apagar coleção
f.deleteAll();

console.log("Coleção depois de deleteAll:", f.elements);

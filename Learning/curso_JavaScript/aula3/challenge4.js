
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
                this.elements.points.push(element);
            } else if (element.type === "line") {
                this.elements.lines.push(element);
            }
        });
    }

    addPoint(x, y) {
        this.elements.points.push(new Point(x, y));
    }

    addLine(coordinates) {
        this.elements.lines.push(new Line(coordinates));
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
                let coordinates = line.points.map(point => [point.x, point.y]);
                this.addLine(coordinates);
            });
        }
    }

    deleteAll() {
        this.elements.points = [];
        this.elements.lines = [];
    }
}

// Testes
let f = new Figure();

f.addPoint(10, 20);
f.addPoint(10, 10);

f.addLine([[10, 20], [30, 40], [50, 60]]);

let json = f.toJSON();

console.log(json);

f.fromJSON(json, true);

console.log(f.elements.points.length);
console.log(f.elements.lines.length);

f.fromJSON('{"points":[{"type":"point","x":10,"y":20},{"type":"point","x":10,"y":30},{"type":"point","x":10,"y":-30},{"type":"point","x":10,"y":20},{"type":"point","x":20,"y":20},{"type":"point","x":30,"y":20},{"type":"point","x":130,"y":20},{"type":"point","x":30,"y":20},{"type":"point","x":0,"y":20},{"type":"point","x":0,"y":-20},{"type":"point","x":0,"y":20}],"lines":[{"type":"line","points":[{"x":0,"y":0},{"x":10,"y":0},{"x":0,"y":10},{"x":20,"y":0},{"x":0,"y":20}]},{"type":"line","points":[{"x":30,"y":0},{"x":10,"y":0},{"x":0,"y":10},{"x":20,"y":0},{"x":0,"y":20}]},{"type":"line","points":[{"x":30,"y":0},{"x":10,"y":-10},{"x":0,"y":10},{"x":20,"y":0},{"x":0,"y":20}]},{"type":"line","points":[{"x":0,"y":0},{"x":10,"y":0},{"x":0,"y":10},{"x":20,"y":0},{"x":0,"y":20}]}]}');

console.log(f.elements.points.length);
console.log(f.elements.lines.length);

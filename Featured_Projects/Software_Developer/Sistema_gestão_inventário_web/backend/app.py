from flask import Flask, request, jsonify
from models import get_db, init_db

app = Flask(__name__)
init_db()

@app.route("/products", methods=["GET"])
def get_products():
    db = get_db()
    cur = db.execute("SELECT * FROM products")
    products = [dict(id=row[0], name=row[1], price=row[2], quantity=row[3]) for row in cur.fetchall()]
    return jsonify(products)

@app.route("/products", methods=["POST"])
def add_product():
    data = request.json
    db = get_db()
    db.execute("INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)",
               (data["name"], data["price"], data["quantity"]))
    db.commit()
    return jsonify({"status": "Product added"}), 201

@app.route("/products/<int:pid>", methods=["PUT"])
def update_product(pid):
    data = request.json
    db = get_db()
    db.execute("UPDATE products SET name=?, price=?, quantity=? WHERE id=?",
               (data["name"], data["price"], data["quantity"], pid))
    db.commit()
    return jsonify({"status": "Product updated"})

@app.route("/products/<int:pid>", methods=["DELETE"])
def delete_product(pid):
    db = get_db()
    db.execute("DELETE FROM products WHERE id=?", (pid,))
    db.commit()
    return jsonify({"status": "Product deleted"})

if __name__ == "__main__":
    app.run(debug=True)
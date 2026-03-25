from flask import Flask, jsonify, request, send_from_directory
from flask_cors import CORS
from models import load_products, save_products
import os

app = Flask(__name__)
CORS(app)

# =====================
# API
# =====================
@app.route('/products', methods=['GET'])
def get_products():
    products = load_products()
    return jsonify(products)

@app.route('/products', methods=['POST'])
def add_product():
    products = load_products()
    data = request.get_json()
    new_product = {
        "id": len(products) + 1,
        "name": data['name'],
        "price": data['price'],
        "quantity": data['quantity']
    }
    products.append(new_product)
    save_products(products)
    return jsonify(new_product), 201

@app.route('/products/<int:pid>', methods=['PUT'])
def update_product(pid):
    products = load_products()
    data = request.get_json()
    for p in products:
        if p["id"] == pid:
            p["quantity"] = data.get("quantity", p["quantity"])
            p["price"] = data.get("price", p["price"])
            save_products(products)
            return jsonify(p)
    return jsonify({"error": "Product not found"}), 404

@app.route('/products/<int:pid>', methods=['DELETE'])
def delete_product(pid):
    products = load_products()
    new_products = [p for p in products if p["id"] != pid]
    if len(new_products) == len(products):
        return jsonify({"error": "Product not found"}), 404
    save_products(new_products)
    return jsonify({"message": "Product deleted"}), 200

# =====================
# Servir frontend
# =====================
FRONTEND_DIR = os.path.join(os.path.dirname(os.path.abspath(__file__)), '../frontend')

@app.route('/')
def serve_index():
    return send_from_directory(FRONTEND_DIR, 'index.html')

@app.route('/dashboard')
def serve_dashboard():
    return send_from_directory(FRONTEND_DIR, 'dashboard.html')

@app.route('/<path:filename>')
def serve_static(filename):
    return send_from_directory(FRONTEND_DIR, filename)

# =====================
# Rodar aplicação
# =====================
if __name__ == '__main__':
    app.run(debug=True)
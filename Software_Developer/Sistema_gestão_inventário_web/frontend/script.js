const apiUrl = "http://127.0.0.1:5000/products";

async function fetchProducts() {
    const res = await fetch(apiUrl);
    const products = await res.json();
    const list = document.getElementById("product-list");
    list.innerHTML = "<h2>Products</h2>";
    products.forEach(p => {
        list.innerHTML += `<p>ID:${p.id} | ${p.name} | ${p.price}€ | ${p.quantity}</p>`;
    });
}

async function addProduct() {
    const name = document.getElementById("name").value;
    const price = parseFloat(document.getElementById("price").value);
    const quantity = parseInt(document.getElementById("quantity").value);
    await fetch(apiUrl, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name, price, quantity })
    });
    fetchProducts();
}

fetchProducts();
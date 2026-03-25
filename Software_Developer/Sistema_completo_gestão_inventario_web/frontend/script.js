const form = document.getElementById('addProductForm');
const tableBody = document.getElementById('productTableBody');

async function loadProducts() {
    try {
        const res = await fetch('/products');
        const products = await res.json();
        tableBody.innerHTML = '';
        products.forEach(p => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.price}€</td>
                <td>${p.quantity}</td>
            `;
            tableBody.appendChild(row);
        });
    } catch (err) {
        console.error("Erro ao carregar produtos:", err);
    }
}

form.addEventListener('submit', async (e) => {
    e.preventDefault();
    const name = document.getElementById('name').value;
    const price = parseFloat(document.getElementById('price').value);
    const quantity = parseInt(document.getElementById('quantity').value);

    try {
        const res = await fetch('/products', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({name, price, quantity})
        });
        if (res.ok) {
            form.reset();
            loadProducts();
        } else {
            alert("Erro ao adicionar produto!");
        }
    } catch (err) {
        console.error("Erro ao enviar produto:", err);
    }
});

loadProducts();
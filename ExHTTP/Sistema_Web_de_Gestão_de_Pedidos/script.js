const orderForm = document.getElementById("orderForm");
const productList = document.getElementById("productList");
const orderTable = document.getElementById("orderTable");
const search = document.getElementById("search");

let orders = JSON.parse(localStorage.getItem("orders")) || [];
let currentProducts = [];

// Guardar
function save() {
    localStorage.setItem("orders", JSON.stringify(orders));
}

// Calcular total do pedido
function calculateTotal(products) {
    return products.reduce(
        (sum, p) => sum + p.quantity * p.price, 0
    );
}

// Renderizar pedidos
function renderOrders(filter = "") {
    orderTable.innerHTML = "";

    orders
        .filter(o => o.customer.toLowerCase().includes(filter.toLowerCase()))
        .forEach(order => {
            const row = document.createElement("tr");
            const productsText = order.products
                .map(p => `${p.product} (${p.quantity}x €${p.price.toFixed(2)})`)
                .join("<br>");

            row.innerHTML = `
                <td>${order.id}</td>
                <td>${order.customer}</td>
                <td>${productsText}</td>
                <td>${order.total.toFixed(2)}€</td>
                <td>
                    <select onchange="updateStatus(${order.id}, this.value)">
                        <option ${order.status === "Novo" ? "selected" : ""}>Novo</option>
                        <option ${order.status === "Processado" ? "selected" : ""}>Processado</option>
                        <option ${order.status === "Enviado" ? "selected" : ""}>Enviado</option>
                    </select>
                </td>
                <td>
                    <button onclick="editOrder(${order.id})">✏️</button>
                    <button onclick="deleteOrder(${order.id})">🗑</button>
                </td>
`;

            orderTable.appendChild(row);
        });
}

// Atualizar estado
function updateStatus(id, status) {
    orders = orders.map(o =>
        o.id === id ? { ...o, status } : o
    );
    save();
    renderOrders(search.value);
}

//editar pedido
function editOrder(id) {
    const order = orders.find(o => o.id === id);
    if (!order) return;

    // Preenche o formulário com os dados do pedido
    customer.value = order.customer;
    currentProducts = [...order.products];

    // Atualiza a lista de produtos no HTML
    productList.innerHTML = order.products
        .map(p => `<li>${p.product} x${p.quantity} (€${p.price})</li>`)
        .join("");

    // Remove o pedido antigo para que seja substituído ao submeter
    orders = orders.filter(o => o.id !== id);
    save();
    renderOrders(search.value);
}


// Apagar pedido
function deleteOrder(id) {
    if (!confirm("Apagar pedido?")) return;
    orders = orders.filter(o => o.id !== id);
    save();
    renderOrders(search.value);
}

// Adicionar produto temporário
document.getElementById("addProduct").addEventListener("click", () => {
    const product = document.getElementById("product").value;
    const quantity = Number(document.getElementById("quantity").value);
    const price = Number(document.getElementById("price").value);

    if (!product || quantity <= 0 || price < 0) return;

    currentProducts.push({ product, quantity, price });
    productList.innerHTML += `<li>${product} x${quantity} (€${price})</li>`;

    product.value = "";
    quantity.value = "";
    price.value = "";
});

// Criar pedido
orderForm.addEventListener("submit", e => {
    e.preventDefault();
    if (currentProducts.length === 0) return;

    const total = calculateTotal(currentProducts);

    orders.push({
        id: Date.now(),
        customer: customer.value,
        products: currentProducts,
        total,
        status: "Novo"
    });

    currentProducts = [];
    productList.innerHTML = "";
    orderForm.reset();

    save();
    renderOrders();
});

// Pesquisa
search.addEventListener("input", () => {
    renderOrders(search.value);
});

// Inicial
renderOrders();

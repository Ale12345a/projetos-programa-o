const salesData = [
    { product: "Laptop", category: "Eletrónica", quantity: 3, unitPrice: 1200 },
    { product: "T-shirt", category: "Roupas", quantity: 10, unitPrice: 20 },
    { product: "Smartphone", category: "Eletrónica", quantity: 5, unitPrice: 800 },
    { product: "Pizza Congelada", category: "Alimentos", quantity: 15, unitPrice: 6 },
    { product: "Calças", category: "Roupas", quantity: 7, unitPrice: 35 }
];

const tableBody = document.querySelector("#salesTable tbody");
const totalRevenueElem = document.getElementById("totalRevenue");
const categoryFilter = document.getElementById("categoryFilter");

// Função para renderizar tabela
function renderTable(filter = "ALL") {
    tableBody.innerHTML = ""; // limpa tabela
    let totalRevenue = 0;

    salesData
        .filter(item => filter === "ALL" || item.category === filter)
        .forEach(item => {
            const total = item.quantity * item.unitPrice;
            totalRevenue += total;

            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${item.product}</td>
                <td>${item.category}</td>
                <td>${item.quantity}</td>
                <td>${item.unitPrice}</td>
                <td class="total-cell">${total}</td>
            `;
            tableBody.appendChild(row);
        });

    totalRevenueElem.textContent = totalRevenue.toFixed(2);
}

// Filtrar por categoria
categoryFilter.addEventListener("change", () => {
    renderTable(categoryFilter.value);
});

// Inicial
renderTable();

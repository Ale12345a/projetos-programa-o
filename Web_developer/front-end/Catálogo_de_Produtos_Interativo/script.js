// Lista de produtos
const products = [
    { name: "Teclado", price: 29.99, category: "eletronicos" },
    { name: "Camiseta", price: 15.99, category: "roupa" },
    { name: "Smartphone", price: 399.99, category: "eletronicos" },
    { name: "Calças", price: 39.99, category: "roupa" },
    { name: "Chocolate", price: 2.99, category: "alimentacao" },
    { name: "Café", price: 5.99, category: "alimentacao" },
];

// Referências do DOM
const container = document.getElementById("productsContainer");
const filter = document.getElementById("categoryFilter");

// Função para mostrar produtos
function displayProducts(filteredProducts) {
    container.innerHTML = ""; // Limpar container

    filteredProducts.forEach(product => {
        const card = document.createElement("div");
        card.classList.add("product-card");

        card.innerHTML = `
            <div class="product-name">${product.name}</div>
            <div class="product-price">€${product.price.toFixed(2)}</div>
            <div class="product-category">${product.category}</div>
        `;

        container.appendChild(card);
    });
}

// Inicial: mostrar todos
displayProducts(products);

// Filtrar produtos ao mudar seleção
filter.addEventListener("change", () => {
    const category = filter.value;
    if (category === "all") {
        displayProducts(products);
    } else {
        const filtered = products.filter(p => p.category === category);
        displayProducts(filtered);
    }
});
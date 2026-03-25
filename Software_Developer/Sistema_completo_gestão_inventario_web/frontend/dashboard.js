async function fetchProducts() {
    try {
        const res = await fetch('/products');
        if (!res.ok) throw new Error("Falha ao buscar produtos");
        return await res.json();
    } catch (err) {
        console.error("Erro ao carregar produtos:", err);
        return [];
    }
}

function createStockChart(products) {
    const ctx = document.getElementById('stockChart').getContext('2d');
    const labels = products.map(p => p.name);
    const data = products.map(p => p.quantity);

    if (window.stockChart) window.stockChart.destroy();

    window.stockChart = new Chart(ctx, {
        type: 'bar',
        data: { labels, datasets: [{ label: 'Quantity', data, backgroundColor: 'rgba(54, 162, 235, 0.7)' }] },
        options: { responsive: true, scales: { y: { beginAtZero: true, precision: 0 } } }
    });
}

function createPriceChart(products) {
    const ctx = document.getElementById('priceChart').getContext('2d');
    const labels = products.map(p => p.name);
    const data = products.map(p => p.price);

    if (window.priceChart) window.priceChart.destroy();

    window.priceChart = new Chart(ctx, {
        type: 'pie',
        data: { labels, datasets: [{ data, backgroundColor: ['#ff6384','#36a2eb','#ffce56','#75c3c8','#9b59b6','#f39c12','#2ecc71','#e74c3c','#3498db','#8e44ad'] }] },
        options: { responsive: true }
    });
}

async function initDashboard() {
    const products = await fetchProducts();
    if (products.length === 0) return;
    createStockChart(products);
    createPriceChart(products);
}

window.addEventListener('DOMContentLoaded', initDashboard);
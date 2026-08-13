import { Line } from "react-chartjs-2";
import { Chart as ChartJS, LineElement, CategoryScale, LinearScale, PointElement, Tooltip, Legend } from "chart.js";

ChartJS.register(LineElement, CategoryScale, LinearScale, PointElement, Tooltip, Legend);

function StockChart() {
  // Simular 30 dias de preços de ações
  const labels = Array.from({ length: 30 }, (_, i) => `Dia ${i + 1}`);
  const prices = labels.map(() => Math.floor(Math.random() * 50) + 120); // Preços entre 120 e 170

  const data = {
    labels,
    datasets: [
      {
        label: "Preço da Ação ($)",
        data: prices,
        borderColor: "rgba(75, 192, 192, 1)",
        backgroundColor: "rgba(75, 192, 192, 0.2)",
        tension: 0.3, // curva suave
        fill: true,
        pointRadius: 3,
      },
    ],
  };

  const options = {
    responsive: true,
    plugins: {
      legend: {
        position: "top",
      },
      tooltip: {
        mode: "index",
        intersect: false,
      },
    },
    scales: {
      y: {
        beginAtZero: false,
        title: {
          display: true,
          text: "Preço ($)",
        },
      },
      x: {
        title: {
          display: true,
          text: "Dias",
        },
      },
    },
  };

  return (
    <div style={{ maxWidth: "800px", margin: "0 auto" }}>
      <Line data={data} options={options} />
    </div>
  );
}

export default StockChart;
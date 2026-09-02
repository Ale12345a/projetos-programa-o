import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js"

import { Bar } from "react-chartjs-2"

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
)

interface FinanceChartProps {
  income: number
  expense: number
}

function FinanceChart({
  income,
  expense,
}: FinanceChartProps) {
  const data = {
    labels: ["Receitas", "Despesas"],

    datasets: [
      {
        label: "Valor (€)",
        data: [income, expense],
      },
    ],
  }

  const options = {
    responsive: true,

    plugins: {
      legend: {
        display: false,
      },

      title: {
        display: true,
        text: "Receitas vs. Despesas",
      },
    },
  }

  return (
    <section>
      <h2>Gráfico financeiro</h2>

      <Bar data={data} options={options} />
    </section>
  )
}

export default FinanceChart
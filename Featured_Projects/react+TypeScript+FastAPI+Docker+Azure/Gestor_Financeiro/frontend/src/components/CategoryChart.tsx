import {
  Chart as ChartJS,
  ArcElement,
  Tooltip,
  Legend,
} from "chart.js"

import { Pie } from "react-chartjs-2"

ChartJS.register(
  ArcElement,
  Tooltip,
  Legend
)

interface CategoryChartProps {
  categories: {
    category: string
    amount: number
  }[]
}

function CategoryChart({
  categories,
}: CategoryChartProps) {
  const data = {
    labels: categories.map((item) => item.category),

    datasets: [
      {
        label: "Despesas (€)",
        data: categories.map((item) => item.amount),
      },
    ],
  }

  return (
    <section>
      <h2>Despesas por categoria</h2>

      <Pie data={data} />
    </section>
  )
}

export default CategoryChart
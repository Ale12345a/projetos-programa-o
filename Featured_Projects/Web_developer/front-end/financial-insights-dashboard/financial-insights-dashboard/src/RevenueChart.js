import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
} from "chart.js";

import { Bar } from "react-chartjs-2";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

function RevenueChart() {

  const data = {
    labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun"],
    datasets: [
      {
        label: "Revenue",
        data: [12000, 19000, 15000, 22000, 18000, 25000],
        backgroundColor: "rgba(75,192,192,0.6)"
      }
    ]
  };

  return (
    <div style={{width:"600px", margin:"40px auto"}}>
      <Bar data={data} />
    </div>
  );
}

export default RevenueChart;
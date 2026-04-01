import "./App.css";
import RevenueChart from "./RevenueChart";

function App() {
  return (
    <div className="container">
      <h1>Financial Insights Dashboard</h1>

      <div className="cards">
        <div className="card">
          <h2>Revenue</h2>
          <p>$120,000</p>
        </div>

        <div className="card">
          <h2>Expenses</h2>
          <p>$45,000</p>
        </div>

        <div className="card">
          <h2>Profit</h2>
          <p>$75,000</p>
        </div>
      </div>

      <RevenueChart />

    </div>
  );
}

export default App;
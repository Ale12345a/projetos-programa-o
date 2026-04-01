import StockChart from "./StockChart";
import "./App.css";

function App() {
  return (
    <div className="App">
      <header>
        <h1>Stock Dashboard</h1>
        <p>Exemplo de dashboard de ações usando React e Chart.js</p>
      </header>
      <main>
        <StockChart />
      </main>
      <footer>
        <p>Projeto desenvolvido para portfólio Front-End</p>
      </footer>
    </div>
  );
}

export default App;
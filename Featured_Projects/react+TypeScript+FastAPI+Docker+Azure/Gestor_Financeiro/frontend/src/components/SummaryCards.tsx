interface Summary {
  total_income: number
  total_expense: number
  balance: number
}

interface SummaryCardsProps {
  summary: Summary | null
}

function SummaryCards({ summary }: SummaryCardsProps) {
  return (
    <section>
      <h2>Resumo financeiro</h2>

      <div>
        <div>
          <h3>Saldo</h3>
          <p>
            {summary
              ? `${summary.balance.toFixed(2)} €`
              : "A carregar..."}
          </p>
        </div>

        <div>
          <h3>Receitas</h3>
          <p>
            {summary
              ? `${summary.total_income.toFixed(2)} €`
              : "A carregar..."}
          </p>
        </div>

        <div>
          <h3>Despesas</h3>
          <p>
            {summary
              ? `${summary.total_expense.toFixed(2)} €`
              : "A carregar..."}
          </p>
        </div>
      </div>
    </section>
  )
}

export default SummaryCards
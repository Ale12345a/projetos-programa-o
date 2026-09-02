interface Transaction {
  id: number
  description: string
  amount: number
  type: "income" | "expense"
  category: string
  date: string
}

interface TransactionListProps {
  transactions: Transaction[]
  onTransactionDeleted: () => void
  onEdit: (transaction: Transaction) => void
}

function TransactionList({
  transactions,
  onTransactionDeleted,
  onEdit,
}: TransactionListProps) {
  const handleDelete = async (id: number) => {
    const confirmed = window.confirm(
      "Tens a certeza que queres apagar este movimento?"
    )

    if (!confirmed) {
      return
    }

    const response = await fetch(
      `/api/transactions/${id}`,
      {
        method: "DELETE",
      }
    )

    if (!response.ok) {
      alert("Erro ao apagar movimento.")
      return
    }

    onTransactionDeleted()
  }

  return (
    <section>
      <h2>Movimentos recentes</h2>

      <table>
        <thead>
          <tr>
            <th>Data</th>
            <th>Descrição</th>
            <th>Categoria</th>
            <th>Tipo</th>
            <th>Valor</th>
            <th>Ações</th>
          </tr>
        </thead>

        <tbody>
          {transactions.map((transaction) => (
            <tr key={transaction.id}>
              <td>
                {new Date(transaction.date).toLocaleDateString("pt-PT")}
              </td>

              <td>{transaction.description}</td>

              <td>{transaction.category}</td>

              <td>
                {transaction.type === "income"
                  ? "Receita"
                  : "Despesa"}
              </td>

              <td>
                {transaction.type === "income" ? "+" : "-"}
                {transaction.amount.toFixed(2)} €
              </td>

              <td>
                <button
                  className="edit-button"
                  onClick={() => onEdit(transaction)}
                >
                  Editar
                </button>

                <button
                  className="delete-button"
                  onClick={() => handleDelete(transaction.id)}
                >
                  Apagar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </section>
  )
}

export default TransactionList
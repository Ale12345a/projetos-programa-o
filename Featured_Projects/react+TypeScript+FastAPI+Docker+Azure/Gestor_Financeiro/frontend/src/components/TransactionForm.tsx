import { useEffect, useState } from "react"

interface Transaction {
  id: number
  description: string
  amount: number
  type: "income" | "expense"
  category: string
  date: string
}

interface TransactionFormProps {
  onTransactionAdded: () => void
  transactionToEdit: Transaction | null
  onEditFinished: () => void
}

function TransactionForm({
  onTransactionAdded,
  transactionToEdit,
  onEditFinished,
}: TransactionFormProps) {
  const [description, setDescription] = useState("")
  const [amount, setAmount] = useState("")
  const [type, setType] = useState<"income" | "expense">("expense")
  const [category, setCategory] = useState("")
  const [date, setDate] = useState("")

  useEffect(() => {
    if (transactionToEdit) {
      setDescription(transactionToEdit.description)
      setAmount(transactionToEdit.amount.toString())
      setType(transactionToEdit.type)
      setCategory(transactionToEdit.category)
      setDate(transactionToEdit.date)
    }
  }, [transactionToEdit])

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault()

    const transaction = {
      description,
      amount: Number(amount),
      type,
      category,
      date,
    }

    let response

    if (transactionToEdit) {
      response = await fetch(
        `/api/transactions/${transactionToEdit.id}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(transaction),
        }
      )
    } else {
      response = await fetch(
        "/api/transactions",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(transaction),
        }
      )
    }

    if (!response.ok) {
      alert(
        transactionToEdit
          ? "Erro ao editar movimento."
          : "Erro ao adicionar movimento."
      )
      return
    }

    setDescription("")
    setAmount("")
    setType("expense")
    setCategory("")
    setDate("")

    if (transactionToEdit) {
      onEditFinished()
    } else {
      onTransactionAdded()
    }
  }

  return (
    <section>
      <h2>
        {transactionToEdit
          ? "Editar movimento"
          : "Adicionar movimento"}
      </h2>

      <form onSubmit={handleSubmit}>
        <div>
          <label>Descrição</label>

          <input
            type="text"
            value={description}
            onChange={(event) =>
              setDescription(event.target.value)
            }
            placeholder="Ex.: Supermercado"
            required
          />
        </div>

        <div>
          <label>Valor</label>

          <input
            type="number"
            step="0.01"
            min="0.01"
            value={amount}
            onChange={(event) =>
              setAmount(event.target.value)
            }
            placeholder="Ex.: 25.50"
            required
          />
        </div>

        <div>
          <label>Tipo</label>

          <select
            value={type}
            onChange={(event) =>
              setType(
                event.target.value as "income" | "expense"
              )
            }
          >
            <option value="expense">Despesa</option>
            <option value="income">Receita</option>
          </select>
        </div>

        <div>
          <label>Categoria</label>

          <input
            type="text"
            value={category}
            onChange={(event) =>
              setCategory(event.target.value)
            }
            placeholder="Ex.: Alimentação"
            required
          />
        </div>

        <div>
          <label>Data</label>

          <input
            type="date"
            value={date}
            onChange={(event) =>
              setDate(event.target.value)
            }
            required
          />
        </div>

        <button type="submit">
          {transactionToEdit
            ? "Guardar alterações"
            : "Adicionar movimento"}
        </button>
      </form>
    </section>
  )
}

export default TransactionForm
import { useEffect, useState } from "react"
import "./App.css"

import SummaryCards from "./components/SummaryCards"
import TransactionList from "./components/TransactionList"
import TransactionForm from "./components/TransactionForm"
import TransactionFilters from "./components/TransactionFilters"
import FinanceChart from "./components/FinanceChart"
import CategoryChart from "./components/CategoryChart"

interface Transaction {
  id: number
  description: string
  amount: number
  type: "income" | "expense"
  category: string
  date: string
}

interface Summary {
  total_income: number
  total_expense: number
  balance: number
}

function App() {
  const [transactions, setTransactions] = useState<Transaction[]>([])
  const [summary, setSummary] = useState<Summary | null>(null)
  const [transactionToEdit, setTransactionToEdit] =
    useState<Transaction | null>(null)

  const [categories, setCategories] = useState<
    {
      category: string
      amount: number
    }[]
  >([])

  const [filterType, setFilterType] = useState("")
  const [filterCategory, setFilterCategory] = useState("")
  const [filterMonth, setFilterMonth] = useState("")

  const [loading, setLoading] = useState(false)
  const [error, setError] = useState("")

  const loadData = async () => {
    setLoading(true)
    setError("")

    const params = new URLSearchParams()

    if (filterType) {
      params.append("type", filterType)
    }

    if (filterCategory) {
      params.append("category", filterCategory)
    }

    if (filterMonth) {
      const [month, year] = filterMonth.split("/")

      params.append("month", `${year}-${month}`)
    }

    const queryString = params.toString()

    const transactionsUrl = queryString
      ? `/api/transactions?${queryString}`
      : "/api/transactions"

    try {
      const [
        transactionsResponse,
        summaryResponse,
        categoriesResponse,
      ] = await Promise.all([
        fetch(transactionsUrl),
        fetch("/api/transactions/summary"),
        fetch("/api/transactions/categories"),
      ])

      if (!transactionsResponse.ok) {
        throw new Error("Erro ao carregar os movimentos.")
      }

      if (!summaryResponse.ok) {
        throw new Error("Erro ao carregar o resumo financeiro.")
      }

      if (!categoriesResponse.ok) {
        throw new Error("Erro ao carregar as categorias.")
      }

      const transactionsData = await transactionsResponse.json()
      const summaryData = await summaryResponse.json()
      const categoriesData = await categoriesResponse.json()

      if (!Array.isArray(transactionsData)) {
        throw new Error("Resposta inválida dos movimentos.")
      }

      if (!Array.isArray(categoriesData)) {
        throw new Error("Resposta inválida das categorias.")
      }

      setTransactions(transactionsData)
      setSummary(summaryData)
      setCategories(categoriesData)
    } catch (error) {
      console.error(error)

      setError(
        error instanceof Error
          ? error.message
          : "Ocorreu um erro ao carregar os dados."
      )

      setTransactions([])
    } finally {
      setLoading(false)
    }
  }

  useEffect(() => {
    loadData()
  }, [filterType, filterCategory, filterMonth])

  return (
    <div>
      <header>
        <h1>Gestor Financeiro</h1>
        <p>Controla os teus movimentos financeiros</p>
      </header>

      <main>
        {error && (
          <section>
            <p>{error}</p>
          </section>
        )}

        {loading && (
          <section>
            <p>A carregar dados...</p>
          </section>
        )}

        <SummaryCards summary={summary} />

        <TransactionFilters
          type={filterType}
          category={filterCategory}
          month={filterMonth}
          onTypeChange={setFilterType}
          onCategoryChange={setFilterCategory}
          onMonthChange={setFilterMonth}
        />

        <TransactionForm
          onTransactionAdded={loadData}
          transactionToEdit={transactionToEdit}
          onEditFinished={() => {
            setTransactionToEdit(null)
            loadData()
          }}
        />

        <TransactionList
          transactions={transactions}
          onTransactionDeleted={loadData}
          onEdit={setTransactionToEdit}
        />

        <FinanceChart
          income={summary?.total_income ?? 0}
          expense={summary?.total_expense ?? 0}
        />

        <CategoryChart categories={categories} />
      </main>
    </div>
  )
}

export default App

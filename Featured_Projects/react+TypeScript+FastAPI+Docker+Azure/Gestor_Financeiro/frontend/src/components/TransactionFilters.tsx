interface TransactionFiltersProps {
  type: string
  category: string
  month: string
  onTypeChange: (value: string) => void
  onCategoryChange: (value: string) => void
  onMonthChange: (value: string) => void
}

function TransactionFilters({
  type,
  category,
  month,
  onTypeChange,
  onCategoryChange,
  onMonthChange,
}: TransactionFiltersProps) {
  return (
    <section>
      <h2>Filtros</h2>

      <div>
        <div>
          <label>Tipo</label>

          <select
            value={type}
            onChange={(event) =>
              onTypeChange(event.target.value)
            }
          >
            <option value="">Todos</option>
            <option value="income">Receitas</option>
            <option value="expense">Despesas</option>
          </select>
        </div>

        <div>
          <label>Categoria</label>

          <input
            type="text"
            value={category}
            onChange={(event) =>
              onCategoryChange(event.target.value)
            }
            placeholder="Ex.: Alimentação"
          />
        </div>

        <div>
          <label>Mês/ano</label>

          <input
            type="month"
            value={month}
            onChange={(event) =>
              onMonthChange(event.target.value)
            }
          />
        </div>
      </div>
    </section>
  )
}

export default TransactionFilters
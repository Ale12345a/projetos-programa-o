import { useState } from "react";

type TaskFormProps = {
  adicionarTarefa: (tarefa: string) => Promise<void>;
};

function TaskForm({ adicionarTarefa }: TaskFormProps) {
  const [novaTarefa, setNovaTarefa] = useState("");
  const [erro, setErro] = useState("");
  const [aAdicionar, setAAdicionar] = useState(false);

  async function handleSubmit() {
    const tarefa = novaTarefa.trim();

    if (tarefa === "") {
      setErro("Por favor, escreve uma tarefa.");
      return;
    }

    if (tarefa.length < 3) {
      setErro("A tarefa deve ter pelo menos 3 caracteres.");
      return;
    }

    if (tarefa.length > 100) {
      setErro("A tarefa não pode ter mais de 100 caracteres.");
      return;
    }

    setErro("");
    setAAdicionar(true);

    try {
      await adicionarTarefa(tarefa);
      setNovaTarefa("");
    } catch (error) {
      console.error("Erro no formulário:", error);
    } finally {
      setAAdicionar(false);
    }
  }

  return (
    <div className="task-form">
      <div className="form-row">
        <input
          type="text"
          value={novaTarefa}
          onChange={(event) => setNovaTarefa(event.target.value)}
          placeholder="Escreve uma tarefa..."
          disabled={aAdicionar}
        />

        <button
          onClick={handleSubmit}
          disabled={aAdicionar}
        >
          {aAdicionar ? "A adicionar..." : "Adicionar"}
        </button>
      </div>

      {erro && <p className="form-error">{erro}</p>}
    </div>
  );
}

export default TaskForm;

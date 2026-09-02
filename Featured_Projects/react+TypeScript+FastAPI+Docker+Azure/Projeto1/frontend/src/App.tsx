import { useEffect, useState } from "react";
import "./App.css";
import TaskForm from "./components/TaskForm";
import TaskList from "./components/TaskList";
import { createTask, getTasks, updateTask, deleteTask } from "./api";
import type { Task } from "./types";

function App() {
  const [tarefas, setTarefas] = useState<Task[]>([]);
  const [aCarregar, setACarregar] = useState(true);
  const [erro, setErro] = useState("");

  useEffect(() => {
    async function carregarTarefas() {
      setACarregar(true);
      setErro("");

      try {
        const tarefasDaAPI = await getTasks();
        setTarefas(tarefasDaAPI);
      } catch (error) {
        console.error("Erro ao carregar tarefas:", error);
        setErro("Não foi possível carregar as tarefas.");
      } finally {
        setACarregar(false);
      }
    }

    carregarTarefas();
  }, []);

  async function adicionarTarefa(tarefa: string) {
    setErro("");

    try {
      const novaTarefa = await createTask(tarefa, "");

      setTarefas([...tarefas, novaTarefa]);
    } catch (error) {
      console.error("Erro ao adicionar tarefa:", error);
      setErro("Não foi possível adicionar a tarefa.");

      throw error;
    }
  }

  async function atualizarTarefa(id: number, completed: boolean) {
    setErro("");

    try {
      const tarefaAtualizada = await updateTask(id, completed);

      setTarefas(
        tarefas.map((tarefa) =>
          tarefa.id === id ? tarefaAtualizada : tarefa
        )
      );
    } catch (error) {
      console.error("Erro ao atualizar tarefa:", error);
      setErro("Não foi possível atualizar a tarefa.");

      throw error;
    }
  }

  async function eliminarTarefa(id: number) {
    setErro("");

    try {
      await deleteTask(id);

      setTarefas(
        tarefas.filter((tarefa) => tarefa.id !== id)
      );
    } catch (error) {
      console.error("Erro ao eliminar tarefa:", error);
      setErro("Não foi possível eliminar a tarefa.");

      throw error;
    }
  }

  const tarefasConcluidas = tarefas.filter(
    (tarefa) => tarefa.completed
  ).length;

  const tarefasPorFazer = tarefas.length - tarefasConcluidas;

  return (
    <div className="app">
      <h1>A minha Todo App</h1>

      <div className="task-stats">
        <div className="stat">
          <strong>{tarefas.length}</strong>
          <span>Total</span>
        </div>

        <div className="stat">
          <strong>{tarefasPorFazer}</strong>
          <span>Por fazer</span>
        </div>

        <div className="stat">
          <strong>{tarefasConcluidas}</strong>
          <span>Concluídas</span>
        </div>
      </div>

      <TaskForm adicionarTarefa={adicionarTarefa} />

      {aCarregar && (
        <p className="loading-message">
          A carregar tarefas...
        </p>
      )}

      {erro && (
        <p className="api-error">
          {erro}
        </p>
      )}

      {!aCarregar && tarefas.length === 0 && !erro && (
        <p className="empty-message">
          Não existem tarefas. Adiciona a tua primeira tarefa!
        </p>
      )}

      <TaskList
        tarefas={tarefas}
        atualizarTarefa={atualizarTarefa}
        eliminarTarefa={eliminarTarefa}
      />
    </div>
  );
}

export default App;
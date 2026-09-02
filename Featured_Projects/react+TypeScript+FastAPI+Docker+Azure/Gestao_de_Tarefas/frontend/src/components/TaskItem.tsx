import { useState } from "react";
import type { Task } from "../types";

type TaskItemProps = {
  tarefa: Task;
  atualizarTarefa: (id: number, completed: boolean) => Promise<void>;
  eliminarTarefa: (id: number) => Promise<void>;
};

function TaskItem({
  tarefa,
  atualizarTarefa,
  eliminarTarefa,
}: TaskItemProps) {
  const [aAtualizar, setAAtualizar] = useState(false);
  const [aEliminar, setAEliminar] = useState(false);

  async function handleChange() {
    setAAtualizar(true);

    try {
      await atualizarTarefa(tarefa.id, !tarefa.completed);
    } catch (error) {
      console.error("Erro ao atualizar tarefa:", error);
    } finally {
      setAAtualizar(false);
    }
  }

  async function handleDelete() {
    setAEliminar(true);

    try {
      await eliminarTarefa(tarefa.id);
    } catch (error) {
      console.error("Erro ao eliminar tarefa:", error);
    } finally {
      setAEliminar(false);
    }
  }

  return (
    <li className={`task-item ${tarefa.completed ? "completed" : ""}`}>
      <div className="task-content">
        <div className="task-header">
          <input
            type="checkbox"
            checked={tarefa.completed}
            onChange={handleChange}
            disabled={aAtualizar || aEliminar}
          />

          <strong>{tarefa.title}</strong>
        </div>

        {tarefa.description && (
          <p className="task-description">{tarefa.description}</p>
        )}

        <span className="task-status">
          {aAtualizar
            ? "A atualizar..."
            : aEliminar
              ? "A eliminar..."
              : tarefa.completed
                ? "Concluída"
                : "Por fazer"}
        </span>
      </div>

      <button
        className="delete-button"
        onClick={handleDelete}
        disabled={aEliminar || aAtualizar}
      >
        {aEliminar ? "A eliminar..." : "Apagar"}
      </button>
    </li>
  );
}

export default TaskItem;

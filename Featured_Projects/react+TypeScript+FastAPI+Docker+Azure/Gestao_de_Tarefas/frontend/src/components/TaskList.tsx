import TaskItem from "./TaskItem";
import type { Task } from "../types";

type TaskListProps = {
  tarefas: Task[];
  atualizarTarefa: (id: number, completed: boolean) => Promise<void>;
  eliminarTarefa: (id: number) => Promise<void>;
};

function TaskList({
  tarefas,
  atualizarTarefa,
  eliminarTarefa,
}: TaskListProps) {
  return (
    <ul>
      {tarefas.map((tarefa) => (
        <TaskItem
          key={tarefa.id}
          tarefa={tarefa}
          atualizarTarefa={atualizarTarefa}
          eliminarTarefa={eliminarTarefa}
        />
      ))}
    </ul>
  );
}

export default TaskList;
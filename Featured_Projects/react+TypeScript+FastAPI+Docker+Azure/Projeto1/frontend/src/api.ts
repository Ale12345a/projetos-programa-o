import type { Task } from "./types";

const API_URL = "http://127.0.0.1:8000";

export async function getTasks(): Promise<Task[]> {
  const response = await fetch(`${API_URL}/tasks`);

  if (!response.ok) {
    throw new Error("Erro ao carregar tarefas");
  }

  return response.json();
}

export async function createTask(
  title: string,
  description: string
): Promise<Task> {
  const response = await fetch(`${API_URL}/tasks`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      title,
      description,
    }),
  });

  if (!response.ok) {
    throw new Error("Erro ao criar tarefa");
  }

  return response.json();
}

export async function updateTask(
  id: number,
  completed: boolean
): Promise<Task> {
  const response = await fetch(`${API_URL}/tasks/${id}`, {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
      completed,
    }),
  });

  if (!response.ok) {
    throw new Error("Erro ao atualizar tarefa");
  }

  return response.json();
}

export async function deleteTask(id: number): Promise<void> {
  const response = await fetch(`${API_URL}/tasks/${id}`, {
    method: "DELETE",
  });

  if (!response.ok) {
    throw new Error("Erro ao eliminar tarefa");
  }
}
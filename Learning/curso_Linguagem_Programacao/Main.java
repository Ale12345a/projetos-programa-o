
import java.util.ArrayList;
import java.util.List;

// Classe Tarefa
class Tarefa {
    private String descricao;
    private int prioridade;
    private boolean concluida;

    public Tarefa(String descricao, int prioridade) {
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.concluida = false;
    }

    public void concluir() {
        this.concluida = true;
    }

    public boolean isConcluida() {
        return concluida;
    }

    @Override
    public String toString() {
        String estado = concluida ? "sim" : "";
        return estado + " " + descricao +
               " (Prioridade: " + prioridade + ")";
    }
}

// Classe GestorTarefas
class GestorTarefas {
    private List<Tarefa> tarefas;

    public GestorTarefas() {
        tarefas = new ArrayList<>();
    }

    public void adicionar(Tarefa tarefa) {
        tarefas.add(tarefa);
    }

    public void listarPendentes() {
        for (Tarefa t : tarefas) {
            if (!t.isConcluida()) {
                System.out.println(t);
            }
        }
    }

    public void removerConcluidas() {
        tarefas.removeIf(Tarefa::isConcluida);
    }

    public void concluirTarefa(int indice) {
        if (indice >= 0 && indice < tarefas.size()) {
            tarefas.get(indice).concluir();
        }
    }
}

// Classe principal
public class Main {
    public static void main(String[] args) {

        // Criar gestor de tarefas
        GestorTarefas gestor = new GestorTarefas();

        // Adicionar tarefas
        gestor.adicionar(new Tarefa("Estudar programação", 2));
        gestor.adicionar(new Tarefa("Fazer exercício físico", 1));

        // Listar tarefas pendentes
        System.out.println("Pendentes:");
        gestor.listarPendentes();

        // Concluir a primeira tarefa
        gestor.concluirTarefa(0);

        // Remover tarefas concluídas
        gestor.removerConcluidas();

        // Listar novamente
        System.out.println("\nApós remoção de concluídas:");
        gestor.listarPendentes();
    }
}

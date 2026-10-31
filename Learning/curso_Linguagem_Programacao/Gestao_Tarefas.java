
import java.util.ArrayList;
import java.util.List;

class Tarefa {
    private String descricao;
    private int prioridade;
    private boolean concluida;

    public Tarefa(String descricao, int prioridade) {
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.concluida = false;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void concluir() {
        this.concluida = true;
    }

    @Override
    public String toString() {
        return descricao + " | Prioridade: " + prioridade;
    }
}

class GestorTarefas {
    private List<Tarefa> tarefas = new ArrayList<>();

    public void adicionarTarefa(String descricao, int prioridade) {
        tarefas.add(new Tarefa(descricao, prioridade));
    }

    public void listarPendentes() {
        System.out.println("\nTarefas pendentes:");

        for (Tarefa tarefa : tarefas) {
            if (!tarefa.isConcluida()) {
                System.out.println(tarefa);
            }
        }
    }

    public void concluirTarefa(String descricao) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getDescricao().equalsIgnoreCase(descricao)) {
                tarefa.concluir();
                return;
            }
        }
    }

    public void removerConcluidas() {
        tarefas.removeIf(Tarefa::isConcluida);
    }
}

public class Gestao_Tarefas {
    public static void main(String[] args) {
        GestorTarefas gestor = new GestorTarefas();

        gestor.adicionarTarefa("Estudar Java", 1);
        gestor.adicionarTarefa("Fazer exercícios", 2);
        gestor.adicionarTarefa("Ler documentação", 3);

        gestor.listarPendentes();

        gestor.concluirTarefa("Estudar Java");
        gestor.removerConcluidas();

        System.out.println("\nDepois de remover as tarefas concluídas:");
        gestor.listarPendentes();
    }
}

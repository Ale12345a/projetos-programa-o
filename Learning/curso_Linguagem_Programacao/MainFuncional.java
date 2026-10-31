
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

// Representar tarefas como dados imutáveis
record Tarefa(String descricao, int prioridade, boolean concluida) {}

// Criar uma tarefa
class FuncoesTarefas {

    public static Tarefa criarTarefa(String descricao, int prioridade) {
        return new Tarefa(descricao, prioridade, false);
    }

    // Listar apenas as tarefas pendentes
    public static List<Tarefa> listarPendentes(List<Tarefa> tarefas) {
        return tarefas.stream()
                .filter(t -> !t.concluida())
                .collect(Collectors.toList());
    }

    // Remover tarefas concluídas
    public static List<Tarefa> removerConcluidas(List<Tarefa> tarefas) {
        return tarefas.stream()
                .filter(t -> !t.concluida())
                .collect(Collectors.toList());
    }

    // Concluir uma tarefa sem modificar a original
    public static List<Tarefa> concluirTarefa(
            List<Tarefa> tarefas, int indexAConcluir) {

        List<Tarefa> novaLista = new ArrayList<>();

        for (int index = 0; index < tarefas.size(); index++) {
            Tarefa tarefa = tarefas.get(index);

            if (index == indexAConcluir) {
                Tarefa tarefaAtualizada = new Tarefa(
                        tarefa.descricao(),
                        tarefa.prioridade(),
                        true
                );

                novaLista.add(tarefaAtualizada);
            } else {
                novaLista.add(tarefa);
            }
        }

        return novaLista;
    }
}

// Classe principal
public class MainFuncional {

    public static void main(String[] args) {

        // Criar lista de tarefas
        List<Tarefa> tarefas = new ArrayList<>();

        // Adicionar tarefas
        tarefas.add(FuncoesTarefas.criarTarefa(
                "Estudar programação", 2));

        tarefas.add(FuncoesTarefas.criarTarefa(
                "Fazer exercício físico", 1));

        // Concluir a primeira tarefa
        tarefas = FuncoesTarefas.concluirTarefa(tarefas, 0);

        // Remover tarefas concluídas
        tarefas = FuncoesTarefas.removerConcluidas(tarefas);

        // Listar tarefas pendentes
        System.out.println("Tarefas pendentes:");

        FuncoesTarefas.listarPendentes(tarefas)
                .forEach(t -> System.out.println(
                        t.descricao() +
                        " (Prioridade: " + t.prioridade() + ")"
                ));
    }
}

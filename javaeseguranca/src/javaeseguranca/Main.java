package javaeseguranca;

public class Main {
    public static void main(String[] args) {
        try {
            ExameDAO exameDAO = new ExameDAO();

            Exame novoExame = new Exame("Potassio", "Tipo de Dado");
            exameDAO.inserirExame(novoExame);

            System.out.println("Lista de Exames:");
            exameDAO.listarExames().forEach(System.out::println);

            Exame exameAtualizado = new Exame("Potassio Atualizado", "Novo Tipo de Dado");
            exameDAO.atualizarExame(1, exameAtualizado);

            System.out.println("Lista de Exames após Atualização:");
            exameDAO.listarExames().forEach(System.out::println);

            exameDAO.deletarExame(1);

            System.out.println("Lista de Exames após Exclusão:");
            exameDAO.listarExames().forEach(System.out::println);

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}

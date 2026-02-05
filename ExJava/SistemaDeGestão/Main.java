public class Main {

    public static void main(String[] args) {

        UserManager manager = new UserManager();

        manager.register("alex", "1234", Role.ADMIN);
        manager.register("maria", "password", Role.USER);

        System.out.println("Login válido:");
        User u1 = manager.login("alex", "1234");
        System.out.println(u1);

        System.out.println("\nLogin inválido:");
        try {
            manager.login("alex", "errada");
        } catch (InvalidCredentialsException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        manager.deactivateUser(1);

        System.out.println("\nUtilizadores ativos:");
        manager.listActiveUsers().forEach(System.out::println);
    }
}

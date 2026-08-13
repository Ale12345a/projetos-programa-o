import java.util.*;

public class Main {

    static List<Ticket> tickets = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== HELP DESK MENU ===");
            System.out.println("1. Load tickets");
            System.out.println("2. Show all tickets");
            System.out.println("3. Show stats");
            System.out.println("0. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    loadTickets();
                    break;
                case 2:
                    showTickets();
                    break;
                case 3:
                    TicketService.showStats(tickets);
                    TicketService.showLateTickets(tickets);
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }

    public static void loadTickets() {
        tickets.add(new Ticket(1, "Internet down", "High", "Closed",
                java.time.LocalDate.of(2024,1,1),
                java.time.LocalDate.of(2024,1,2),
                "IT"));

        tickets.add(new Ticket(2, "Computer slow", "Medium", "Open",
                java.time.LocalDate.of(2024,1,3),
                null,
                "HR"));

        tickets.add(new Ticket(3, "Email not working", "High", "Closed",
                java.time.LocalDate.of(2024,1,4),
                java.time.LocalDate.of(2024,1,4),
                "Finance"));

        tickets.add(new Ticket(4, "Printer issue", "Low", "Closed",
                java.time.LocalDate.of(2024,1,5),
                java.time.LocalDate.of(2024,1,10),
                "HR"));

        tickets.add(new Ticket(5, "System crash", "High", "Open",
                java.time.LocalDate.of(2024,1,6),
                null,
                "IT"));

        System.out.println("✅ Tickets loaded!");
    }

    public static void showTickets() {
        for (Ticket t : tickets) {
            System.out.println(t.id + " - " + t.title + " (" + t.status + ")");
        }
    }
}
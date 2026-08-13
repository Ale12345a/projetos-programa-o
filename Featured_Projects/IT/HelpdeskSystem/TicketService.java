import java.time.temporal.ChronoUnit;
import java.util.List;

public class TicketService {

    public static long calculateResolutionTime(Ticket t) {
        if (t.resolvedAt == null) return -1;
        return ChronoUnit.DAYS.between(t.createdAt, t.resolvedAt);
    }

    public static void showStats(List<Ticket> tickets) {
        int total = tickets.size();
        int open = 0;
        int closed = 0;

        double totalResolutionTime = 0;
        int resolvedCount = 0;

        for (Ticket t : tickets) {
            if (t.status.equals("Open")) open++;
            if (t.status.equals("Closed")) {
                closed++;
                long days = calculateResolutionTime(t);
                if (days >= 0) {
                    totalResolutionTime += days;
                    resolvedCount++;
                }
            }
        }

        System.out.println("\n=== HELP DESK STATS ===");
        System.out.println("Total Tickets: " + total);
        System.out.println("Open Tickets: " + open);
        System.out.println("Closed Tickets: " + closed);

        if (resolvedCount > 0) {
            System.out.println("Average Resolution Time: " + (totalResolutionTime / resolvedCount) + " days");
        }
    }

    public static void showLateTickets(List<Ticket> tickets) {
        System.out.println("\n=== LATE TICKETS (>3 days) ===");

        for (Ticket t : tickets) {
            long days = calculateResolutionTime(t);
            if (days > 3) {
                System.out.println(t.title + " - " + days + " days");
            }
        }
    }
}
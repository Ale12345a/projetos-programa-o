import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        ReservationManager manager = new ReservationManager();

        manager.addRoom("Room A", RoomType.SMALL, 5);
        manager.addRoom("Room B", RoomType.MEDIUM, 10);
        manager.addRoom("Room C", RoomType.LARGE, 20);

        LocalDateTime start = LocalDateTime.of(2026, 1, 20, 10, 0);
        LocalDateTime end   = LocalDateTime.of(2026, 1, 20, 12, 0);

        manager.reserveRoom(1, start, end);

        // reserva válida
        manager.reserveRoom(2, start, end);

        // conflito (lança exceção)
        try {
            manager.reserveRoom(1,
                    LocalDateTime.of(2026, 1, 20, 11, 0),
                    LocalDateTime.of(2026, 1, 20, 13, 0));
        } catch (ReservationConflictException e) {
            System.out.println("Conflito detetado corretamente!");
        }

        System.out.println("\nSalas disponíveis:");
        manager.getAvailableRooms(start, end)
                .forEach(System.out::println);
    }
}

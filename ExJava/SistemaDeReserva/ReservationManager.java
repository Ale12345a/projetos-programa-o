import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ReservationManager {

    private final List<Room> rooms = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();

    private int nextRoomId = 1;
    private int nextReservationId = 1;

    // ➕ Adicionar sala
    public void addRoom(String name, RoomType type, int capacity) {
        rooms.add(new Room(nextRoomId++, name, type, capacity));
    }

    // 🔍 Encontrar sala
    private Room findRoomById(int roomId) {
        return rooms.stream()
                .filter(r -> r.getId() == roomId)
                .findFirst()
                .orElseThrow(() -> new RoomNotFoundException(roomId));
    }

    // 📅 Criar reserva
    public void reserveRoom(int roomId, LocalDateTime start, LocalDateTime end) {

        Room room = findRoomById(roomId);

        boolean conflict = reservations.stream()
                .filter(r -> r.getRoom().getId() == roomId)
                .filter(r -> r.getStatus() == ReservationStatus.ACTIVE)
                .anyMatch(r ->
                        start.isBefore(r.getEnd()) &&
                        end.isAfter(r.getStart())
                );

        if (conflict) {
            throw new ReservationConflictException();
        }

        reservations.add(new Reservation(nextReservationId++, room, start, end));
    }

    // ❌ Cancelar reserva
    public void cancelReservation(int reservationId) {
        reservations.stream()
                .filter(r -> r.getId() == reservationId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Reservation not found"))
                .cancel();
    }

    // 📂 Reservas por sala
    public List<Reservation> getReservationsForRoom(int roomId) {
        return reservations.stream()
                .filter(r -> r.getRoom().getId() == roomId)
                .collect(Collectors.toList());
    }

    // 🟢 Salas disponíveis
    public List<Room> getAvailableRooms(LocalDateTime start, LocalDateTime end) {

        return rooms.stream()
                .filter(room ->
                        reservations.stream()
                                .filter(r -> r.getRoom().getId() == room.getId())
                                .filter(r -> r.getStatus() == ReservationStatus.ACTIVE)
                                .noneMatch(r ->
                                        start.isBefore(r.getEnd()) &&
                                        end.isAfter(r.getStart())
                                )
                )
                .sorted(Comparator.comparing(Room::getCapacity))
                .collect(Collectors.toList());
    }
}

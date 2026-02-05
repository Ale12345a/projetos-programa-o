import java.time.LocalDateTime;

public class Reservation {

    private final int id;
    private final Room room;
    private final LocalDateTime start;
    private final LocalDateTime end;
    private ReservationStatus status;

    public Reservation(int id, Room room, LocalDateTime start, LocalDateTime end) {
        if (!end.isAfter(start)) {
            throw new InvalidReservationException("End must be after start");
        }

        this.id = id;
        this.room = room;
        this.start = start;
        this.end = end;
        this.status = ReservationStatus.ACTIVE;
    }

    public int getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELLED;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", room=" + room.getId() +
                ", start=" + start +
                ", end=" + end +
                ", status=" + status +
                '}';
    }
}

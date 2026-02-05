public class ReservationConflictException extends RuntimeException {
    public ReservationConflictException() {
        super("Reservation conflict detected");
    }
}

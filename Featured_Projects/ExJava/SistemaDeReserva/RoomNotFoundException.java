public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(int id) {
        super("Room with id " + id + " not found");
    }
}

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String username) {
        super("Username already exists: " + username);
    }
}

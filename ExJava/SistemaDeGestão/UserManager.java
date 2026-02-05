import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private final List<User> users = new ArrayList<>();
    private int nextId = 1;

    public User register(String username, String password, Role role) {

        boolean exists = users.stream()
                .anyMatch(u -> u.getUsername().equals(username));

        if (exists) {
            throw new DuplicateUserException(username);
        }

        String hash = PasswordUtils.hashPassword(password);
        User user = new User(nextId++, username, hash, role);
        users.add(user);
        return user;
    }

    public User login(String username, String password) {

        User user = users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(UserNotFoundException::new);

        if (!user.isActive()) {
            throw new InvalidCredentialsException();
        }

        boolean valid = PasswordUtils.verifyPassword(password, user.getPasswordHash());
        if (!valid) {
            throw new InvalidCredentialsException();
        }

        return user;
    }

    public void deactivateUser(int userId) {
        User user = users.stream()
                .filter(u -> u.getId() == userId)
                .findFirst()
                .orElseThrow(UserNotFoundException::new);

        user.deactivate();
    }

    public List<User> listActiveUsers() {
        return users.stream()
                .filter(User::isActive)
                .toList();
    }
}

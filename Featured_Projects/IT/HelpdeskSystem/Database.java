import java.sql.*;

public class Database {

    private static final String URL = "jdbc:sqlite:database.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS tickets (
                id INTEGER,
                title TEXT,
                priority TEXT,
                status TEXT,
                created_at TEXT,
                resolved_at TEXT,
                department TEXT
            );
        """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertTicket(Ticket t) {
        String sql = "INSERT INTO tickets VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, t.id);
            pstmt.setString(2, t.title);
            pstmt.setString(3, t.priority);
            pstmt.setString(4, t.status);
            pstmt.setString(5, t.createdAt.toString());
            pstmt.setString(6, t.resolvedAt != null ? t.resolvedAt.toString() : null);
            pstmt.setString(7, t.department);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
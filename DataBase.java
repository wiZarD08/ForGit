import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBase {
    private final Connection con;

    public DataBase() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/base1", "username", "pass");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(String name, String id) {
        try (PreparedStatement preSt = con.prepareStatement("insert into game (name_, id) values (?, ?)")) {
            preSt.setString(1, name);
            preSt.setString(1, id);
            System.out.print(preSt.getUpdateCount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeCon() {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("Can't close connection");
            throw new RuntimeException(e);
        }
    }
}

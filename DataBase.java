import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private Connection con;

    DataBase() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/base1", "username", "pass");
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

import java.sql.*;

public class DataBase {
    private final Connection con;

    public DataBase() {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/base1", "username", "pass");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectAll() {
        try (Statement stmt = con.createStatement();
             ResultSet res = stmt.executeQuery("select name_, id from game")) {
            while (res.next()) {
                System.out.print(res.getInt(2) + "  ");
                System.out.println(res.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(String name, int id) {
        try (PreparedStatement preSt = con.prepareStatement("insert into game (name_, id) values (?, ?)")) {
            preSt.setString(1, name);
            preSt.setInt(2, id);
            System.out.print(preSt.getUpdateCount());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(String name) {
        try (PreparedStatement preSt = con.prepareStatement("insert into game (name_) values (?)")) {
            preSt.setString(1, name);
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

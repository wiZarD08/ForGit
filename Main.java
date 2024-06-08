import java.sql.*;

public class Main {

    public static void main(String[] args) {
        DataBase db = new DataBase();
        db.selectAll();
        db.closeCon();
    }

    public static void notForUsing(String[] args) {
        try (Connection con = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/base1", "username", "pass")) {

            //executeQuery    Statement
            try (Statement stmt = con.createStatement();
                 ResultSet res = stmt.executeQuery("select name_, id from game")) {
                while (res.next()) {
                    System.out.print(res.getString(2) + "  ");
                    System.out.println(res.getString(1));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            //executeUpdate Statement
            try (Statement stmt = con.createStatement()) {
            //    stmt.executeUpdate("insert into game (name_) values ('fisherman')");
                System.out.print(stmt.getUpdateCount());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            //executeUpdate PreparedStatement
            try (PreparedStatement preSt = con.prepareStatement("insert into game (name_, id) values (?, ?)")) {
                preSt.setString(1, "game");
                preSt.setString(1, "game");
                System.out.print(preSt.getUpdateCount());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

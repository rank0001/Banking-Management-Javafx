package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//connecting the database
public class DbConnection {
    private static final String SQCONN = "jdbc:sqlite:bank.sqlite";
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        try {
            if (connection == null) {
                Class.forName("org.sqlite.JDBC");
                return DriverManager.getConnection(SQCONN);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

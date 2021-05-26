package register;
import dbUtil.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegModel {
    Connection connection;

    public RegModel() {
        try {
            this.connection = DbConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (this.connection == null) {
            System.exit(1);
        }
    }
    //inserting user info from registration
    public boolean registerUser(String name, String password, int id,String amount) throws SQLException {
        String sqlInsert = "INSERT INTO user(name,password,id,amount) VALUES (?,?,?,?)";
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlInsert);
            pr.setString(1, name);
            pr.setString(2, password);
            pr.setInt(3, id);
            pr.setString(4, amount);
            pr.execute();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

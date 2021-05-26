package login;

import dbUtil.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    Connection connection;

    public LoginModel() {
        try {
            this.connection = DbConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (this.connection == null) {
            System.exit(1);
        }
    }
    //Validating login process for User
    public boolean isLoginUser(String user, String pass) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM user where name = ? and password = ?";
        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, user);
            pr.setString(2, pass);
            rs = pr.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        } finally {
            {
                pr.close();
                rs.close();
            }
        }
    }
    //Validating login process for Officer
    public boolean isLoginOfficer(String user, String pass) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM officer where name = ? and password = ?";
        try {
            pr = this.connection.prepareStatement(sql);
            pr.setString(1, user);
            pr.setString(2, pass);
            rs = pr.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            return false;
        } finally {
            {
                pr.close();
                rs.close();
            }
        }
    }
}

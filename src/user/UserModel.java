package user;

import dbUtil.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    Connection connection;

    public UserModel() {
        try {
            this.connection = DbConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (this.connection == null) {
            System.exit(1);
        }
    }

    public Boolean hasValidTransaction(int account) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM transact where id = ?";
        try {
            pr = this.connection.prepareStatement(sql);
            pr.setInt(1, account);
            rs = pr.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("asda");
            return false;
        } finally {
            {
                pr.close();
                rs.close();
            }
        }
    }
    public Boolean hasValidID(int account) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM user where id = ?";
        try {
            pr = this.connection.prepareStatement(sql);
            pr.setInt(1, account);
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

    public Boolean hasEnoughCash(int account,String amount) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT amount from user where id like '" + account + "%'";
        try {
            pr = this.connection.prepareStatement(sql);
            rs = pr.executeQuery();
            if (rs.next()) {
                String money =  rs.getString(1);
                Double accountMoney = Double.parseDouble(money);
                Double withDrawMoney = Double.parseDouble(amount);
                if(accountMoney>=withDrawMoney)
                     return  true;
                else
                     return false;

            }
        } catch (SQLException e) {
            return false;
        } finally {

            try {
                pr.close();
                rs.close();
            } catch (SQLException e) {
                return  false;
            }
        }
        return  false;
    }

    public Boolean changeTransaction(int account, String type,String amount) throws SQLException {
        String sqlInsert = "INSERT INTO transact(id,type,amount) VALUES (?,?,?)";
        try {
            Connection connection = DbConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlInsert);
            pr.setInt(1, account);
            pr.setString(2, type);
            pr.setString(3, amount);
            pr.execute();
            connection.close();
            return  true;
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
    }

}

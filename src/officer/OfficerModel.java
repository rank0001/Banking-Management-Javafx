package officer;

import dbUtil.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfficerModel {
    Connection connection;

    public OfficerModel() {
        try {
            this.connection = DbConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (this.connection == null) {
            System.exit(1);
        }
    }
    //getting the original amount of the user
    public String originalAmount(int account){
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT amount from user where id like '" + account + "%'";
        try {
            pr = this.connection.prepareStatement(sql);
            rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            return null;
        } finally {
            try{
                pr.close();
                rs.close();
            }
            catch (SQLException e){
            }
        }
        return  null;
    }
    //updating the amounts of the user
    public Boolean  updateAmount(String amount,int account) throws SQLException {
        PreparedStatement pr = null;
        ResultSet rs = null;
        char ch='"';

        String sql = "UPDATE user SET amount = " + amount + " " + "where id like " + ch
                + account +  "%" + ch;

        try {
            pr =this.connection.prepareStatement(sql);
            pr.executeUpdate();
            return  true;

        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        } finally {

            try{
                pr.close();
            }
            catch (SQLException e){

            }
        }
    }
    //deleting transaction
    public Boolean deleteTransaction(int account){
        String sqlInsert = "DELETE FROM transact WHERE id = ?";

        try{
            Connection connection = DbConnection.getConnection();
            PreparedStatement pr = connection.prepareStatement(sqlInsert);
            pr.setInt(1,account);
            pr.executeUpdate();
            connection.close();
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

}

package user;

import dbUtil.DbConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import login.Option;
import register.RegModel;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserController implements Initializable {


    private DbConnection dbConnection;
    private Connection connection;
    private UserModel userModel;

    @FXML
    private ComboBox<Options> combobox;
    @FXML
    private TextField account;
    @FXML
    private TextField amount;
    @FXML
    private Label confirmation;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.dbConnection = new DbConnection();
        this.userModel = new UserModel();
        this.combobox.setItems(FXCollections.observableArrayList(Options.values()));
        try {
            this.connection = DbConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (this.connection == null) {
            System.exit(1);
        }
    }

    @FXML
    public void transaction(ActionEvent actionEvent) {
        String options = this.combobox.getValue().toString();
        String accountId = this.account.getText();
        String withdrawMoney = this.amount.getText();
        if (options.equals("Deposit")) {
            try {
                Boolean isValidTransaction = this.userModel.hasValidTransaction(Integer.parseInt(accountId));
                Boolean isValidID = this.userModel.hasValidID(Integer.parseInt(accountId));
                if(!isValidTransaction && isValidID){
                    Boolean check = this.userModel.changeTransaction(Integer.parseInt(accountId), "Deposit", withdrawMoney);
                    if (check)
                        this.confirmation.setText("Your Transaction is ready for processing!");
                    else
                        this.confirmation.setText("This Transaction can not be done!");
                }
                else{
                    this.confirmation.setText("This Transaction can not be done!");
                }
            } catch (Exception localException) {
                this.confirmation.setText("This Transaction can not be done!");
            }
        } else {

            try {
                Boolean isValidID = this.userModel.hasValidID(Integer.parseInt(accountId));
                Boolean isValidTransaction = this.userModel.hasValidTransaction(Integer.parseInt(accountId));
                Boolean isValidMoney = this.userModel.hasEnoughCash(Integer.parseInt(accountId), withdrawMoney);
                System.out.println(isValidTransaction + " " + isValidMoney);
                if (!isValidTransaction && isValidMoney && isValidID) {
                    Boolean check = this.userModel.changeTransaction(Integer.parseInt(accountId), "Withdraw", withdrawMoney);
                    if (check)
                        this.confirmation.setText("Your Transaction is ready for processing!");
                    else
                        this.confirmation.setText("This Transaction can not be done!");
                } else {
                    this.confirmation.setText("This Transaction can not be done!");
                }
            } catch (Exception localException) {
                this.confirmation.setText("This Transaction can not be done!");
            }
        }

    }
}

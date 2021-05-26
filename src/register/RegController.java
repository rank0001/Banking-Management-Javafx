package register;
import dbUtil.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class RegController implements Initializable {

    private DbConnection dbConnection;
    private Connection connection;
    private RegModel regModel;

    @FXML
    private TextField name;

    @FXML
    private PasswordField password;

    @FXML
    private TextField account;

    @FXML
    private TextField deposit;

    @FXML
    private Label confirmation;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.dbConnection = new DbConnection();
        this.regModel = new RegModel();
        try {
            this.connection = DbConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (this.connection == null) {
            System.exit(1);
        }
    }

    //registration page
    @FXML
    private void register(ActionEvent event) {
        String password = this.password.getText();
        String name = this.name.getText();
        String amount = this.deposit.getText();
        String account = this.account.getText();
        int id = Integer.parseInt(account);
        if (name != null && password != null && account != null && amount != null) {
            try {
                Boolean check = this.regModel.registerUser(name, password, id, amount);
                if (check)
                    confirmation.setText("registration completed successfully!");
                else
                    confirmation.setText("Registration is invalid!");
            } catch (SQLException e) {
                e.printStackTrace();
                confirmation.setText("Registration is invalid!");
            }
        }

    }
}

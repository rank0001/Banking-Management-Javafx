package login;
import dbUtil.DbConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import officer.OfficerController;
import user.UserController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private DbConnection dbConnection;
    private Connection connection;
    private LoginModel loginModel;

    @FXML
    private ComboBox<Option> comboBox;
    @FXML
    private TextField name;
    @FXML
    private PasswordField password;
    @FXML
    private Label confirmation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.dbConnection = new DbConnection();
        this.loginModel = new LoginModel();
        this.comboBox.setItems(FXCollections.observableArrayList(Option.values()));
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
    public  void login(ActionEvent actionEvent){
        String options = this.comboBox.getValue().toString();
        if(options.equals("User")){
            try {
                if (this.loginModel.isLoginUser(this.name.getText(), this.password.getText())) {
                    userLogin();
                } else {
                    this.confirmation.setText("Wrong credentials!");
                }
            } catch (Exception localException) {
                this.confirmation.setText("Wrong credentials!");
                System.out.println("exception occured!");

            }
        }
        else{
            try {
                if (this.loginModel.isLoginOfficer(this.name.getText(), this.password.getText())) {
                        officerLogin();
                } else {
                    this.confirmation.setText("Wrong credentials!");
                }
            } catch (Exception localException) {
                System.out.println("exception occured!");
            }
        }

    }
    //user dashboard
    public void userLogin() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/user/user.fxml").openStream());
            UserController userController = (UserController) loader.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("User Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //officer dashboard
    public void officerLogin() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/officer/officer.fxml").openStream());
            OfficerController officerController = (OfficerController) loader.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Officer Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


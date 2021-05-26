package home;
import dbUtil.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import login.LoginController;
import register.RegController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    public DbConnection dbConnection;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dbConnection = new DbConnection();
    }
    @FXML
    public void register(ActionEvent event) {

        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/register/reg.fxml").openStream());
            RegController regController = (RegController) loader.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Registration");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void login(ActionEvent event) {

        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/login/login.fxml").openStream());
            LoginController loginController = (LoginController) loader.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("User Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
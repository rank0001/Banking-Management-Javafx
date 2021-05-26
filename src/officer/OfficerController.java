package officer;

import dbUtil.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OfficerController implements Initializable {
    @FXML
    private TextField account;
    @FXML
    private TextField amount;
    @FXML
    private Label confirmation;

    @FXML
    private TableView<TransactionData> transactionData;

    @FXML
    private TableColumn<TransactionData, String> idColumn;
    @FXML
    private TableColumn<TransactionData, String> typeColumn;
    @FXML
    private TableColumn<TransactionData, String> amountColumn;

    public DbConnection dbConnection;
    private ObservableList<TransactionData> data;
    private OfficerModel officerModel;
    private String sql = "SELECT * FROM transact";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dbConnection = new DbConnection();
        this.officerModel = new OfficerModel();
    }

    //displaying transaction results
    @FXML
    private void loadData(ActionEvent event) throws SQLException {
        try {
            Connection connection = DbConnection.getConnection();
            this.data = FXCollections.observableArrayList();
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                this.data.add(new TransactionData(resultSet.getString(1),
                        resultSet.getString(2), resultSet.getString(3)
                ));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("error");
        }
        this.idColumn.setCellValueFactory(new PropertyValueFactory<TransactionData, String>("id"));
        this.typeColumn.setCellValueFactory(new PropertyValueFactory<TransactionData, String>("type"));
        this.amountColumn.setCellValueFactory(new PropertyValueFactory<TransactionData, String>("amount"));
        this.transactionData.setItems(null);
        this.transactionData.setItems(this.data);
    }
//depositing to the account
    @FXML
    private void deposit(ActionEvent actionEvent) {
        int id = Integer.parseInt(this.account.getText());
        String amount = this.amount.getText();
        try {
            String originalAmount = this.officerModel.originalAmount(id);
            Double currentAmount = Double.parseDouble(amount) + Double.parseDouble(originalAmount);
            if (this.officerModel.updateAmount(currentAmount.toString(), id) &&
                    this.officerModel.deleteTransaction(id)) {
                this.confirmation.setText("Transaction completed succesfully!");
            } else {
                this.confirmation.setText("Transaction failed to complete!");
            }
        } catch (Exception localException) {
            this.confirmation.setText("Deposit could not be done!");
            System.out.println("exception occured!");
        }
    }
    //withdrawing money from account
    @FXML
    private void withdraw(ActionEvent actionEvent) {
        int id = Integer.parseInt(this.account.getText());
        String amount = this.amount.getText();
        try {
            String originalAmount = this.officerModel.originalAmount(id);
            if (Double.parseDouble((originalAmount)) < Double.parseDouble(amount)) {
                this.confirmation.setText("Withdrawal not allowed!");
            } else {
                Double currentAmount = Double.parseDouble(originalAmount) - Double.parseDouble(amount);
                if (this.officerModel.updateAmount(currentAmount.toString(), id) &&
                        this.officerModel.deleteTransaction(id)) {
                    this.confirmation.setText("Transaction completed succesfully!");
                } else {
                    this.confirmation.setText("Transaction failed to complete!");
                }
            }
        } catch (Exception localException) {
            this.confirmation.setText("Deposit could not be done!");
            System.out.println("exception occured!");
        }
    }

    //clearing the input data
    @FXML
    private void clearData(ActionEvent actionEvent) {
        this.account.setText("");
        this.amount.setText("");
    }

}

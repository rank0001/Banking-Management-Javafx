module Banking.Management {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;
    opens home;
    opens register;
    opens login;
    opens user;
    opens officer;
}
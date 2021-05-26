package officer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TransactionData {
    private final StringProperty type;
    private final StringProperty amount;
    private final StringProperty id;

    public TransactionData(String id,String type, String amount) {
        this.type = new SimpleStringProperty(type);
        this.amount = new SimpleStringProperty(amount);
        this.id = new SimpleStringProperty(id);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getAmount() {
        return amount.get();
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }
}

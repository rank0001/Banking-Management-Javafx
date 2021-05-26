package user;

//for tweaking the combobox
public enum Options {
    Deposit, Withdraw;
    private Options() {

    }

    public String value() {
        return name();
    }

    public static Options fromValue(String v) {
        return valueOf(v);
    }
}

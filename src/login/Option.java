package login;

//for tweaking the combobox
public enum Option {
    Officer, User;
    private Option() {
    }

    public String value() {
        return name();
    }

    public static Option fromValue(String v) {
        return valueOf(v);
    }
}

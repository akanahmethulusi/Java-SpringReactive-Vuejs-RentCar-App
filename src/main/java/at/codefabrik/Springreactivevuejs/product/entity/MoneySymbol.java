package at.codefabrik.Springreactivevuejs.product.entity;

public enum MoneySymbol {
    EUR("Euro", "€"),
    USD("US Dollar", "$"),
    GBP("Pound Sterling", "£"),
    JPY("Japanese Yen", "¥"),
    CHF("Swiss Franc", "CHF");

    private final String label;
    private final String symbol;

    MoneySymbol(String label, String symbol) {
        this.label = label;
        this.symbol = symbol;
    }
    public String getLabel() {
        return label;
    }
    public String getSymbol() {
        return symbol;
    }
}

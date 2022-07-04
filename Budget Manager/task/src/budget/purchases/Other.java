package budget.purchases;

public class Other extends Purchase {
    public Other(String name, double price) {
        super(name, price);
    }

    public Other() {
    }

    @Override
    public String toString() {
        return String.format(
                "Other:\n%s $%.2f%n", Other.super.getName(), Other.super.getPrice()).replace(",", ".");
    }

    @Override
    public void printer() {
        System.out.print(String.format("%s$%.2f%n", Other.super.getName(), Other.super.getPrice()).replace(",", "."));
    }
}

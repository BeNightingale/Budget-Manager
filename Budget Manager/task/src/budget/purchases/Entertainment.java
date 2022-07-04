package budget.purchases;

public class Entertainment extends Purchase {
    public Entertainment(String name, double price) {
        super(name, price);
    }

    public Entertainment() {
    }

    @Override
    public String toString() {
        return String.format(
                "Entertainment:\n%s $%.2f%n", Entertainment.super.getName(), Entertainment.super.getPrice()).replace(",", ".");
    }
    @Override
    public void printer() {
        System.out.print(String.format("%s$%.2f%n", Entertainment.super.getName(), Entertainment.super.getPrice()).replace(",", "."));
    }
}

package budget.purchases;

public class Clothes extends Purchase {
    public Clothes(String name, double price) {
        super(name, price);
    }

    public Clothes() {
    }

    @Override
    public String toString() {
        return String.format(
                "Clothes:\n%s $%.2f%n", Clothes.super.getName(), Clothes.super.getPrice()).replace(",", ".");
    }
    @Override
    public void printer() {
        System.out.print(String.format("%s$%.2f%n", Clothes.super.getName(), Clothes.super.getPrice()).replace(",", "."));
    }
}

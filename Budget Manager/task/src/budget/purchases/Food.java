package budget.purchases;

public class Food extends Purchase {
    public Food(String name, double price) {
        super(name, price);
    }

    public Food() {
    }

    @Override
    public String toString() {
        return String.format(
                "Food:\n%s $%.2f%n", Food.super.getName(), Food.super.getPrice()).replace(",", ".");
    }

    @Override
    public void printer() {
        System.out.print(String.format("%s$%.2f%n", Food.super.getName(), Food.super.getPrice()).replace(",", "."));
    }
}

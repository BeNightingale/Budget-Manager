package budget.purchases;

import java.io.Serializable;

public class Purchase implements Serializable {
    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Purchase() {

    }

    public Purchase(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f%n", name, price);
    }

    public void printer() {
        System.out.print(String.format("%s$%.2f%n", getName(), getPrice()).replace(",", "."));
    }
}

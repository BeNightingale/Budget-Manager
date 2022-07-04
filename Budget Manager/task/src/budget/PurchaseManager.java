package budget;

import budget.purchases.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class PurchaseManager {
    private final List<Purchase> purchaseList = new ArrayList<>();
    private double sum;
    public void addPurchase(Purchase purchase) {
        purchaseList.add(purchase);
        sum += purchase.getPrice();
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void showList(List<Purchase> list) {
        if (list.isEmpty()) {
            System.out.println("The purchase list is empty");
        } else {
            list.forEach(Purchase::printer);
            System.out.print(String.format("Total sum: $%.2f\n", getSumOfExpensesType(list)).replace(",", "."));
        }
    }

    public double getSumOfExpensesType(List<Purchase> list) {
        return list.stream().map(Purchase::getPrice).mapToDouble(x -> x).sum();
    }

    public Purchase createPurchase(int choice, Scanner scanner) {
        Purchase purchase = null;
        switch (choice) {
            case 1 -> purchase = new Food();
            case 2 -> purchase = new Clothes();
            case 3 -> purchase = new Entertainment();
            case 4 -> purchase = new Other();
            default -> {
            }
        }
        scanner.nextLine();
        System.out.println("\nEnter purchase name:");
        purchase.setName(scanner.nextLine());
        System.out.println("Enter its price:");
        purchase.setPrice(scanner.nextDouble());
        return purchase;
    }

    public double getSum() {
        return sum;
    }
}

package budget;

import budget.purchases.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class Util {
    private Account account;
    private PurchaseManager purchaseManager;
    private Scanner scanner;

    UnaryOperator<List<Purchase>> food = (x) -> {
                System.out.println("\nFood:");
                return x.stream()
                    .filter(a -> a instanceof Food)
                    .collect(Collectors.toList());
                };

    UnaryOperator<List<Purchase>> clothes = (x) -> {
                System.out.println("\nClothes:");
                return x.stream()
                    .filter(a -> a instanceof Clothes)
                    .collect(Collectors.toList());
                };

    UnaryOperator<List<Purchase>> entertainment = (x) -> {
                System.out.println("\nEntertainment:");
                return x.stream()
                    .filter(a -> a instanceof Entertainment)
                    .collect(Collectors.toList());
                };

    UnaryOperator<List<Purchase>> other = (x) -> {
                System.out.println("\nOther:");
                return x.stream()
                    .filter(a -> a instanceof Other)
                    .collect(Collectors.toList());
                };

    UnaryOperator<List<Purchase>> all = (x) -> {
                System.out.println("\nAll:");
                return x;
                };

    private final Map<Integer, UnaryOperator<List<Purchase>>> mapOfPurchaseLists = Map.of(
            1, food,
            2, clothes,
            3, entertainment,
            4, other,
            5, all);

    Runnable sortAll = () -> {
        List<Purchase> list = purchaseManager.getPurchaseList();
        list.sort(new PurchaseComparator().reversed());
        if (list.isEmpty()) {
            System.out.println("\nThe purchase list is empty!");
        } else {
            purchaseManager.showList(mapOfPurchaseLists.get(5).apply(list));
        }
    };
    Runnable sortByType = () -> {
        if (purchaseManager.getPurchaseList().isEmpty()) {
            System.out.println("\nTypes:\n" +
                    "Food - $0\n" +
                    "Entertainment - $0\n" +
                    "Clothes - $0\n" +
                    "Other - $0\n" +
                    "Total sum: $0");
        } else {
            double[] sum = new double[5];
            sum[0] = purchaseManager.getPurchaseList().stream().filter(a -> a instanceof Food).map(Purchase::getPrice).mapToDouble(x -> x).sum();
            sum[1] = purchaseManager.getPurchaseList().stream().filter(a -> a instanceof Entertainment).map(Purchase::getPrice).mapToDouble(x -> x).sum();
            sum[2] = purchaseManager.getPurchaseList().stream().filter(a -> a instanceof Clothes).map(Purchase::getPrice).mapToDouble(x -> x).sum();
            sum[3] = purchaseManager.getPurchaseList().stream().filter(a -> a instanceof Other).map(Purchase::getPrice).mapToDouble(x -> x).sum();
            sum[4] = purchaseManager.getSum();
            System.out.println(String.format("\nTypes:\n" +
                    "Food - $%.2f\n" +
                    "Entertainment - $%.2f\n" +
                    "Clothes - $%.2f\n" +
                    "Other - $%.2f\n" +
                    "Total sum: $%.2f", sum[0], sum[1], sum[2], sum[3], sum[4]).replace(",", "."));
            }
        };
    Runnable sortCertainType = () -> {
        List<Purchase> list = purchaseManager.getPurchaseList();
        list.sort(new PurchaseComparator().reversed());
        int choice;
        System.out.println();
        System.out.println(Menu.getSortingByPurchaseTypeMenu());
        choice = scanner.nextInt();
        if (list.isEmpty()) {
            System.out.println("\nThe purchase list is empty!");
        } else {
            purchaseManager.showList(mapOfPurchaseLists.get(choice).apply(list));
        }
    };

    private final Map<Integer, Runnable> sortMap = Map.of(
            1, sortAll,
            2, sortByType,
            3, sortCertainType
    );

    Runnable lambda0 = () -> System.out.println("\nBye!");

    Runnable lambda1 = () -> {
        System.out.println("\nEnter income:");
        account.addIncome(scanner.nextDouble());
        System.out.println("Income was added!\n");
    };

    Runnable lambda2 = () -> {
        int choice;
        do {
            System.out.println();
            System.out.println(Menu.getPurchaseMenu());
            choice = scanner.nextInt();
            if (choice != 5) {
                Purchase purchase = purchaseManager.createPurchase(choice, scanner);
                purchaseManager.addPurchase(purchase);
                account.addIncome(-purchase.getPrice());
                System.out.println("Purchase was added!");
            }
        } while (choice != 5);
        System.out.println();
    };

    Runnable lambda3 = () -> {
        int choice;
        do {
            System.out.println();
            System.out.println(Menu.getPurchasesListMenu());
            choice = scanner.nextInt();
            if (choice != 6) {
                purchaseManager.showList(mapOfPurchaseLists.get(choice).apply(purchaseManager.getPurchaseList()));
            }
        } while (choice != 6);
        System.out.println();
    };

    Runnable lambda4 = () -> System.out.printf("\nBalance: $%.2f\n\n", account.getBalance());

    Runnable lambda5 = () -> {
        File file = new File("purchases.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(String.format("%.2f", account.getBalance()));
            fileWriter.write("\n");
            purchaseManager.getPurchaseList().forEach(x -> {
                try {
                    fileWriter.write(x.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            System.out.println("\nPurchases were saved!\n");
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };

    Runnable lambda6 = () -> {
        Path path = Paths.get("/Users/beatka/IdeaProjects/Budget Manager/purchases.txt");
        try {
            List<String> allLines = Files.readAllLines(path);
            account.setBalance(Double.parseDouble(
                    allLines.get(0).replace(",", "."))
            );
            for (int i = 1; i < allLines.size() - 1; i += 2) {
                String[] nameAndPrice = allLines.get(i + 1).split("\\$");
                switch (allLines.get(i)) {
                    case "Food:" ->
                            purchaseManager.addPurchase(new Food(nameAndPrice[0], Double.parseDouble(nameAndPrice[1])));
                    case "Clothes:" ->
                            purchaseManager.addPurchase(new Clothes(nameAndPrice[0], Double.parseDouble(nameAndPrice[1])));
                    case "Entertainment:" ->
                            purchaseManager.addPurchase(new Entertainment(nameAndPrice[0], Double.parseDouble(nameAndPrice[1])));
                    default -> {
                        Other other;
                        if (nameAndPrice.length == 3) {
                            other = new Other(nameAndPrice[0] + "$" + nameAndPrice[1], Double.parseDouble(nameAndPrice[2]));
                        } else {
                            other = new Other(nameAndPrice[0], Double.parseDouble(nameAndPrice[1]));
                        }
                        purchaseManager.addPurchase(other);
                    }
                }
                }

            System.out.println("\nPurchases were loaded!\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };

    Runnable lambda7 = () -> {
        int choice;
        do {
            System.out.println();
            System.out.println(Menu.getSortingMenu());
            choice = scanner.nextInt();
            if (choice != 4) {
                sortMap.get(choice).run();
            }
        } while (choice != 4);
        System.out.println();
    };
    private final Map<Integer, Runnable> map = Map.of(
            0, lambda0,
            1, lambda1,
            2, lambda2,
            3, lambda3,
            4, lambda4,
            5, lambda5,
            6, lambda6,
            7, lambda7);

    public Map<Integer, Runnable> getMap() {
            return map;
        }

    public Util(Account account, Scanner scanner, PurchaseManager purchaseManager) {
            this.account = account;
            this.scanner = scanner;
            this.purchaseManager = purchaseManager;
    }
}

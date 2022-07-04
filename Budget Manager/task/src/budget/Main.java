package budget;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Account account = new Account();
        PurchaseManager purchaseManager = new PurchaseManager();
        Util util = new Util(account, scanner, purchaseManager);
        int choice;
        do {
            System.out.println(Menu.getRoot());
            choice = scanner.nextInt();
            util.getMap().get(choice).run();
        } while (choice != 0);


    }
}

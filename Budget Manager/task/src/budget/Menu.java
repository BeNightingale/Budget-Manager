package budget;

public class Menu {
    private static final String root = "Choose your action:\n" +
            "1) Add income\n" +
            "2) Add purchase\n" +
            "3) Show list of purchases\n" +
            "4) Balance\n" +
            "5) Save\n" +
            "6) Load\n" +
            "7) Analyze (Sort)\n" +
            "0) Exit";
    private static final String purchaseMenu = "Choose the type of purchase\n" +
            "1) Food\n" +
            "2) Clothes\n" +
            "3) Entertainment\n" +
            "4) Other\n" +
            "5) Back";
    private static final String purchasesListMenu = "Choose the type of purchases\n" +
            "1) Food\n" +
            "2) Clothes\n" +
            "3) Entertainment\n" +
            "4) Other\n" +
            "5) All\n" +
            "6) Back";

    private static final String sortingMenu = "How do you want to sort?\n" +
            "1) Sort all purchases\n" +
            "2) Sort by type\n" +
            "3) Sort certain type\n" +
            "4) Back";

    private static final String sortingByPurchaseTypeMenu = "Choose the type of purchase\n" +
            "1) Food\n" +
            "2) Clothes\n" +
            "3) Entertainment\n" +
            "4) Other";

    public static String getRoot() {
        return root;
    }
    public static String getPurchaseMenu() {
        return purchaseMenu;
    }
    public static String getPurchasesListMenu() {
        return purchasesListMenu;
    }

    public static String getSortingMenu() {
        return sortingMenu;
    }

    public static String getSortingByPurchaseTypeMenu() {
        return sortingByPurchaseTypeMenu;
    }

}

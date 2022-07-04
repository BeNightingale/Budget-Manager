package budget;

import budget.purchases.Purchase;
import java.util.Comparator;

public class PurchaseComparator implements Comparator<Purchase> {
    @Override
    public int compare(Purchase o1, Purchase o2) {
        return Double.compare(o1.getPrice(), o2.getPrice());
    }
}

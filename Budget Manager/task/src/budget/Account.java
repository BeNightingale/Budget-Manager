package budget;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private final List<Double> incomeList = new ArrayList<>();
    private double balance;

    public void addIncome(double lastIncome) {
        incomeList.add(lastIncome);
        this.balance += lastIncome;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

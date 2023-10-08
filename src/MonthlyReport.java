import java.util.ArrayList;

public class MonthlyReport {
    int year;
    int month;

    ArrayList <Record> expenses;
    ArrayList <Record> earnings;

    public int sumExpenses() {
        return getSum(expenses);
    }

    public int sumEarnings() {
        return getSum(earnings);
    }


    public int getSum(ArrayList<Record> records) {
        int sum = 0;
        for (Record r : records) {
            sum += r.sum();
        }
        return sum;
    }

    public Record getMax(ArrayList<Record> records) {
        int max = Integer.MIN_VALUE;
        Record maxRecord = null;
        for (Record r : records) {
            if (r.sum() > max) {
                max = r.sum();
                maxRecord = r;
            }
        }
        return maxRecord;
    }


    public Record getMaxEarning() {
        return getMax(earnings);
    }

    public Record getMaxExpense() {
        return getMax(expenses);
    }
}

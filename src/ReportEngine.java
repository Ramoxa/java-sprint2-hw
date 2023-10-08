import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class ReportEngine {

    HashMap<Integer, MonthlyReport> monthlyReports = new HashMap<>();
    YearlyReport yearlyReport = new YearlyReport();

    FileReader fileReader = new FileReader();

    void readYearlyReports() {

        String fileName = "y.2021.csv";
        ArrayList<String> strings = fileReader.readFileContents(fileName);
        strings.remove(0);

        for (String string : strings) {
            String[] split = string.split(",");
            int month = Integer.parseInt(split[0]);
            int amount = Integer.parseInt(split[1]);
            boolean isExpense = Boolean.parseBoolean(split[2]);

            YearlyRecord yearlyRecord = yearlyReport.records.get(month);
            if (yearlyRecord == null) {
                if (isExpense == true) {
                    yearlyReport.records.put(month, new YearlyRecord(month, amount, 0));
                } else {
                    yearlyReport.records.put(month, new YearlyRecord(month, 0, amount));
                }
            } else {
                if (isExpense == true) {
                    yearlyRecord.expense = amount;
                } else {
                    yearlyRecord.earning = amount;
                }
            }
        }
    }

    void readMonthlyReports() {
        for (int i = 1; i <= 3; i++) {
            String filename = "m.20210" + i + ".csv";
            ArrayList<String> strings = fileReader.readFileContents(filename);
            strings.remove(0);
            MonthlyReport monthlyReport = new MonthlyReport();
            monthlyReport.year = 2021;
            monthlyReport.month = i;
            monthlyReport.expenses = new ArrayList<>();
            monthlyReport.earnings = new ArrayList<>();

            for (String string : strings) {
                String[] split = string.split(",");
                Record record = new Record(split[0],
                        Integer.parseInt(split[2]),
                        Integer.parseInt(split[3]));
                if (split[1].equals("TRUE")) {
                    monthlyReport.expenses.add(record);
                } else {
                    monthlyReport.earnings.add(record);
                }
            }
            monthlyReports.put(i, monthlyReport);
        }

    }

    public void printMonthlyReports() {

        if (monthlyReports.isEmpty()) {
            System.out.println("Нет файлов с месячными отчетами.");
        }
        System.out.println("Считаны месячные отчеты за: ");
        for (Integer month : monthlyReports.keySet()) {
            System.out.println("Месяц: " + month);

        }
    }

    public void printYearlyReports() {
        if (yearlyReport.records.isEmpty()) {
            System.out.println("Нет файла с годовым отчетом.");
        } else {
            System.out.println("Считан годовой отчет за 2021 г.");
        }

    }

    // самый прибыльный товар
    public void showMonthlyReportInfo() {

        for (int i = 1; i <= 3; i++) {
            Record maxEarning = monthlyReports.get(i).getMaxEarning();
            Record maxExpense = monthlyReports.get(i).getMaxExpense();
            System.out.println("Месяц: " + monthlyReports.get(i).month);
            System.out.println("Самый прибыльный товар: " + maxEarning.name + " " + maxEarning.sum());
            System.out.println("Самая большая трата: " + maxExpense.name + " " + maxExpense.sum());
        }
    }

    public void showYearlyReportInfo() {
        for (int i = 1; i <= 3; i++) {
            ArrayList<Integer> expenses = null;
            expenses.add(yearlyReport.records.get(i).expense);
            int sumExpense = expenses.stream().mapToInt(Integer::intValue).sum();

            ArrayList <Integer> earnings = null;
            expenses.add(yearlyReport.records.get(i).earning);
            int sumEarning = earnings.stream().mapToInt(Integer::intValue).sum();

            int profit = sumEarning - sumExpense;

            System.out.println (profit);

        }
    }
    public void check() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(i);
            System.out.println(monthlyReports.get(i).sumEarnings());
            System.out.println(monthlyReports.get(i).sumExpenses());
            System.out.println(yearlyReport.records.get(i).earning);
            System.out.println(yearlyReport.records.get(i).expense);
        }
    } }









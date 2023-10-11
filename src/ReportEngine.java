import java.util.ArrayList;
import java.util.HashMap;

public class ReportEngine {

    HashMap<Integer, MonthlyReport> monthlyReports = new HashMap<>();
    YearlyReport yearlyReport = new YearlyReport();
    FileReader fileReader = new FileReader();
    void readYearlyReports() {
        String fileName = "y.2021.csv";
        ArrayList<String> strings = fileReader.readFileContents(fileName);
        if (strings == null) {
            System.out.println("В системе нет файла с годовым отчетом");
            System.out.println("Загрузите файл с годовым отчетом");
            Main.printMenu();
        } else {
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
    }

    void readMonthlyReports() {
        for (int i = 1; i <= 3; i++) {
            String filename = "m.20210" + i + ".csv";
            ArrayList<String> strings = fileReader.readFileContents(filename);
            if (strings == null) {
                System.out.println("В системе нет файла с годовым отчетом");
                System.out.println("Загрузите файл с годовым отчетом");
                Main.printMenu();
            } else {
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

    public void showMonthlyReportInfo() {
        if (monthlyReports.isEmpty()) {
            System.out.println("Нет файлов с месячными отчетами.");
            System.out.println("Для выполения операции сначала выполните команду 1.");
            Main.printMenu();
        }
        else {
            for (int i = 1; i <= 3; i++) {
                Record maxEarning = monthlyReports.get(i).getMaxEarning();
                Record maxExpense = monthlyReports.get(i).getMaxExpense();
                System.out.println("Месяц: " + monthlyReports.get(i).month);
                System.out.println("Самый прибыльный товар: " + maxEarning.name + " " + maxEarning.sum());
                System.out.println("Самая большая трата: " + maxExpense.name + " " + maxExpense.sum());
            } }
    }

    public void showYearlyReportInfo() {
        if (yearlyReport.records.isEmpty()) {
            System.out.println("Нет файла с годовым отчетом.");
            System.out.println("Для выполения операции сначала выполните команду 2.");
            Main.printMenu();
        } else {
            HashMap<Integer, Integer> earnings = new HashMap<>();
            HashMap<Integer, Integer> expenses = new HashMap<>();

            for (int i = 1; i <= 3; i++) {
                int earning = yearlyReport.records.get(i).earning;
                int expense  = yearlyReport.records.get(i).expense;
                int profit = earning - expense;
                System.out.println ("Прибыль за " + i + " месяц " + profit);
                earnings.put(i,earning);
                expenses.put(i, expense);
            }

            int sumEarnings = earnings.values().stream().mapToInt(Integer::intValue).sum();
            int sumeExpense = expenses.values().stream().mapToInt(Integer::intValue).sum();
            System.out.println ("Доход за год " + sumEarnings);
            System.out.println ("Расход за год " + sumeExpense);

            int averageEarning = sumEarnings / earnings.size();
            System.out.println ("Средний доход за все имеющиеся операции в году " +  averageEarning);

            int averageExpense = sumeExpense / expenses.size();
            System.out.println ("Средний расход за все имеющиеся операции в году " +  averageExpense);
        }
    }

public  void check () {
    if ((monthlyReports.isEmpty()) || yearlyReport.records.isEmpty()) {
        System.out.println("Нет файлов с месячными отчетами.");
        System.out.println("Для выполения операции сначала выполните команды 1 и 2.");
        Main.printMenu();
    }
    else {
        for (int i = 1; i <= 3; i++) {
            System.out.println(i);
            System.out.println("Доходы согласно месячному отчету " + monthlyReports.get(i).sumEarnings());
            System.out.println("Доходы согласно годовому отчету " + yearlyReport.records.get(i).earning);
            System.out.println("Расходы согласно месячному отчету " + monthlyReports.get(i).sumExpenses());
            System.out.println("Расходы согласно годовому отчету " + yearlyReport.records.get(i).expense);

            if ((monthlyReports.get(i).sumEarnings() == yearlyReport.records.get(i).earning)
            && (monthlyReports.get(i).sumExpenses() == yearlyReport.records.get(i).expense)) {
                System.out.println("Отчеты сверены, ошибок не обнаружено");
            }
            else {
                System.out.println("В " + i +" месяце обнаружена ошибка");
            }

        }
    }
    }

}

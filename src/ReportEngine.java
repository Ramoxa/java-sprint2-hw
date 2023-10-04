import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;

public class ReportEngine {

    HashMap<Integer, MonthlyReport> monthlyReports = new HashMap<>();

    ArrayList<YearlyReport> yearlyReports = new ArrayList<>();

    FileReader fileReader = new FileReader();

    void readYearlyReports() {

        String fileName = "y.2021.csv";
        ArrayList<String> strings = fileReader.readFileContents(fileName);
        strings.remove(0);

        for (String string : strings) {
            YearlyReport yearlyReport = new YearlyReport();
            String[] split = string.split(",");
            yearlyReport.month = Integer.parseInt(split[0]);
            yearlyReport.amount = Integer.parseInt(split[1]);
            yearlyReport.isExpence = Boolean.parseBoolean(split[2]);

            yearlyReports.add(yearlyReport);
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
        if (yearlyReports.isEmpty()) {
            System.out.println("Нет файла с годовым отчетом.");
        } else {
            System.out.println("Считан годовой отчет за 2021 г.");
        }

    }

    public String getTopName() {

        HashMap<String, Integer> freqs = new HashMap<>();
        for (Record record : monthlyReports) {
            freqs.put(record.name, freqs.getOrDefault(record.name, 0) + record.quantity);
        }
        String maxName = null;
        for (String title : freqs.keySet()) {
            if (maxName == null) {
                maxName = title;
                continue;
            }
            if (freqs.get(maxName) < freqs.get(title)) {
                maxName = title;
            }
        }
        return maxName;
    }


    public int profit() {



            }
        }

    }


}











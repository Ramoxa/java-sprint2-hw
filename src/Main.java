
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        printMenu();
    }

    private static void printMenu() {
        System.out.println("Выберите одно из действий: ");
        System.out.println("1 - Считать все месячные отчёты.");
        System.out.println("2 - Считать годовой отчёт.");
        System.out.println("3 - Сверить отчёты.");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах.");
        System.out.println("5 - Вывести информацию о годовом отчёте.");
        System.out.println("6 - Выход.");

        Scanner scanner = new Scanner(System.in);
        ReportEngine reportEngine = new ReportEngine();
        YearlyReport yearlyReport = new YearlyReport();

        while (true) {
            int command = scanner.nextInt();
            if (command == 1) {
                reportEngine.readMonthlyReports();
                reportEngine.printMonthlyReports();
            } else if (command == 2) {
                reportEngine.readYearlyReports();
                reportEngine.printYearlyReports();
            } else if (command == 3) {
                if ((reportEngine.monthlyReports.isEmpty()) && (yearlyReport.records.isEmpty())) {
                    System.out.println("Нет файлов с месячными отчетами.");
                    System.out.println("Для выполения операции сначала выполните команды 1 и 2.");
                    printMenu();
                }
                   else {
                    reportEngine.check();
                }
            } else if (command == 4) {
                if (reportEngine.monthlyReports.isEmpty()) {
                    System.out.println("Нет файлов с месячными отчетами.");
                    System.out.println("Для выполения операции сначала выполните команду 1.");
                    printMenu();
                }
                else {
                reportEngine.showMonthlyReportInfo(); }
            } else if (command == 5) {
                if (yearlyReport.records.isEmpty()) {
                    System.out.println("Нет файла с годовым отчетом.");
                    System.out.println("Для выполения операции сначала выполните команду 2.");
                    printMenu();
                } else {
                    reportEngine.showYearlyReportInfo();
                }
            } else if (command == 6) {
                System.out.println("Программа завершена.");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
                printMenu();
                break;
            }
        }
    }
}





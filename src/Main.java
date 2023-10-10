
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportEngine reportEngine = new ReportEngine();
        printMenu();

        while (true) {
            int command = scanner.nextInt();
            if (command == 1) {
                reportEngine.readMonthlyReports();
                reportEngine.printMonthlyReports();
            } else if (command == 2) {
                reportEngine.readYearlyReports();
                reportEngine.printYearlyReports();
            } else if (command == 3) {
                reportEngine.check();
            } else if (command == 4) {
                reportEngine.showMonthlyReportInfo();
            } else if (command == 5) {
                reportEngine.showYearlyReportInfo();
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

    public static void printMenu() {
        System.out.println("Выберите одно из действий: ");
        System.out.println("1 - Считать все месячные отчёты.");
        System.out.println("2 - Считать годовой отчёт.");
        System.out.println("3 - Сверить отчёты.");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах.");
        System.out.println("5 - Вывести информацию о годовом отчёте.");
        System.out.println("6 - Выход.");
    }
}





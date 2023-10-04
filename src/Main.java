

public class Main {

    public static void main(String[] args) {

        ReportEngine reportEngine = new ReportEngine();
        reportEngine.readMonthlyReports ();
        reportEngine.readYearlyReports();

        reportEngine.printMonthlyReports ();
        reportEngine.printYearlyReports ();
        reportEngine.getTopName();
        System.out.println(reportEngine);
    }
}


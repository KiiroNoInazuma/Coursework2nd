import service.TaskService;
import task.*;


public class Menu {
    public static void main(String[] args) {

        DailyTask dailyTask = new DailyTask("тест1esagfesgegsegesge", Type.WORK, "11.02.2023 12:12", "test2");
        MonthlyTask monthlyTask = new MonthlyTask("тест2", Type.WORK, "16.02.2023 12:12", "test2");
        WeeklyTask weeklyTask = new WeeklyTask("тест3", Type.WORK, "12.02.2023 12:12", "test2");

        TaskService ts = new TaskService();
        ts.add(dailyTask);
        ts.add(monthlyTask);
        ts.remove(2);
        ts.getAllByDate("16.02.2023 12:12").forEach(System.out::println);


    }
}

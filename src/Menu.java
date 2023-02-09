import service.TaskService;
import task.*;

import java.time.LocalDateTime;

public class Menu {
    public static void main(String[] args) {

       DailyTask dailyTask = new DailyTask("тест1esagfesgegsegesge", Type.WORK, LocalDateTime.now(), "test2");
        MonthlyTask monthlyTask = new MonthlyTask("тест2", Type.WORK, LocalDateTime.now(), "test2");
        WeeklyTask weeklyTask = new WeeklyTask("тест3", Type.WORK, LocalDateTime.now(), "test2");

        TaskService ts = new TaskService();
        ts.add(dailyTask);
        ts.add(monthlyTask);





    }
}

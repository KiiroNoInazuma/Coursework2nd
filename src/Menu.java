import service.TaskService;
import task.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Menu {
    public static void main(String[] args) {

        OneTimeTask dailyTask = new OneTimeTask("тест1esagfesgegsegesge", Type.WORK, "11.02.2023 12:12", "test2");
        MonthlyTask monthlyTask = new MonthlyTask("тест2", Type.WORK, "28.01.2022 13:12", "test2");
        YearlyTask yearlyTask = new YearlyTask("тест3", Type.WORK, "31.01.2023 13:12", "test2");
        TaskService ts = new TaskService();
        ts.add(dailyTask);
        ts.add(monthlyTask);
        ts.add(yearlyTask);
        //ts.remove(2);
        DateTimeFormatter conversion = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        ts.getAllByDate(LocalDate.parse("31.01.2024 13:12", conversion)).forEach(System.out::println);

    }
}

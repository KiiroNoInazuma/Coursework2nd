package task;

import java.time.LocalDateTime;

public class WeeklyTask extends Task{
    public WeeklyTask(String title, Type type, LocalDateTime dateTime, String description) {
        super(title, type, dateTime, description);
    }
}

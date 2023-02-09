package task;

import java.time.LocalDateTime;

public class DailyTask extends Task {
    public DailyTask(String title, Type type, LocalDateTime dateTime, String description) {
        super(title, type, dateTime, description);
    }
}

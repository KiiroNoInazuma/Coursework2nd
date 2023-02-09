package task;

import java.time.LocalDateTime;

public class OneTimeTask extends Task{
    public OneTimeTask(String title, Type type, LocalDateTime dateTime, String description) {
        super(title, type, dateTime, description);
    }
}

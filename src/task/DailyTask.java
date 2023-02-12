package task;

import java.time.LocalDate;

public class DailyTask extends Task {
    public DailyTask(String title, Type type, String dateTime, String description) {
        super(title, type, dateTime, description);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        LocalDate outTime = getDateTime().toLocalDate();
        return localDate.equals(outTime)||localDate.isAfter(outTime);
    }
}

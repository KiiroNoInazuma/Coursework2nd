package task;

import java.time.LocalDate;

public class OneTimeTask extends Task{

    public OneTimeTask(String title, Type type, String dateTime, String description) {
        super(title, type, dateTime, description);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return localDate.equals(getDateTime().toLocalDate());
    }
}

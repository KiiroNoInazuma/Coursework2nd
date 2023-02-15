package task;

import java.time.LocalDate;
import java.time.Period;

public class YearlyTask extends Task{
    public YearlyTask(String title, Type type, String dateTime, String description) {
        super(title, type, dateTime, description);
    }

    @Override
   public boolean appearsIn(LocalDate localDate) {
        LocalDate outTime = getDateTime().toLocalDate();
        return Period.between(outTime, localDate).getDays() == 0;
    }
}

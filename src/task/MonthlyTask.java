package task;

import java.time.LocalDate;
import java.time.Period;

public class MonthlyTask extends Task {
    public MonthlyTask(String title, Type type, String dateTime, String description) {
        super(title, type, dateTime, description);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        LocalDate outTime = getDateTime().toLocalDate();
        int check = Period.between(outTime, localDate).getDays();
        if (localDate.getMonthValue() == 2) return check == 28;
        if (localDate.lengthOfMonth() == 31) {
            return check == 0;
        } else {
            return check == 0 || check == 30;
        }
    }
}

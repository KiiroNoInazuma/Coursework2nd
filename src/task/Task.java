package task;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class Task {
    private static int idGenerator;
    private final int id;
    private String title;
    private final Type type;
    private final LocalDateTime dateTime;
    private String description;

    public Task(String title, Type type, LocalDateTime dateTime, String description) {
        idGenerator++;
        this.id = idGenerator;
        this.title = title;
        this.type = type;

        this.dateTime = dateTime;
        this.description = description;
    }

    abstract boolean appearsIn(LocalDate localDate);
    @Override
    public String toString() {
        return id + ". " + "\t\t\tЗадача" + "<<" + type + ">>: " + title + "\n Дата и время напоминания: " + dateTime + " \nОписание: " + description;
    }
}

package task;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public abstract class Task {
    private static int idGenerator;
    private final int id;
    private String title;
    private final Type type;
    private final LocalDateTime dateTime;
    private String description;

    public Task(String title, Type type, String dateTime, String description) {
        DateTimeFormatter conversion = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        idGenerator++;
        this.id = idGenerator;
        this.title = title;
        this.type = type;
        this.dateTime = LocalDateTime.parse(dateTime, conversion);
        this.description = description;
    }

    public abstract boolean appearsIn(LocalDate localDate);

    @Override
    public String toString() {
        return id + ". " + "\t\t\tЗадача" + "<<" + type + ">>: " + title + "\n Дата и время напоминания: " + dateTime + " \nОписание: " + description;
    }
}

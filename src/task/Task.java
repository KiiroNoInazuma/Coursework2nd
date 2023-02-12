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
        String date = dateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        return id + ". " + "\t\t\tЗадача" + "<<" + type.type() + ">>: " + title + "\n Дата и время напоминания: " + date + " \nОписание: " + description;
    }
}

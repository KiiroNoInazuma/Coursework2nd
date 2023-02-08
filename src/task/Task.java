package task;

import java.time.LocalDateTime;

public class Task {
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

    @Override
    public String toString() {
        return id + ". " + "\t\t\tЗадача" + "<<" + type + ">>: " + title + "\n Дата и время напоминания: " + dateTime + " \nОписание: " + description;
    }
}

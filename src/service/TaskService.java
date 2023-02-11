package service;

import task.Task;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

public class TaskService {
    private final Map<Integer, Task> taskMap;
    private final Collection<Task> removedTasks;

    public TaskService() {
        taskMap = new HashMap<>();
        removedTasks = new ArrayList<>();
    }

    public void add(Task task) {
        taskMap.put(task.getId(), task);
    }

    public Task remove(int id) {
        removedTasks.add(taskMap.get(id));
        taskMap.remove(id);
        return removedTasks.stream().toList().get(id - 1);
    }

    public Collection<Task> getAllByDate(String localDate) {
        Stream<Task> str = taskMap.values().stream();
        return str.filter(s -> s.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")).equals(localDate)).toList();
    }
}

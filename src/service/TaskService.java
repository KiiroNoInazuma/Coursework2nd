package service;

import task.Task;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

public class TaskService {
    public Map<Integer, Task> taskMap;
    public Collection<Task> removedTasks;

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
        return removedTasks.stream().iterator().next();
    }

    public Collection<Task> getAllByDate(LocalDateTime localDate) {
        Stream<Task> str = taskMap.values().stream();
        return str.toList();
    }
}

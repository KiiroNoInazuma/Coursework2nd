package service;

import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
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
        if (taskMap.containsKey(id)) {
            removedTasks.add(taskMap.get(id));
            taskMap.remove(id);
            System.out.println("Задача удалена!");
            return removedTasks.stream().toList().get(id - 1);
        } else {
            System.out.println("❗ Задачи № " + id + " не существует ❗");
            return null;
        }
    }

    public Collection<Task> getAllByDate(LocalDate localDate) {
        Stream<Task> str = taskMap.values().stream().sorted(Comparator.comparing(Task::cutDate));
        Stream<Task>check = taskMap.values().stream();
        if(check.filter(s -> s.appearsIn(localDate)).toList().isEmpty()){
            System.out.println("❗ Активной задачи на "+localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))+" не существует ❗");
        }
        return str.filter(s -> s.appearsIn(localDate)).toList();
    }

    public Collection<Task> getAllByDate() {
        if(taskMap.values().isEmpty()){
            System.out.println("*нет активных задач*");
        }
        Stream<Task> str = taskMap.values().stream();
        return str.collect(Collectors.toList());
    }

    public void getRemovedTasks() {
        if (removedTasks.isEmpty()){
            System.out.println("*нет завершенных задач*");
        }
        for (Task removedTask : removedTasks) {
            System.out.println(removedTask);
        }
    }
}

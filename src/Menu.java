import exceptions.TaskNotFoundException;
import service.TaskService;
import task.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    static private Scanner scanner = new Scanner(System.in);
    static private TaskService taskService = new TaskService();
    static private boolean exit;

    static private void inputField() {
        System.out.print("Поле для ввода --→ ");
    }


    static {

        System.out.println("\u001B[34m" + """
                \t\t\t●▬▬▬▬▬▬▬▬▬▬▬▬▬ஜ۩۞۩ஜ▬▬▬▬▬▬▬▬▬▬▬▬▬●
                \t\t\t\t░░░░░░░░░░░░░░░ ЕЖЕДНЕВНИК ░░░░░░░░░░░░░░
                \t\t\t●▬▬▬▬▬▬▬▬▬▬▬▬▬ஜ۩۞۩ஜ▬▬▬▬▬▬▬▬▬▬▬▬▬●
                """ + "\u001B[0m");
    }


    static private void select(String choose) {
        switch (choose) {
            case "1" -> addTask();
            case "2" -> showTask();
            case "3" -> removeTask();
            case "4" -> showAll();
            case "0" -> exit = true;
            default -> System.out.println("Ошибка ввода, попробуйте еще раз!");
        }
    }

    static private void addTask() {
        String title;
        String dateTime;
        String description;
        Type select = null;


        System.out.println("\t\t\t\t\t\t↓↓↓ Выберите тип задачи ↓↓↓");
        System.out.println("\t\t\t\t\t\t  1. Рабочая\t2. Личная");
        inputField();
        String type = scanner.next();
        try {
            select = (type.equals("1")) ? Type.WORK : (type.equals("2")) ? Type.PERSONAL : Type.valueOf("Error");
        } catch (TaskNotFoundException e) {
            System.out.println("Такого типа не существует! Попробуйте еще раз!");
            addTask();
        }
        System.out.println("\n\t\t\t\t\t\t Период действия задачи: ");
        System.out.println("1: ОДНОКРАТНАЯ\t2: ЕЖЕДНЕВНАЯ\t3: ЕЖЕНЕДЕЛЬНАЯ\t4: ЕЖЕМЕСЯЧНАЯ\t5: ЕЖЕГОДНАЯ");
        inputField();
        String option = scanner.next();

        System.out.println("\n\t\t\t\t\t~Напишите название Вашей задачи~");
        System.out.print("Заголовок --→ ");
        scanner.nextLine();
        title = scanner.nextLine();
        System.out.println("\n\t\t\t\t\uD83D\uDCC5Введите дату задачи (dd.MM.yyyy HH:mm): \uD83D\uDCC5");
        System.out.print("Дата --→ ");
        dateTime = scanner.nextLine();
        System.out.println("\n\t\t\t\t\t\t\t\uD83D\uDCDDОписание задачи\uD83D\uDCDD");
        System.out.println("----------------------------------------------------------------------------------");
        description = scanner.nextLine();
        try {
            switch (option) {
                case "1" -> taskService.add(new OneTimeTask(title, select, dateTime, description));
                case "2" -> taskService.add(new DailyTask(title, select, dateTime, description));
                case "3" -> taskService.add(new WeeklyTask(title, select, dateTime, description));
                case "4" -> taskService.add(new MonthlyTask(title, select, dateTime, description));
                case "5" -> taskService.add(new YearlyTask(title, select, dateTime, description));
                default -> {
                    System.out.println("Ошибка добавления задачи. Период действия заметки выбран некорректно! Задача не добавилась =(");
                    System.out.println("Нажмите ENTER для повторной попытки");
                    scanner.nextLine();
                    addTask();
                }
            }
        } catch (DateTimeException e) {
            System.out.println("Неверный ввод даты! |Вводите дату в формате ~dd.MM.yyyy HH:mm~|\nПример: 01.01.2021 12:12");
            addTask();
        }
    }

    static private void removeTask() {
        System.out.print("Выберите номер задачи для удаления--> ");
        try {
            taskService.remove(scanner.nextInt());
        }catch (InputMismatchException e){
            System.out.println("❗ Ошибка числа или значения. Введите номер задачи ❗");
        }
    }

    static private void showTask() {
        System.out.println("|Вводите дату в формате ~dd.MM.yyyy~|");
        System.out.print("\uD83D\uDCC6 Открыть задачу на --→ ");
        scanner.nextLine();
        try {
            taskService.getAllByDate(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy"))).forEach(System.out::println);
        }catch (DateTimeException e){
            System.out.println("Неверный ввод даты! |Вводите дату в формате ~dd.MM.yyyy ~|\nПример: 01.01.2021");
        }
        System.out.println("Нажмите ENTER для выхода в меню");
        scanner.nextLine();
    }

    static private void showAll() {
        System.out.println("Активные задач: ");
        taskService.getAllByDate().forEach(System.out::println);
        System.out.println("Закрытые задачи: ");
        taskService.getRemovedTasks();
    }

    public static void main(String[] args) {
        while (!exit) {
            System.out.println("\n\uD83D\uDCC5 1. Добавить задачу\t \uD83D\uDCDD 2.Показать задачу\t❌ 3.Удалить задачу \t\uD83D\uDCD64.Вывести весь список задач\t\uD83D\uDEAA0.Выход");
            inputField();
            select(scanner.next());
        }
    }
}

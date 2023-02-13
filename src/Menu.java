import service.TaskService;
import task.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {

    static Scanner scanner = new Scanner(System.in);
    static TaskService taskService = new TaskService();
    static boolean exit;

    static void inputField() {
        System.out.print("Поле для ввода --→ ");
    }


    static {

        System.out.println("\u001B[34m" + """
                \t\t\t●▬▬▬▬▬▬▬▬▬▬▬▬▬ஜ۩۞۩ஜ▬▬▬▬▬▬▬▬▬▬▬▬▬●
                \t\t\t\t░░░░░░░░░░░░░░░ ЕЖЕДНЕВНИК ░░░░░░░░░░░░░░
                \t\t\t●▬▬▬▬▬▬▬▬▬▬▬▬▬ஜ۩۞۩ஜ▬▬▬▬▬▬▬▬▬▬▬▬▬●
                """ + "\u001B[0m");
    }


    static void select(String choose) {
        switch (choose) {
            case "1" -> addTask();
            case "2" -> showTask();
            case "3" -> removeTask();
            case "0" -> exit = true;
            case "4" -> showAll();
            default -> System.out.println("Ошибка ввода, попробуйте еще раз!");
        }
    }

    static void addTask() {
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
        } catch (IllegalArgumentException e) {
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
    }

    static void removeTask() {
        System.out.println("Выберите номер задачи для удаления--> ");
        taskService.remove(scanner.nextInt());
        System.out.println("Задача удалена!");
    }

    static void showTask() {
        System.out.println("|Вводите дату в формате ~dd.MM.yyyy HH:mm~|");
        System.out.print("\uD83D\uDCC6 Открыть задачу на --→ ");
        scanner.nextLine();
        taskService.getAllByDate(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))).forEach(System.out::println);
        System.out.println("Нажмите ENTER для выхода в меню");
        scanner.nextLine();
    }

    static void showAll() {
        taskService.getAllByDate().forEach(System.out::println);
    }

    public static void main(String[] args) {
        while (!exit) {
            System.out.println("\n\uD83D\uDCC5 1. Добавить задачу\t \uD83D\uDCDD 2.Показать задачу\t❌ 3. Удалить задачу\t\uD83D\uDEAA0.Выход");
            inputField();
            select(scanner.next());
        }
    }
}

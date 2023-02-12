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
        System.out.println("\t\t\t●▬▬▬▬▬▬▬▬▬▬▬▬▬ஜ۩۞۩ஜ▬▬▬▬▬▬▬▬▬▬▬▬▬●\n" +
                "\t\t\t\t\u200E\u200E\u200E\u200E\u200E\u200E\u200E\u200E░░░░░░░░░░░░░░░ ЕЖЕДНЕВНИК ░░░░░░░░░░░░░░\n" +
                "\t\t\t\u200E\u200E\u200E\u200E\u200E\u200E\u200E\u200E\u200E\u200E\u200E\u200E●▬▬▬▬▬▬▬▬▬▬▬▬▬ஜ۩۞۩ஜ▬▬▬▬▬▬▬▬▬▬▬▬▬●\n");
    }


    static void select(int choose) {
        if (choose == 1) addTask();
        if (choose == 2) removeTask();
        if (choose == 3) showTask();
        if (choose == 0) exit = true;
    }

    static void addTask() {
        String title;
        String dateTime;
        String description;


        System.out.println("\t\t\t\t\t\t↓↓↓ Выберите тип задачи ↓↓↓");
        System.out.println("\t\t\t\t\t\t  1. Рабочая\t2. Личная");
        inputField();
        int type = scanner.nextInt();
        Type select = (type == 1) ? Type.WORK : Type.PERSONAL;
        System.out.println("\n\t\t\t\t\t\t Период действия задачи: ");
        System.out.println("1: ОДНОКРАТНАЯ\t2: ЕЖЕДНЕВНАЯ\t3: ЕЖЕНЕДЕЛЬНАЯ\t4: ЕЖЕМЕСЯЧНАЯ\t5: ЕЖЕГОДНАЯ");
        inputField();
        int option = scanner.nextInt();
        System.out.println("\n\t\t\t\t\t~Напишите название Вашей задачи~");
        System.out.print("Заголовок --→ ");
        scanner.nextLine();
        title = scanner.nextLine();
        System.out.println("\n\t\t\t\t\uD83D\uDCC5Введите дату задачи (00.00.0000 00:00): \uD83D\uDCC5");
        System.out.print("Дата --→ ");
        dateTime = scanner.nextLine();
        System.out.println("\n\t\t\t\t\t\t\t\uD83D\uDCDDОписание задачи\uD83D\uDCDD");
        System.out.println("----------------------------------------------------------------------------------");
        description = scanner.nextLine();
        switch (option) {
            case 1 -> taskService.add(new OneTimeTask(title, select, dateTime, description));
            case 2 -> taskService.add(new DailyTask(title, select, dateTime, description));
            case 3 -> taskService.add(new WeeklyTask(title, select, dateTime, description));
            case 4 -> taskService.add(new MonthlyTask(title, select, dateTime, description));
            case 5 -> taskService.add(new YearlyTask(title, select, dateTime, description));
            default -> System.out.println("Error");
        }
    }

    static void removeTask() {
        taskService.remove(scanner.nextInt());

    }

    static void showTask() {
        scanner.nextLine();
        taskService.getAllByDate(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))).forEach(System.out::println);
    }

    public static void main(String[] args) {
        while (!exit) {
            System.out.println("\n\t\t1. Добавить задачу\t2. Удалить задачу\t3.Показать задачу\t0.Выход");
            inputField();
            select(scanner.nextInt());
        }
    }
}

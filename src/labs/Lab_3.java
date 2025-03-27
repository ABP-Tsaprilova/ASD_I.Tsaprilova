package labs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Worker {
    String name;
    String position;
    int year;
    int month;

    public Worker(String name, String position, int year, int month) {
        this.name = name;
        this.position = position;
        this.year = year;
        this.month = month;
    }

    public int getExperience(int currentYear, int currentMonth) {
        int experience = currentYear - year;
        if (currentMonth < month) {
            experience--;
        }
        return experience;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", year=" + year +
                ", month=" + month +
                '}';
    }
}

public class Lab_3 {
    public static void lab_3() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть кількість працівників: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Worker> workers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("Введіть дані працівника " + (i + 1) + ":");
            System.out.print("Прізвище та ініціали: ");
            String name = scanner.nextLine();
            System.out.print("Посада: ");
            String position = scanner.nextLine();
            System.out.print("Рік прийняття на роботу: ");
            int year = scanner.nextInt();
            System.out.print("Місяць прийняття на роботу: ");
            int month = scanner.nextInt();
            scanner.nextLine();
            workers.add(new Worker(name, position, year, month));
        }

            Collections.sort(workers, Comparator.comparing(worker -> worker.name));

        System.out.print("Введіть необхідний стаж: ");
        int requiredExperience = scanner.nextInt();

        int currentYear = java.time.Year.now().getValue();
        int currentMonth = java.time.LocalDate.now().getMonthValue();
        boolean found = false;
        System.out.println("Працівники зі стажем більше " + requiredExperience + " років:");
        for (Worker worker : workers) {
            if (worker.getExperience(currentYear, currentMonth) > requiredExperience) {
                System.out.println(worker.name);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Працівників з таким стажем не знайдено.");
        }
    }
}

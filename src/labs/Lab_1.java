package labs;
import java.util.Scanner;

public class Lab_1 {
    public static void lab_1() {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nОберіть завдання:");
                System.out.println("1. Периметр та площа прямокутної трапеції");
                System.out.println("2. Обчислення значення y");
                System.out.println("3. Перевірка на збіг чисел");
                System.out.println("4. Вихід");

                while (!scanner.hasNextInt()) {
                    System.out.println("Помилка: Введіть ціле число.");
                    scanner.next();
                }
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        Trapezoid.calculate(scanner);
                        break;
                    case 2:
                        CalculateY.calculate(scanner);
                        break;
                    case 3:
                        CheckNumbers.check(scanner);
                        break;
                    case 4:
                        System.out.println("Вихід з програми.");
                        return;
                    default:
                        System.out.println("Помилковий вибір. Спробуйте ще раз.");
                }
            }
        }
    }

class Trapezoid {
    public static void calculate(Scanner scanner) {
        System.out.print("Введіть основу a: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Помилка: Введіть число.");
            scanner.next();
        }
        double a = scanner.nextDouble();

        System.out.print("Введіть основу b: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Помилка: Введіть число.");
            scanner.next();
        }
        double b = scanner.nextDouble();

        if (a <= b) {
            System.out.println("Помилка: a повинно бути більше b.");
            return;
        }

        System.out.print("Введіть гострий кут alpha (в радіанах): ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Помилка: Введіть число.");
            scanner.next();
        }
        double alpha = scanner.nextDouble();

        double c = (a - b) / Math.cos(alpha);

        double h = (a - b) * Math.tan(alpha);

        double perimeter = a + b + c + h; // Виправлена формула периметра

        double area = (a + b) * h / 2;

        System.out.println("Периметр трапеції: " + perimeter);
        System.out.println("Площа трапеції: " + area);
    }
}
class CalculateY {
        public static void calculate(Scanner scanner) {
            System.out.print("Введіть значення x: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Помилка: Введіть число.");
                scanner.next();
            }
            double x = scanner.nextDouble();

            if (Math.cos(x) == 0 && 5 + Math.tan(x) < 0) {
                System.out.println("Помилка: Вираз не визначено для даного x.");
                return;
            }
            double y = (Math.sin(x) + Math.cos(x)) / Math.sqrt(5 + Math.tan(x));

            System.out.println("Значення y: " + y);
        }
    }

    class CheckNumbers {
        public static void check(Scanner scanner) {
            System.out.print("Введіть перше число: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Помилка: Введіть ціле число.");
                scanner.next();
            }
            int num1 = scanner.nextInt();
            System.out.print("Введіть друге число: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Помилка: Введіть ціле число.");
                scanner.next();
            }
            int num2 = scanner.nextInt();

            System.out.print("Введіть третє число: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Помилка: Введіть ціле число.");
                scanner.next();
            }
            int num3 = scanner.nextInt();
            if (num1 == num2 || num1 == num3 || num2 == num3) {
                System.out.println("Серед даних чисел є хоча б одна пара збігаючихся.");
            } else {
                System.out.println("Серед даних чисел немає збігаючихся пар.");
            }
        }
    }


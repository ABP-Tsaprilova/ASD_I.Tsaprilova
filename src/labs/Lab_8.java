package labs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Lab_8 {
        public static void lab_8() {
            Scanner scanner = new Scanner(System.in);
            int[] arrayA = getArrayFromUser(scanner, "A");
            int[] arrayB = getArrayFromUser(scanner, "B");

            List<Integer> result = findUniqueAndMultiple(arrayA, arrayB);
            System.out.println("Результат: " + result);
        }

        public static List<Integer> findUniqueAndMultiple(int[] arrayA, int[] arrayB) {
            List<Integer> result = new ArrayList<>();
            List<Integer> listA = toList(arrayA);
            List<Integer> listB = toList(arrayB);


            List<Integer> multiplesA = findMultiples(arrayA);
            result.addAll(multiplesA);


            List<Integer> multiplesB = findMultiples(arrayB);
            result.addAll(multiplesB);


            for (int num : arrayA) {
                if (!listB.contains(num) && !multiplesA.contains(num) && !multiplesB.contains(num)) {
                    result.add(num);
                }
            }


            for (int num : arrayB) {
                if (!listA.contains(num) && !multiplesA.contains(num) && !multiplesB.contains(num)) {
                    result.add(num);
                }
            }

            return result;
        }

        private static List<Integer> findMultiples(int[] array) {
            List<Integer> multiples = new ArrayList<>();
            List<Integer> list = new ArrayList<>();

            for (int num : array) {
                if (list.contains(num) && !multiples.contains(num)) {
                    multiples.add(num);
                } else {
                    list.add(num);
                }
            }
            return multiples;
        }

        private static List<Integer> toList(int[] array) {
            List<Integer> list = new ArrayList<>();
            for (int num : array) {
                list.add(num);
            }
            return list;
        }

        private static int[] getArrayFromUser(Scanner scanner, String arrayName) {
            System.out.println("Виберіть спосіб введення масиву " + arrayName + ":");
            System.out.println("1. Ввести вручну");
            System.out.println("2. Згенерувати випадково");

            int choice = scanner.nextInt();
            if (choice == 1) {
                return getManualArray(scanner, arrayName);
            } else if (choice == 2) {
                int[] randomArray = getRandomArray(scanner, arrayName);
                System.out.println("Згенерований масив " + arrayName + ": " + Arrays.toString(randomArray));
                return randomArray;
            } else {
                System.out.println("Невірний вибір. Використано випадкове генерування.");
                int[] randomArray = getRandomArray(scanner, arrayName);
                System.out.println("Згенерований масив " + arrayName + ": " + Arrays.toString(randomArray));
                return randomArray;
            }
        }

        private static int[] getManualArray(Scanner scanner, String arrayName) {
            System.out.print("Введіть розмір масиву " + arrayName + ": ");
            int size = scanner.nextInt();
            int[] array = new int[size];

            System.out.println("Введіть елементи масиву " + arrayName + " через пробіл:");
            for (int i = 0; i < size; i++) {
                array[i] = scanner.nextInt();
            }
            return array;
        }

        private static int[] getRandomArray(Scanner scanner, String arrayName) {
            System.out.print("Введіть розмір масиву " + arrayName + ": ");
            int size = scanner.nextInt();
            int[] array = new int[size];
            Random random = new Random();

            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(100);
            }
            return array;
        }
    }

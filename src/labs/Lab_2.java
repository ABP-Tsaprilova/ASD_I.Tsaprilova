package labs;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lab_2 {
    public static void lab_2 () {
        Scanner scanner = new Scanner(System.in);
                Random random = new Random();

                int choice;
                do {
                    System.out.println("\nОберіть завдання:");
                    System.out.println("1. Одновимірний масив");
                    System.out.println("2. Заміна елементів в масиві");
                    System.out.println("3. Прямокутна матриця");
                    System.out.println("4. Квадратна матриця");
                    System.out.println("0. Вихід");
                    System.out.print("Ваш вибір: ");
                    choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            task1(scanner, random);
                            break;
                        case 2:
                            task2(scanner, random);
                            break;
                        case 3:
                            task3(scanner, random);
                            break;
                        case 4:
                            task4(scanner, random);
                            break;
                        case 0:
                            System.out.println("Програма завершена.");
                            break;
                        default:
                            System.out.println("Невірний вибір. Спробуйте ще раз.");
                    }
                } while (choice != 0);

                scanner.close();
            }


            public static void task1(Scanner scanner, Random random) {
                System.out.print("Введіть розмірність одновимірного масиву N: ");
                int n = scanner.nextInt();
                int[] array = new int[n];
                for (int i = 0; i < n; i++) {
                    array[i] = random.nextInt(20) - 10;
                }
                System.out.println("Отриманий масив: " + Arrays.toString(array));
                int product = 1;
                for (int i = 1; i < array.length; i += 2) {
                    product *= array[i];
                }
                System.out.println("Добуток елементів з непарними індексами: " + product);

                int maxIndex = 0;
                int minIndex = 0;
                for (int i = 1; i < array.length; i++) {
                    if (Math.abs(array[i]) > Math.abs(array[maxIndex])) {
                        maxIndex = i;
                    }
                    if (Math.abs(array[i]) < Math.abs(array[minIndex])) {
                        minIndex = i;
                    }
                }

                int count = 0;
                int start = Math.min(maxIndex, minIndex);
                int end = Math.max(maxIndex, minIndex);
                for (int i = start + 1; i < end; i++) {
                    count++;
                }
                System.out.println("Кількість елементів між max і min: " + count);
            }


            public static void task2(Scanner scanner, Random random) {
                System.out.print("Введіть розмірність одновимірного масиву N: ");
                int n = scanner.nextInt();
                int[] array = new int[n];
                for (int i = 0; i < n; i++) {
                    array[i] = random.nextInt(20) - 10;
                }
                System.out.println("Отриманий масив: " + Arrays.toString(array));
                System.out.print("Введіть початковий індекс для заміни: ");
                int start = scanner.nextInt();
                System.out.print("Введіть кінцевий індекс для заміни: ");
                int end = scanner.nextInt();
                for (int i = start; i <= end && i < array.length; i++) {
                    array[i] = 1;
                }
                System.out.println("Масив після заміни: " + Arrays.toString(array));
            }


            public static void task3(Scanner scanner, Random random) {
                System.out.print("Введіть кількість рядків матриці: ");
                int rows = scanner.nextInt();
                System.out.print("Введіть кількість стовпців матриці: ");
                int cols = scanner.nextInt();
                int[][] matrix = new int[rows][cols];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        matrix[i][j] = random.nextInt(20) - 10;
                    }
                }
                System.out.println("Отримана матриця:");
                printMatrix(matrix);
                for (int i = 0; i < matrix.length; i += 2) {
                    int sum = 0;
                    for (int j = 0; j < matrix[i].length; j++) {
                        sum += matrix[i][j];
                    }
                    System.out.println("Сума елементів в рядку " + i + ": " + sum);
                }

                int minProduct = Integer.MAX_VALUE;
                int minRowIndex = -1;
                for (int i = 0; i < matrix.length; i++) {
                    int product = 1;
                    for (int j = 0; j < matrix[i].length; j++) {
                        product *= matrix[i][j];
                    }
                    if (product < minProduct) {
                        minProduct = product;
                        minRowIndex = i;
                    }
                }
                System.out.println("Номер рядка з найменшим добутком: " + minRowIndex);
            }

            public static void task4(Scanner scanner, Random random) {
                System.out.print("Введіть розмірність квадратної матриці N: ");
                int size = scanner.nextInt();
                int[][] squareMatrix = new int[size][size];
                int n = squareMatrix.length;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (j > i && j > n - 1 - i) {
                            squareMatrix[i][j] = 1;
                        } else {
                            squareMatrix[i][j] = 0;
                        }
                    }
                }
                System.out.println("Квадратна матриця після заповнення:");
                printMatrix(squareMatrix);
            }


            public static void printMatrix(int[][] matrix) {
                for (int[] row : matrix) {
                    System.out.println(Arrays.toString(row));
                }
            }
        }
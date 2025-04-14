package labs;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

public class Lab_8 {

    private static final int SIZE = 10;
    private static final Random RANDOM = new Random();
    private static final Scanner SCAN = new Scanner(System.in);

    public static void lab_8() {
        int[] array = new int[SIZE];

        for (int i = 0; i < SIZE; i++) {
            array[i] = RANDOM.nextInt(51);
            System.out.print(array[i] + "  ");
        }
        System.out.println();

        System.out.println("Лінійний пошук: ");
        int target1 = inputInt("Введіть число для пошуку: ");
        linear(array, target1);

        System.out.println("Лінійний пошук з бар'єром: ");
        int target2 = inputInt("Введіть число для пошуку: ");
        linearBarrier(array, target2);

        System.out.println("Бінарний пошук: ");
        int target3 = inputInt("Введіть число для пошуку: ");
        binary(Arrays.copyOf(array, array.length), target3);

        System.out.println("Пошук Фібоначчі: ");
        int target4 = inputInt("Введіть число для пошуку: ");
        fibonacci(Arrays.copyOf(array, array.length), target4);

        System.out.println("Пошук з перестановкою в початок: ");
        int target5 = inputInt("Введіть число для пошуку: ");
        permutation(array, target5);

        System.out.println("Пошук з транспозицією: ");
        int target6 = inputInt("Введіть число для пошуку: ");
        transposition(array, target6);

        System.out.println("\n\nТепер сортування\n\n\n");

        System.out.println("Сортування лінійної вставки: ");
        array = recreate();
        linearInsert(array);
        System.out.println(Arrays.toString(array));

        System.out.println("Сортування простого вибору: ");
        array = recreate();
        simpleChoice(array);
        System.out.println(Arrays.toString(array));

        System.out.println("Сортування бульбашкою: ");
        array = recreate();
        bubbleSort(array);
        System.out.println(Arrays.toString(array));

        System.out.println("Сортування Шелла: ");
        array = recreate();
        shell(array);
        System.out.println(Arrays.toString(array));

        System.out.println("Сортування злиттям: ");
        array = recreate();
        mergeSort(array);
        System.out.println(Arrays.toString(array));

        System.out.println("Сортування пірамідальне: ");
        array = recreate();
        pyramid(array);
        System.out.println(Arrays.toString(array));

        System.out.println("Сортування Хоара: ");
        array = recreate();
        quickSort(array, 0, SIZE - 1);
        System.out.println(Arrays.toString(array));
        System.out.println("\n\nЗнаходження елементів A, які повторюються в B:");

        int[] arrA = recreate();
        int[] arrB = recreate();

        System.out.println("Масив A: " + Arrays.toString(arrA));
        System.out.println("Масив B: " + Arrays.toString(arrB));

        findAinBRepeated(arrA, arrB);
    }

    private static int inputInt(String message) {
        System.out.print(message);
        return SCAN.nextInt();
    }

    // Пошук
    private static int linear(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                System.out.println("Found: " + num + " at index " + i);
                return i;
            }
        }
        System.out.println("There is no " + num + " in array");
        return -1;
    }

    private static int linearBarrier(int[] arr, int num) {
        int[] extendedArray = Arrays.copyOf(arr, arr.length + 1);
        extendedArray[extendedArray.length - 1] = num;
        System.out.println("Масив з бар'єром: " + Arrays.toString(extendedArray));
        int i = 0;
        while (extendedArray[i] != num) i++;
        if (i == extendedArray.length - 1) {
            System.out.println("There is no " + num + " in array");
            return -1;
        }
        System.out.println("Found: " + num + " at index " + i);
        return i;
    }

    private static int binary(int[] arr, int num) {
        Arrays.sort(arr);
        System.out.println("Відсортований масив: " + Arrays.toString(arr));
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < num) left = mid + 1;
            else if (arr[mid] > num) right = mid - 1;
            else {
                System.out.println("Found: " + arr[mid] + " at index " + mid);
                return mid;
            }
        }
        System.out.println("There is no " + num + " in array");
        return -1;
    }

    private static int fibonacci(int[] arr, int num) {
        Arrays.sort(arr);
        System.out.println("Відсортований масив: " + Arrays.toString(arr));
        int fibMMm2 = 0;
        int fibMMm1 = 1;
        int fibM = fibMMm2 + fibMMm1;
        int n = arr.length;

        while (fibM < n) {
            fibMMm2 = fibMMm1;
            fibMMm1 = fibM;
            fibM = fibMMm2 + fibMMm1;
        }

        int offset = -1;

        while (fibM > 1) {
            int i = Math.min(offset + fibMMm2, n - 1);
            if (arr[i] < num) {
                fibM = fibMMm1;
                fibMMm1 = fibMMm2;
                fibMMm2 = fibM - fibMMm1;
                offset = i;
            } else if (arr[i] > num) {
                fibM = fibMMm2;
                fibMMm1 = fibMMm1 - fibMMm2;
                fibMMm2 = fibM - fibMMm1;
            } else {
                System.out.println("Found: " + arr[i] + " at index " + i);
                return i;
            }
        }

        if (fibMMm1 == 1 && offset + 1 < n && arr[offset + 1] == num) {
            System.out.println("Found: " + arr[offset + 1] + " at index " + (offset + 1));
            return offset + 1;
        }
        System.out.println("There is no " + num + " in array");
        return -1;
    }

    private static int permutation(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                int temp = arr[i];
                for (int j = i; j > 0; j--) arr[j] = arr[j - 1];
                arr[0] = temp;
                System.out.println("Масив після перестановки: " + Arrays.toString(arr));
                System.out.println("Found: " + num + " at index 0");
                return 0;
            }
        }
        System.out.println("There is no " + num + " in array");
        return -1;
    }

    private static int transposition(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                if (i > 0) {
                    int temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                    System.out.println("Масив після транспозиції: " + Arrays.toString(arr));
                    System.out.println("Found: " + num + " at index " + (i - 1));
                    return i - 1;
                }
                System.out.println("Found: " + num + " at index " + i);
                return i;
            }
        }
        System.out.println("There is no " + num + " in array");
        return -1;
    }

    // Сортування
    private static void linearInsert(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i], j = i - 1;
            while (j >= 0 && arr[j] > key) arr[j + 1] = arr[j--];
            arr[j + 1] = key;
        }
    }

    private static void simpleChoice(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++)
                if (arr[j] < arr[min]) min = j;
            int temp = arr[i]; arr[i] = arr[min]; arr[min] = temp;
        }
    }

    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++)
            for (int j = 0; j < arr.length - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j]; arr[j] = arr[j + 1]; arr[j + 1] = temp;
                }
    }
    private static void shell(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2)
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i], j = i;
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
    }

    private static void mergeSort(int[] arr) {
        if (arr.length <= 1) return;
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    private static void merge(int[] result, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length)
            result[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];
    }

    private static void pyramid(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) heapify(arr, arr.length, i);
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[0]; arr[0] = arr[i]; arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i, left = 2 * i + 1, right = 2 * i + 2;
        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;
        if (largest != i) {
            int swap = arr[i]; arr[i] = arr[largest]; arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++)
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        int temp = arr[i + 1]; arr[i + 1] = arr[high]; arr[high] = temp;
        return i + 1;
    }

    private static int[] recreate() {
        int[] arr = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = RANDOM.nextInt(51);
            System.out.print(arr[i] + "  ");
        }
        System.out.println();
        return arr;
    }

    public static void findAinBRepeated(int[] arrA, int[] arrB) {
        Map<Integer, Integer> bCounts = new HashMap<>();
        for (int num : arrB) {
            bCounts.put(num, bCounts.getOrDefault(num, 0) + 1);
        }

        System.out.println("Елементи масиву A, які повторюються в масиві B:");
        boolean found = false;
        for (int numA : arrA) {
            if (bCounts.containsKey(numA) && bCounts.get(numA) > 1) {
                System.out.println("Елемент: " + numA + ", у B трапляється " + bCounts.get(numA) + " разів");
                found = true;
            }
        }

        if (!found) {
            System.out.println("У масиві B немає елементів, які повторюються та присутні в A.");
        }
    }
}


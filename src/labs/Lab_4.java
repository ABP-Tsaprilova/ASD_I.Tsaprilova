package labs;
import java.util.LinkedList;
import java.util.Scanner;

class Product {
    String name;
    String manufacturer;

    public Product(String name, String manufacturer) {
        this.name = name;
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}

public class Lab_4 {
    static LinkedList<Product> productList = new LinkedList<>();

    public static void lab_4() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nМеню:");
            System.out.println("1. Додати елемент на початок");
            System.out.println("2. Видалити елемент за назвою");
            System.out.println("3. Додати елемент в кінець списку");
            System.out.println("4. Пошук за виробником");
            System.out.println("5. Друк списку");
            System.out.println("0. Вихід");
            System.out.print("Виберіть дію: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Очищення буфера

            switch (choice) {
                case 1:
                    addElement(scanner);
                    break;
                case 2:
                    removeElement(scanner);
                    break;
                case 3:
                    addLast(scanner);
                    break;
                case 4:
                    searchByManufacturer(scanner);
                    break;
                case 5:
                    printList();
                    break;
                case 0:
                    System.out.println("Вихід з програми.");
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        } while (choice != 0);
    }

    public static void addElement(Scanner scanner) {
        System.out.print("Введіть назву товару: ");
        String name = scanner.nextLine();
        System.out.print("Введіть виробника: ");
        String manufacturer = scanner.nextLine();
        Product product = new Product(name, manufacturer);
        productList.addFirst(product); // Додавання на початок
    }

    public static void removeElement(Scanner scanner) {
        System.out.print("Введіть назву товару для видалення: ");
        String name = scanner.nextLine();
        for (Product product : productList) {
            if (product.name.equals(name)) {
                productList.remove(product);
                System.out.println("Товар '" + name + "' видалено.");
                return;
            }
        }
        System.out.println("Товар '" + name + "' не знайдено.");
    }

    public static void addLast(Scanner scanner) {
        System.out.print("Введіть назву товару: ");
        String name = scanner.nextLine();
        System.out.print("Введіть виробника: ");
        String manufacturer = scanner.nextLine();
        Product product = new Product(name, manufacturer);
        productList.addLast(product); // Додавання в кінець
    }

    public static void searchByManufacturer(Scanner scanner) {
        System.out.print("Введіть виробника для пошуку: ");
        String manufacturer = scanner.nextLine();
        boolean found = false;
        for (Product product : productList) {
            if (product.manufacturer.equals(manufacturer)) {
                System.out.println(product);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Товари виробника '" + manufacturer + "' не знайдено.");
        }
    }

    public static void printList() {
        if (productList.isEmpty()) {
            System.out.println("Список порожній.");
            return;
        }
        System.out.println("Список товарів:");
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    public static void main(String[] args) {
        lab_4();
    }
}
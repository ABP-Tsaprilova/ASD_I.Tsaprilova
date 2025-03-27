package labs;
import java.util.Scanner;

class Node {
    Product data;
    Node next;

    public Node(Product data) {
        this.data = data;
        this.next = null;
    }
}

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
    static Node head = null;

    public static void lab_4() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nМеню:");
            System.out.println("1. Додати елемент");
            System.out.println("2. Видалити елемент");
            System.out.println("3. Додати в кінець списку");
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
        Node newNode = new Node(product);
        newNode.next = head;
        head = newNode;
    }

    public static void removeElement(Scanner scanner) {
        System.out.print("Введіть назву товару для видалення: ");
        String name = scanner.nextLine();
        Node current = head;
        Node previous = null;
        while (current != null) {
            if (current.data.name.equals(name)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                System.out.println("Товар '" + name + "' видалено.");
                return;
            }
            previous = current;
            current = current.next;
        }
        System.out.println("Товар '" + name + "' не знайдено.");
    }

    public static void addLast(Scanner scanner) {
        System.out.print("Введіть назву товару: ");
        String name = scanner.nextLine();
        System.out.print("Введіть виробника: ");
        String manufacturer = scanner.nextLine();
        Product product = new Product(name, manufacturer);
        Node newNode = new Node(product);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public static void searchByManufacturer(Scanner scanner) {
        System.out.print("Введіть виробника для пошуку: ");
        String manufacturer = scanner.nextLine();
        Node current = head;
        boolean found = false;
        while (current != null) {
            if (current.data.manufacturer.equals(manufacturer)) {
                System.out.println(current.data);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("Товари виробника '" + manufacturer + "' не знайдено.");
        }
    }

    public static void printList() {
        Node current = head;
        if (current == null) {
            System.out.println("Список порожній.");
            return;
        }
        System.out.println("Список товарів:");
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
}
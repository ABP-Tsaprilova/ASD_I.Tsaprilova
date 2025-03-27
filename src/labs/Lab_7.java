package labs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class Lab_7 {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        private Node root;

        public BinaryTree() {
            this.root = null;
        }

        public void insert(int data) {
            root = insertRecursive(root, data);
        }

        private Node insertRecursive(Node current, int data) {
            if (current == null) {
                return new Node(data);
            }

            if (data < current.data) {
                current.left = insertRecursive(current.left, data);
            } else if (data > current.data) {
                current.right = insertRecursive(current.right, data);
            }

            return current;
        }

        public boolean contains(int data) {
            return containsRecursive(root, data);
        }

        private boolean containsRecursive(Node current, int data) {
            if (current == null) {
                return false;
            }
            if (data == current.data) {
                return true;
            }
            return data < current.data
                    ? containsRecursive(current.left, data)
                    : containsRecursive(current.right, data);
        }

        public void delete(int data) {
            root = deleteRecursive(root, data);
        }

        private Node deleteRecursive(Node current, int data) {
            if (current == null) {
                return null;
            }

            if (data == current.data) {
                if (current.left == null && current.right == null) {
                    return null;
                } else if (current.left == null) {
                    return current.right;
                } else if (current.right == null) {
                    return current.left;
                }

                Node successor = findSmallestValue(current.right);
                current.data = successor.data;
                current.right = deleteRecursive(current.right, successor.data);
                return current;
            }
            if (data < current.data) {
                current.left = deleteRecursive(current.left, data);
            } else {
                current.right = deleteRecursive(current.right, data);
            }
            return current;
        }

        private Node findSmallestValue(Node root) {
            return root.left == null ? root : findSmallestValue(root.left);
        }

        public void traverseInOrder(Node node) {
            if (node != null) {
                traverseInOrder(node.left);
                System.out.print(" " + node.data);
                traverseInOrder(node.right);
            }
        }

        public int findShortestPath(int target) {
            return findShortestPathRecursive(root, target, 0);
        }

        private int findShortestPathRecursive(Node current, int target, int depth) {
            if (current == null) {
                return -1;
            }

            if (current.data == target) {
                return depth;
            }

            int leftDepth = findShortestPathRecursive(current.left, target, depth + 1);
            int rightDepth = findShortestPathRecursive(current.right, target, depth + 1);

            if (leftDepth == -1 && rightDepth == -1) {
                return -1;
            } else if (leftDepth == -1) {
                return rightDepth;
            } else if (rightDepth == -1) {
                return leftDepth;
            } else {
                return Math.min(leftDepth, rightDepth);
            }
        }

        public int findMaxDepth() {
            return findMaxDepthRecursive(root);
        }

        private int findMaxDepthRecursive(Node current) {
            if (current == null) {
                return 0;
            }
            int leftDepth = findMaxDepthRecursive(current.left);
            int rightDepth = findMaxDepthRecursive(current.right);
            return Math.max(leftDepth, rightDepth) + 1;
        }

        public Node getRoot() {
            return root;
        }
    }

    public static List<Integer> sortBuses(List<Integer> busNumbers) {
        List<Integer> oddBuses = new ArrayList<>();
        for (int number : busNumbers) {
            if (number % 2 != 0) {
                oddBuses.add(number);
            }
        }
        Collections.sort(oddBuses, Collections.reverseOrder());
        return oddBuses;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Виберіть завдання:");
            System.out.println("1. Робота з бінарним деревом");
            System.out.println("2. Сортування автобусів");
            System.out.println("3. Вихід");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    runBinaryTreeTask(scanner);
                    break;
                case 2:
                    runBusSortingTask();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static void runBinaryTreeTask(Scanner scanner) {
        BinaryTree tree = new BinaryTree();
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Виберіть операцію з бінарним деревом:");
        System.out.println("1. Обхід дерева");
        System.out.println("2. Пошук елемента");
        System.out.println("3. Додавання елемента");
        System.out.println("4. Видалення елемента");
        System.out.println("5. Знайти найкоротший шлях");
        System.out.println("6. Знайти максимальну глибину");

        int operation = scanner.nextInt();

        switch (operation) {
            case 1:
                tree.traverseInOrder(tree.getRoot());
                System.out.println();
                break;
            case 2:
                System.out.print("Введіть елемент для пошуку: ");
                int searchElement = scanner.nextInt();
                System.out.println("Елемент " + searchElement + " " + (tree.contains(searchElement) ? "знайдено" : "не знайдено"));
                break;
            case 3:
                System.out.print("Введіть елемент для додавання: ");
                int addElement = scanner.nextInt();
                tree.insert(addElement);
                System.out.println("Елемент " + addElement + " додано");
                break;
            case 4:
                System.out.print("Введіть елемент для видалення: ");
                int deleteElement = scanner.nextInt();
                tree.delete(deleteElement);
                System.out.println("Елемент " + deleteElement + " видалено");
                break;
            case 5:
                System.out.print("Введіть елемент для пошуку найкоротшого шляху: ");
                int targetElement = scanner.nextInt();
                int shortestPath = tree.findShortestPath(targetElement);
                System.out.println("Найкоротший шлях до елемента " + targetElement + ": " + shortestPath);
                break;
            case 6:
                System.out.println("Максимальна глибина дерева: " + tree.findMaxDepth());
                break;
            default:
                System.out.println("Невірна операція.");
        }
    }

    private static void runBusSortingTask() {
        List<Integer> busNumbers = List.of(11, 32, 23, 12, 6, 52, 47, 63, 69, 50, 43, 28, 35, 33, 42, 56, 55, 101);
    }
}

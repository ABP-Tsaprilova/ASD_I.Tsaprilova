package labs;
import java.util.*;

public class Lab_9 {
    public static void lab_9() {
        // Представляємо граф як матрицю суміжності
        int[][] graph = {
                {0, 1, 1, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}, // a
                {1, 0, Integer.MAX_VALUE, 1, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}, // b
                {1, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1}, // c
                {Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE}, // d
                {1, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 1, Integer.MAX_VALUE, Integer.MAX_VALUE}, // e
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, 0, 1, 1}, // f
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 1, 0, Integer.MAX_VALUE}, // g
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 0}  // h
        };

        char[] vertices = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nВиберіть алгоритм:");
            System.out.println("1. Алгоритм Дейкстри");
            System.out.println("2. Алгоритм Флойда-Воршалла");
            System.out.println("3. Вийти");

            int choice = scanner.nextInt();

            if (choice == 3) {
                break;
            }

            System.out.print("Введіть початкову вершину (a-h): ");
            char startChar = scanner.next().charAt(0);
            System.out.print("Введіть кінцеву вершину (a-h): ");
            char endChar = scanner.next().charAt(0);

            int startVertex = findVertexIndex(vertices, startChar);
            int endVertex = findVertexIndex(vertices, endChar);

            if (startVertex == -1 || endVertex == -1) {
                System.out.println("Неправильний ввід вершин.");
                continue;
            }

            if (choice == 1) {
                dijkstra(graph, startVertex, endVertex, vertices);
            } else if (choice == 2) {
                floydWarshall(graph, startVertex, endVertex, vertices);
            }
        }

        scanner.close();
    }

    public static void dijkstra(int[][] graph, int startVertex, int endVertex, char[] vertices) {
        int vertexCount = graph.length;
        int[] distances = new int[vertexCount];
        boolean[] visited = new boolean[vertexCount];
        int[] parent = new int[vertexCount];

        // Ініціалізація відстаней та visited
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(visited, false);
        distances[startVertex] = 0;
        Arrays.fill(parent, -1);

        for (int i = 0; i < vertexCount - 1; i++) {
            int u = findMinDistanceVertex(distances, visited);
            if (u == -1) break; // Якщо всі досяжні вершини вже відвідані

            visited[u] = true;

            for (int v = 0; v < vertexCount; v++) {
                if (!visited[v] && graph[u][v] != Integer.MAX_VALUE &&
                        distances[u] != Integer.MAX_VALUE &&
                        distances[u] + graph[u][v] < distances[v]) {
                    distances[v] = distances[u] + graph[u][v];
                    parent[v] = u;
                }
            }
        }

        System.out.println("Найкоротший шлях від " + vertices[startVertex] + " до " + vertices[endVertex] + ":");
        if (distances[endVertex] == Integer.MAX_VALUE) {
            System.out.println("Шлях не знайдено.");
        } else {
            System.out.println("Відстань: " + distances[endVertex]);
            System.out.print("Шлях: ");
            printShortestPath(parent, endVertex, vertices);
            System.out.println();
        }
    }

    private static int findMinDistanceVertex(int[] distances, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE;
        int minVertex = -1;
        for (int v = 0; v < distances.length; v++) {
            if (!visited[v] && distances[v] < minDistance) {
                minDistance = distances[v];
                minVertex = v;
            }
        }
        return minVertex;
    }

    private static void printShortestPath(int[] parent, int j, char[] vertices) {
        if (parent[j] == -1) {
            System.out.print(vertices[j] + " ");
            return;
        }
        printShortestPath(parent, parent[j], vertices);
        System.out.print(vertices[j] + " ");
    }

    public static void floydWarshall(int[][] graph, int startVertex, int endVertex, char[] vertices) {
        int vertexCount = graph.length;
        int[][] distances = new int[vertexCount][vertexCount];

        // Ініціалізація матриці відстаней
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                distances[i][j] = graph[i][j];
            }
        }

        // Алгоритм Флойда-Воршалла
        for (int k = 0; k < vertexCount; k++) {
            for (int i = 0; i < vertexCount; i++) {
                for (int j = 0; j < vertexCount; j++) {
                    if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE
                            && distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }

        System.out.println("Найкоротший шлях від " + vertices[startVertex] + " до " + vertices[endVertex] + " (алгоритм Флойда-Воршалла):");
        if (distances[startVertex][endVertex] == Integer.MAX_VALUE) {
            System.out.println("Шлях не знайдено.");
        } else {
            System.out.println("Відстань: " + distances[startVertex][endVertex]);
        }
    }

    public static int findVertexIndex(char[] vertices, char vertexChar) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] == vertexChar) {
                return i;
            }
        }
        return -1;
    }
}
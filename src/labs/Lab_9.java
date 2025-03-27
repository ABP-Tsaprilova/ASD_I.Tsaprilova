package labs;
import java.util.Arrays;
import java.util.Scanner;
public class Lab_9 {
    public static void lab_9() {
                int[][] graph = {
                        {0, 1, 1, 0, 1, 0, 0, 1}, // a
                        {1, 0, 0, 1, 1, 0, 0, 0}, // b
                        {1, 0, 0, 0, 1, 1, 0, 1}, // c
                        {0, 1, 0, 0, 1, 0, 1, 0}, // d
                        {1, 1, 1, 1, 0, 1, 1, 0}, // e
                        {0, 0, 1, 0, 1, 0, 1, 1}, // f
                        {0, 0, 0, 1, 1, 1, 0, 1}, // g
                        {1, 0, 1, 0, 0, 1, 1, 0}  // h
                };

                Scanner scanner = new Scanner(System.in);
                System.out.print("Введіть початкову вершину (a-h): ");
                char startChar = scanner.next().charAt(0);
                int start = startChar - 'a';

                System.out.print("Введіть кінцеву вершину (a-h): ");
                char endChar = scanner.next().charAt(0);
                int end = endChar - 'a';

                System.out.println("\nАлгоритм Дейкстри:");
                dijkstra(graph, start, end);

                System.out.println("\nАлгоритм Флойда-Варшалла:");
                floydWarshall(graph, start, end);
            }

            // Алгоритм Дейкстри
            public static void dijkstra(int[][] graph, int start, int end) {
                int n = graph.length;
                int[] dist = new int[n];
                boolean[] visited = new boolean[n];
                int[] parent = new int[n];

                Arrays.fill(dist, Integer.MAX_VALUE);
                dist[start] = 0;
                parent[start] = -1;

                System.out.println("Початкові відстані: " + Arrays.toString(dist));
                System.out.println("Початкові батьківські вершини: " + Arrays.toString(parent));

                for (int i = 0; i < n - 1; i++) {
                    int u = minDistance(dist, visited);
                    visited[u] = true;

                    System.out.println("\nКрок " + (i + 1) + ": Вибрана вершина " + (char) ('a' + u));

                    for (int v = 0; v < n; v++) {
                        if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                            dist[v] = dist[u] + graph[u][v];
                            parent[v] = u;
                        }
                    }

                    System.out.println("Поточні відстані: " + Arrays.toString(dist));
                    System.out.println("Поточні батьківські вершини: " + Arrays.toString(parent));
                }

                System.out.print("Найкоротший шлях від " + (char) ('a' + start) + " до " + (char) ('a' + end) + ": ");
                printPath(parent, end);
                System.out.println(" (Довжина: " + dist[end] + ")");
            }

            private static int minDistance(int[] dist, boolean[] visited) {
                int min = Integer.MAX_VALUE;
                int minIndex = -1;

                for (int v = 0; v < dist.length; v++) {
                    if (!visited[v] && dist[v] <= min) {
                        min = dist[v];
                        minIndex = v;
                    }
                }
                return minIndex;
            }

            private static void printPath(int[] parent, int currentVertex) {
                if (currentVertex == -1) {
                    return;
                }
                printPath(parent, parent[currentVertex]);
                System.out.print((char) ('a' + currentVertex) + " ");
            }

            // Алгоритм Флойда-Варшалла
            public static void floydWarshall(int[][] graph, int start, int end) {
                int n = graph.length;
                int[][] dist = new int[n][n];

                // Ініціалізація матриці відстаней
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        dist[i][j] = (graph[i][j] == 0 && i != j) ? Integer.MAX_VALUE : graph[i][j];
                    }
                }

                System.out.println("Матриця суміжності:");
                printMatrix(graph);

                // Ітерації
                for (int k = 0; k < n; k++) {
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE && dist[i][k] + dist[k][j] < dist[i][j]) {
                                dist[i][j] = dist[i][k] + dist[k][j];
                            }
                        }
                    }
                }

                System.out.println("\nМатриця найкоротших шляхів:");
                printMatrix(dist);

                System.out.println("\nНайкоротший шлях від " + (char) ('a' + start) + " до " + (char) ('a' + end) + ": " + dist[start][end]);
            }

            private static void printMatrix(int[][] matrix) {
                for (int[] row : matrix) {
                    for (int value : row) {
                        if (value == Integer.MAX_VALUE) {
                            System.out.print("INF ");
                        } else {
                            System.out.print(value + "   ");
                        }
                    }
                    System.out.println();
                }
            }
        }
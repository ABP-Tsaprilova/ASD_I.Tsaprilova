package labs;
import java.util.*;

public class Lab_10 {
    public static void lab_10() {

        class ShortestPathFinder {

            private static final int INF = Integer.MAX_VALUE;

            public static void lab10_() {
                // Представляємо граф як матрицю суміжності (ваги ребер)
                int[][] graph = {
                        {0, 8, INF, INF, 7, INF, INF, INF},   // 0
                        {INF, 0, 3, INF, 5, INF, INF, INF},   // 1
                        {INF, INF, 0, 9, 2, INF, INF, INF},   // 2
                        {INF, INF, INF, 0, INF, 11, INF, INF},  // 3
                        {INF, INF, INF, INF, 0, 6, INF, INF},   // 4
                        {INF, INF, INF, INF, INF, 0, 13, 3},  // 5
                        {INF, INF, INF, INF, INF, INF, 0, 10},  // 6
                        {INF, INF, INF, INF, INF, INF, INF, 0}    // 7
                };

                Scanner scanner = new Scanner(System.in);

                System.out.println("Виберіть початкову вершину (0-7):");
                int startNode = scanner.nextInt();

                System.out.println("Виберіть кінцеву вершину (0-7):");
                int endNode = scanner.nextInt();

                System.out.println("\nРезультати:");

                // Алгоритм Дейкстри
                dijkstra(graph, startNode, endNode);

                // Алгоритм Флойда-Воршалла
                floydWarshall(graph, startNode, endNode);

                scanner.close();
            }

            public static void dijkstra(int[][] graph, int startNode, int endNode) {
                int numNodes = graph.length;
                int[] distances = new int[numNodes];
                boolean[] visited = new boolean[numNodes];
                int[] parent = new int[numNodes];

                Arrays.fill(distances, INF);
                Arrays.fill(visited, false);
                distances[startNode] = 0;
                Arrays.fill(parent, -1);

                for (int i = 0; i < numNodes - 1; i++) {
                    int u = findMinDistance(distances, visited);
                    if (u == -1) {
                        break;
                    }
                    visited[u] = true;

                    for (int v = 0; v < numNodes; v++) {
                        if (!visited[v] && graph[u][v] != INF && distances[u] != INF && distances[u] + graph[u][v] < distances[v]) {
                            distances[v] = distances[u] + graph[u][v];
                            parent[v] = u;
                        }
                    }
                }

                System.out.println("\nАлгоритм Дейкстри:");
                if (distances[endNode] == INF) {
                    System.out.println("Шлях від " + startNode + " до " + endNode + " не знайдено.");
                } else {
                    System.out.println("Найкоротша відстань від " + startNode + " до " + endNode + ": " + distances[endNode]);
                    System.out.print("Шлях: ");
                    printPath(parent, endNode);
                    System.out.println();
                }
            }

            private static int findMinDistance(int[] distances, boolean[] visited) {
                int minDistance = INF;
                int minIndex = -1;
                for (int v = 0; v < distances.length; v++) {
                    if (!visited[v] && distances[v] < minDistance) {
                        minDistance = distances[v];
                        minIndex = v;
                    }
                }
                return minIndex;
            }

            private static void printPath(int[] parent, int j) {
                if (parent[j] == -1) {
                    System.out.print(j + " ");
                    return;
                }
                printPath(parent, parent[j]);
                System.out.print(j + " ");
            }

            public static void floydWarshall(int[][] graph, int startNode, int endNode) {
                int numNodes = graph.length;
                int[][] distances = new int[numNodes][numNodes];
                int[][] next = new int[numNodes][numNodes];

                // Ініціалізація матриць відстаней та наступних вершин
                for (int i = 0; i < numNodes; i++) {
                    for (int j = 0; j < numNodes; j++) {
                        distances[i][j] = graph[i][j];
                        if (i == j) {
                            next[i][j] = -1;
                        } else if (graph[i][j] != INF) {
                            next[i][j] = j;
                        } else {
                            next[i][j] = -1;
                        }
                    }
                }

                // Алгоритм Флойда-Воршалла
                for (int k = 0; k < numNodes; k++) {
                    for (int i = 0; i < numNodes; i++) {
                        for (int j = 0; j < numNodes; j++) {
                            if (distances[i][k] != INF && distances[k][j] != INF &&
                                    distances[i][j] > distances[i][k] + distances[k][j]) {
                                distances[i][j] = distances[i][k] + distances[k][j];
                                next[i][j] = next[i][k];
                            }
                        }
                    }
                }

                System.out.println("\nАлгоритм Флойда-Воршалла:");
                if (distances[startNode][endNode] == INF) {
                    System.out.println("Шлях від " + startNode + " до " + endNode + " не знайдено.");
                } else {
                    System.out.println("Найкоротша відстань від " + startNode + " до " + endNode + ": " +
                            distances[startNode][endNode]);
                    System.out.print("Шлях: ");
                    printFloydWarshallPath(next, startNode, endNode);
                    System.out.println();
                }
            }

            private static void printFloydWarshallPath(int[][] next, int i, int j) {
                if (i == j) {
                    System.out.print(i + " ");
                    return;
                }
                if (next[i][j] == -1) {
                    System.out.print("Шлях не існує");
                    return;
                }
                System.out.print(i + " ");
                while (i != j) {
                    i = next[i][j];
                    System.out.print(i + " ");
                }
            }
        }

        ShortestPathFinder.lab10_();
    }
}

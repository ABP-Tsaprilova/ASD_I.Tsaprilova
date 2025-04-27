package labs;
import java.util.*;

public class Lab_10 {
    public static Map<Integer, Integer> dijkstra(int graph[][], int startNode) {
        int numNodes = graph.length;
        int[] shortestDistances = new int[numNodes];
        boolean[] added = new boolean[numNodes];
        Map<Integer, Integer> parentMap = new HashMap<>();

        for (int i = 0; i < numNodes; i++) {
            shortestDistances[i] = Integer.MAX_VALUE;
            added[i] = false;
            parentMap.put(i, null);
        }

        shortestDistances[startNode] = 0;

        for (int i = 1; i < numNodes; i++) {
            int nearestNode = -1;
            int shortestDistance = Integer.MAX_VALUE;

            for (int j = 0; j < numNodes; j++) {
                if (!added[j] && shortestDistances[j] < shortestDistance) {
                    nearestNode = j;
                    shortestDistance = shortestDistances[j];
                }
            }

            if (nearestNode == -1) {
                break;
            }

            added[nearestNode] = true;

            for (int neighborNode = 0; neighborNode < numNodes; neighborNode++) {
                if (!added[neighborNode] && graph[nearestNode][neighborNode] != 0) {
                    int newDistance = shortestDistances[nearestNode] + graph[nearestNode][neighborNode];
                    if (newDistance < shortestDistances[neighborNode]) {
                        shortestDistances[neighborNode] = newDistance;
                        parentMap.put(neighborNode, nearestNode);
                    }
                }
            }
        }

        return parentMap;
    }

    // Відтворення шляху (перенесено з попереднього коду)
    public static List<Integer> getPath(Map<Integer, Integer> parentMap, int targetNode) {
        List<Integer> path = new ArrayList<>();
        Integer currentNode = targetNode;
        while (currentNode != null) {
            path.add(currentNode);
            currentNode = parentMap.get(currentNode);
        }
        Collections.reverse(path);
        return path;
    }

    // Алгоритм Флойда-Воршелла (перенесено з попереднього коду)
    public static int[][] floydWarshall(int graph[][]) {
        int numNodes = graph.length;
        int[][] distanceMatrix = new int[numNodes][numNodes];

        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if (i == j) {
                    distanceMatrix[i][j] = 0;
                } else if (graph[i][j] != 0) {
                    distanceMatrix[i][j] = graph[i][j];
                } else {
                    distanceMatrix[i][j] = Integer.MAX_VALUE / 2;
                }
            }
        }

        for (int k = 0; k < numNodes; k++) {
            for (int i = 0; i < numNodes; i++) {
                for (int j = 0; j < numNodes; j++) {
                    distanceMatrix[i][j] = Math.min(distanceMatrix[i][j], distanceMatrix[i][k] + distanceMatrix[k][j]);
                }
            }
        }

        return distanceMatrix;
    }

    // Допоміжна функція для обчислення довжини шляху (перенесено з попереднього коду)
    private static int getShortestDistance(int[][] graph, List<Integer> path) {
        int distance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            distance += graph[path.get(i)][path.get(i + 1)];
        }
        return distance;
    }

    public static void lab_10() {
        Scanner scanner = new Scanner(System.in);

        // Представлення графу (заповніть відповідно до вашого графу)
        int[][] graph = new int[][]{
                {0, 2, 3, 5, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 4, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 6, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 6, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 8, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 13, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int numNodes = graph.length;

        while (true) {
            System.out.println("\nМеню пошуку найкоротшого шляху:");
            System.out.println("1. Алгоритм Дейкстри");
            System.out.println("2. Алгоритм Флойда-Воршелла (вивести відстань)");
            System.out.println("0. Вихід");
            System.out.print("Виберіть опцію: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Введіть початкову вершину (1-" + numNodes + "): ");
                    int startDijkstra = scanner.nextInt() - 1;
                    System.out.print("Введіть кінцеву вершину (1-" + numNodes + "): ");
                    int endDijkstra = scanner.nextInt() - 1;

                    if (startDijkstra >= 0 && startDijkstra < numNodes && endDijkstra >= 0 && endDijkstra < numNodes) {
                        Map<Integer, Integer> parentMapDijkstra = dijkstra(graph, startDijkstra);
                        List<Integer> pathDijkstra = getPath(parentMapDijkstra, endDijkstra);
                        int distanceDijkstra = (parentMapDijkstra.containsKey(endDijkstra) && pathDijkstra.get(pathDijkstra.size() - 1) == endDijkstra)
                                ? getShortestDistance(graph, pathDijkstra)
                                : Integer.MAX_VALUE;

                        if (distanceDijkstra == Integer.MAX_VALUE) {
                            System.out.println("Шлях від вершини " + (startDijkstra + 1) + " до вершини " + (endDijkstra + 1) + " не знайдено.");
                        } else {
                            System.out.println("Найкоротший шлях від вершини " + (startDijkstra + 1) + " до вершини " + (endDijkstra + 1) + ": " + pathDijkstra);
                            System.out.println("Довжина найкоротшого шляху: " + distanceDijkstra);
                        }
                    } else {
                        System.out.println("Некоректний ввід вершин.");
                    }
                    break;

                case 2:
                    System.out.print("Введіть початкову вершину (1-" + numNodes + "): ");
                    int startFloyd = scanner.nextInt() - 1;
                    System.out.print("Введіть кінцеву вершину (1-" + numNodes + "): ");
                    int endFloyd = scanner.nextInt() - 1;

                    if (startFloyd >= 0 && startFloyd < numNodes && endFloyd >= 0 && endFloyd < numNodes) {
                        int[][] distanceMatrixFloyd = floydWarshall(graph);
                        if (distanceMatrixFloyd[startFloyd][endFloyd] >= Integer.MAX_VALUE / 2) {
                            System.out.println("Шлях від вершини " + (startFloyd + 1) + " до вершини " + (endFloyd + 1) + " не знайдено.");
                        } else {
                            System.out.println("Найкоротша відстань від вершини " + (startFloyd + 1) + " до вершини " + (endFloyd + 1) + ": " + distanceMatrixFloyd[startFloyd][endFloyd]);
                            // Для відтворення шляху Флойда-Воршелла потрібна додаткова матриця попередників
                        }
                    } else {
                        System.out.println("Некоректний ввід вершин.");
                    }
                    break;

                case 0:
                    System.out.println("Вихід з програми.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Некоректний вибір. Спробуйте ще раз.");
            }
        }
    }

}


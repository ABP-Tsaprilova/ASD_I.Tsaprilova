package labs;
import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

public class Lab_11 {
    public static void lab_11() {
        int vertices = 8;
        List<Edge> allEdgesList = Arrays.asList(
                new Edge(1, 2, 2),
                new Edge(1, 3, 3),
                new Edge(1, 4, 5),
                new Edge(1, 8, 7),
                new Edge(2, 3, 4),
                new Edge(2, 5, 1),
                new Edge(3, 4, 6),
                new Edge(3, 6, 2),
                new Edge(4, 7, 5),
                new Edge(5, 6, 3),
                new Edge(5, 8, 6),
                new Edge(6, 7, 1),
                new Edge(6, 8, 8)
        );

        // Створення списку суміжності для алгоритму Прима
        List<List<Edge>> adj = new ArrayList<>(vertices + 1);
        for (int i = 0; i <= vertices; i++) {
            adj.add(new ArrayList<>());
        }
        for (Edge edge : allEdgesList) {
            adj.get(edge.src).add(new Edge(edge.src, edge.dest, edge.weight));
            adj.get(edge.dest).add(new Edge(edge.dest, edge.src, edge.weight));
        }

        MSTAlgorithms mstAlgo = new MSTAlgorithms();

        int[] primComparisons = new int[1];
        List<Edge> mstPrim = mstAlgo.primMST(vertices, adj, primComparisons);
        int weightPrim = mstAlgo.calculateMSTWeight(mstPrim);

        System.out.println("\nМінімальне остовне дерево, знайдене алгоритмом Прима:");
        mstAlgo.printMST(mstPrim);
        System.out.println("Вартість: " + weightPrim);
        System.out.println("Кількість порівнянь (приблизно): " + primComparisons[0]);

        int[] kruskalComparisons = new int[1];
        List<Edge> mstKruskal = mstAlgo.kruskalMST(vertices, new ArrayList<>(allEdgesList), kruskalComparisons);
        int weightKruskal = mstAlgo.calculateMSTWeight(mstKruskal);

        System.out.println("\nМінімальне остовне дерево, знайдене алгоритмом Крускала:");
        mstAlgo.printMST(mstKruskal);
        System.out.println("Вартість: " + weightKruskal);
        System.out.println("Кількість порівнянь (приблизно): " + kruskalComparisons[0]);

        System.out.println("\nПорівняння алгоритмів:");
        System.out.println("Вартість остовного дерева Прима: " + weightPrim);
        System.out.println("Вартість остовного дерева Крускала: " + weightKruskal);
        System.out.println("Кількість порівнянь (приблизно) Прима: " + primComparisons[0]);
        System.out.println("Кількість порівнянь (приблизно) Крускала: " + kruskalComparisons[0]);
    }

    public static void main(String[] args) {
        Lab_11 lab11 = new Lab_11();
        lab11.lab_11();
    }
}

class MSTAlgorithms {

    public static class Subset {
        int parent, rank;

        public Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    }

    public static int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    public static void union(Subset[] subsets, int x, int y) {
        int rootX = find(subsets, x);
        int rootY = find(subsets, y);
        if (subsets[rootX].rank < subsets[rootY].rank) {
            subsets[rootX].parent = rootY;
        } else if (subsets[rootX].rank > subsets[rootY].rank) {
            subsets[rootY].parent = rootX;
        } else {
            subsets[rootY].parent = rootX;
            subsets[rootX].rank++;
        }
    }

    public static List<Edge> primMST(int vertices, List<List<Edge>> adj, int[] comparisons) {
        List<Edge> mst = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        boolean[] inMST = new boolean[vertices + 1];
        int startVertex = 1;
        inMST[startVertex] = true;
        comparisons[0] = 0;

        for (Edge edge : adj.get(startVertex)) {
            pq.offer(edge);
            comparisons[0]++;
        }

        while (mst.size() < vertices - 1 && !pq.isEmpty()) {
            Edge currentEdge = pq.poll();
            int u = currentEdge.src;
            int v = currentEdge.dest;

            if (inMST[u] && !inMST[v]) {
                mst.add(currentEdge);
                inMST[v] = true;
                for (Edge neighborEdge : adj.get(v)) {
                    int neighbor = (neighborEdge.src == v) ? neighborEdge.dest : neighborEdge.src;
                    if (!inMST[neighbor]) {
                        pq.offer(neighborEdge);
                        comparisons[0]++;
                    }
                }
            } else if (!inMST[u] && inMST[v]) {
                mst.add(currentEdge);
                inMST[u] = true;
                for (Edge neighborEdge : adj.get(u)) {
                    int neighbor = (neighborEdge.src == u) ? neighborEdge.dest : neighborEdge.src;
                    if (!inMST[neighbor]) {
                        pq.offer(neighborEdge);
                        comparisons[0]++;
                    }
                }
            }
        }
        return mst;
    }

    public static List<Edge> kruskalMST(int vertices, List<Edge> edges, int[] comparisons) {
        List<Edge> mst = new ArrayList<>();
        Collections.sort(edges);
        comparisons[0] = edges.size() * (int) (Math.log(edges.size()) / Math.log(2)); // Approximation
        Subset[] subsets = new Subset[vertices + 1];
        for (int i = 1; i <= vertices; i++) {
            subsets[i] = new Subset(i, 0);
        }

        int i = 0;
        while (mst.size() < vertices - 1 && i < edges.size()) {
            Edge currentEdge = edges.get(i++);
            int rootSrc = find(subsets, currentEdge.src);
            int rootDest = find(subsets, currentEdge.dest);
            comparisons[0]++; // Implicit comparison in find

            if (rootSrc != rootDest) {
                mst.add(currentEdge);
                union(subsets, rootSrc, rootDest);
            }
        }
        return mst;
    }

    public static int calculateMSTWeight(List<Edge> mst) {
        int weight = 0;
        for (Edge edge : mst) {
            weight += edge.weight;
        }
        return weight;
    }

    public static void printMST(List<Edge> mst) {
        System.out.println("Ребра остовного дерева:");
        for (Edge edge : mst) {
            System.out.println("(" + edge.src + " - " + edge.dest + ") вага: " + edge.weight);
        }
    }
}
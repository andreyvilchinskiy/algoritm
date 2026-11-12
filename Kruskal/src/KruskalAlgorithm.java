import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgorithm {

    static int V = 9;
    static List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) {
        // 9 ребер
        addEdge(0, 1, 5);
        addEdge(1, 2, 3);
        addEdge(2, 3, 2);
        addEdge(2, 4, 2);
        addEdge(2, 6, 3);
        addEdge(4, 5, 3);
        addEdge(4, 6, 6);
        addEdge(6, 7, 2);
        addEdge(7, 8, 4);

        System.out.println("=== Запуск алгоритма Краскала ===");
        kruskalMST();
    }

    static void addEdge(int u, int v, int w) {
        edges.add(new Edge(u, v, w));
    }

    static void kruskalMST() {
        List<Edge> resultMST = new ArrayList<>();

        Collections.sort(edges);

        DisjointSet[] subsets = new DisjointSet[V];
        for (int i = 0; i < V; ++i) {
            subsets[i] = new DisjointSet(i, 0);
        }

        int edgeCount = 0;
        int i = 0;

        // Для 9 вершин нужно 8 ребер
        while (edgeCount < V - 1 && i < edges.size()) {
            Edge nextEdge = edges.get(i++);

            int x = DisjointSet.find(subsets, nextEdge.src);
            int y = DisjointSet.find(subsets, nextEdge.dest);

            if (x != y) {
                resultMST.add(nextEdge);
                DisjointSet.union(subsets, x, y);
                edgeCount++;
            }
        }

        int totalWeight = 0;
        for (Edge e : resultMST) {
            System.out.println(e);
            totalWeight += e.weight;
        }
        System.out.println("--------------------");
        System.out.println("Общая стоимость (Краскал): " + totalWeight);
    }
}
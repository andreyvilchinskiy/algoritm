import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrimAlgorithm {

    static int V = 9;

    public static void main(String[] args) {
        List<List<Edge>> graph = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }

        // 9 ребер
        addEdge(graph, 0, 1, 5);
        addEdge(graph, 1, 2, 3);
        addEdge(graph, 2, 3, 2);
        addEdge(graph, 2, 4, 2);
        addEdge(graph, 2, 6, 3);
        addEdge(graph, 4, 5, 3);
        addEdge(graph, 4, 6, 6);
        addEdge(graph, 6, 7, 2);
        addEdge(graph, 7, 8, 4);

        System.out.println("=== Запуск алгоритма Прима (Старт из вершины 1) ===");
        primMST(graph);
    }

    static void addEdge(List<List<Edge>> graph, int u, int v, int w) {
        graph.get(u).add(new Edge(v, w));
        graph.get(v).add(new Edge(u, w));
    }

    static void primMST(List<List<Edge>> graph) {
        boolean[] inMST = new boolean[V];
        int[] key = new int[V];
        int[] parent = new int[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        // СТАРТ вершина 1
        key[1] = 0;

        for (int count = 0; count < V; count++) {
            int u = -1;
            int min = Integer.MAX_VALUE;
            for (int v = 0; v < V; v++) {
                if (!inMST[v] && key[v] < min) {
                    min = key[v];
                    u = v;
                }
            }

            if (u == -1) break;

            inMST[u] = true;

            for (Edge edge : graph.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;

                if (!inMST[v] && weight < key[v]) {
                    parent[v] = u;
                    key[v] = weight;
                }
            }
        }

        int totalWeight = 0;
        System.out.println("Ребра, включенные в MST (Вершина1 - Вершина2 : Вес):");
        for (int i = 0; i < V; i++) {
            if (parent[i] != -1) {
                System.out.println(parent[i] + " - " + i + " : " + key[i]);
                totalWeight += key[i];
            }
        }
        System.out.println("--------------------");
        System.out.println("Общая стоимость (Прим): " + totalWeight);
    }
}
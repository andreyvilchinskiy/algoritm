package Network_delays;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final int vertexCount;
    private final List<List<Edge>> adjacency;

    public Graph(int vertexCount) {
        this.vertexCount = vertexCount;
        this.adjacency = new ArrayList<>(vertexCount);
        for (int i = 0; i < vertexCount; i++) {
            adjacency.add(new ArrayList<>());
        }
    }

    public void addEdge(int from, int to, int weight) {
        adjacency.get(from).add(new Edge(to, weight));
        adjacency.get(to).add(new Edge(from, weight));
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public List<Edge> getEdges(int vertex) {
        return adjacency.get(vertex);
    }

    // Суммарная длина всех проводов
    public long totalLength() {
        long sum = 0;
        for (List<Edge> list : adjacency) {
            for (Edge e : list) {
                sum += e.weight;
            }
        }
        return sum / 2; // каждое ребро учтено дважды
    }
}
package model;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final int vertices;
    private final List<List<Edge>> adjacency;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.adjacency = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacency.add(new ArrayList<>());
        }
    }

    public void addEdge(int u, int v, long weight) {
        adjacency.get(u).add(new Edge(v, weight));
        adjacency.get(v).add(new Edge(u, weight));
    }

    public int getVertices() {
        return vertices;
    }

    public List<Edge> getNeighbors(int vertex) {
        return adjacency.get(vertex);
    }
}
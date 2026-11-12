public class Edge implements Comparable<Edge> {
    public int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }

    @Override
    public String toString() {
        return String.format("Ребро (%d <-> %d) | Вес: %d", src, dest, weight);
    }
}
package Network_delays;

/**
 * Ребро графа: куда ведёт и какова длина провода.
 */
public class Edge {
    public final int to;
    public final int weight;

    public Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}
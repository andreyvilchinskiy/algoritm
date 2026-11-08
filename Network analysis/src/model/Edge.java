package model;

public class Edge {
    private final int to;
    private final long weight;

    public Edge(int to, long weight) {
        this.to = to;
        this.weight = weight;
    }

    public int getTo() {
        return to;
    }

    public long getWeight() {
        return weight;
    }
}
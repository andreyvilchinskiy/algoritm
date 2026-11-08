package algorithm;

import model.Graph;

public class NetworkAnalyzer {

    private final long[] distToEntry;
    private final int[] nearestEntry;


    public NetworkAnalyzer(Graph graph, int[] entryPoints) {
        int n = graph.getVertices();
        distToEntry = new long[n];
        nearestEntry = new int[n];
        java.util.Arrays.fill(distToEntry, Dijkstra.INF);
        java.util.Arrays.fill(nearestEntry, -1);

        java.util.PriorityQueue<long[]> pq = new java.util.PriorityQueue<>(
                (a, b) -> Long.compare(a[1], b[1]));

        for (int ep : entryPoints) {
            distToEntry[ep] = 0;
            nearestEntry[ep] = ep;
            pq.add(new long[]{ep, 0});
        }

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int v = (int) cur[0];
            long d = cur[1];
            if (d > distToEntry[v]) continue;

            for (model.Edge e : graph.getNeighbors(v)) {
                long nd = d + e.getWeight();
                if (nd < distToEntry[e.getTo()]) {
                    distToEntry[e.getTo()] = nd;
                    nearestEntry[e.getTo()] = nearestEntry[v];
                    pq.add(new long[]{e.getTo(), nd});
                }
            }
        }
    }

    public long[] getDistances() {
        return distToEntry;
    }

    public int[] getNearestEntries() {
        return nearestEntry;
    }

    public long totalDelay() {
        long sum = 0;
        for (long d : distToEntry) sum += d;
        return sum;
    }

    public int maxDelayRouter() {
        int idx = 0;
        for (int i = 1; i < distToEntry.length; i++) {
            if (distToEntry[i] > distToEntry[idx]) idx = i;
        }
        return idx;
    }

    public double averageDelay() {
        return (double) totalDelay() / distToEntry.length;
    }
}
package algorithm;

import model.Edge;
import model.Graph;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

    public static final long INF = Long.MAX_VALUE / 4;

    public static long[] distances(Graph graph, int source) {
        int n = graph.getVertices();
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.add(new long[]{source, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int v = (int) cur[0];
            long d = cur[1];
            if (d > dist[v]) continue;

            for (Edge e : graph.getNeighbors(v)) {
                long nd = d + e.getWeight();
                if (nd < dist[e.getTo()]) {
                    dist[e.getTo()] = nd;
                    pq.add(new long[]{e.getTo(), nd});
                }
            }
        }
        return dist;
    }

    public static List<Integer> path(Graph graph, int source, int target) {
        int n = graph.getVertices();
        long[] dist = new long[n];
        int[] prev = new int[n];
        Arrays.fill(dist, INF);
        Arrays.fill(prev, -1);
        dist[source] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.add(new long[]{source, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int v = (int) cur[0];
            long d = cur[1];
            if (d > dist[v]) continue;

            for (Edge e : graph.getNeighbors(v)) {
                long nd = d + e.getWeight();
                if (nd < dist[e.getTo()]) {
                    dist[e.getTo()] = nd;
                    prev[e.getTo()] = v;
                    pq.add(new long[]{e.getTo(), nd});
                }
            }
        }

        java.util.LinkedList<Integer> result = new java.util.LinkedList<>();
        for (int v = target; v != -1; v = prev[v]) {
            result.addFirst(v);
        }
        // Если путь не существует (но по условию граф связен с точками входа)
        if (result.getFirst() != source) {
            return java.util.Collections.emptyList();
        }
        return result;
    }
}
package Network_delays;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

public class NetworkDelay {

    public static long computeTotalDelay(Graph graph, int[] entryPoints) {
        int n = graph.getVertexCount();

        // Верхняя граница для любого кратчайшего расстояния.
        int maxDist = (int) Math.min(Integer.MAX_VALUE - 1, 100L * n + 1);

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        boolean[] visited = new boolean[n];

        // Bucket queue: buckets[d] содержит вершины с текущим расстоянием d.
        @SuppressWarnings("unchecked")
        ArrayDeque<Integer>[] buckets = new ArrayDeque[maxDist + 1];
        for (int i = 0; i <= maxDist; i++) {
            buckets[i] = new ArrayDeque<>();
        }

        // Инициализация: все точки входа имеют расстояние 0.
        for (int entry : entryPoints) {
            dist[entry] = 0;
            buckets[0].add(entry);
        }

        int processedDistance = 0;

        while (processedDistance <= maxDist) {
            ArrayDeque<Integer> bucket = buckets[processedDistance];
            if (bucket.isEmpty()) {
                processedDistance++;
                continue;
            }

            int v = bucket.poll();
            if (visited[v]) continue;
            if (dist[v] != processedDistance) continue; // устаревшая запись
            visited[v] = true;

            List<Edge> edges = graph.getEdges(v);
            for (Edge e : edges) {
                int u = e.to;
                int nd = processedDistance + e.weight;
                if (nd <= maxDist && nd < dist[u]) {
                    dist[u] = nd;
                    buckets[nd].add(u);
                }
            }
        }

        // Суммируем задержки. По условию для каждого маршрутизатора задержка конечна.
        long total = 0;
        for (int i = 0; i < n; i++) {
            total += dist[i];
        }
        return total;
    }
}
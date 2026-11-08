package algorithm;

import model.Graph;
import model.RouteResult;

import java.util.ArrayList;
import java.util.List;

public class RoutePlanner {


    public static RouteResult planOptimalRoute(Graph graph, int start, int a, int b, int c) {
        int[] required = {a, b, c};
        int[][] permutations = {
                {0, 1, 2}, {0, 2, 1}, {1, 0, 2},
                {1, 2, 0}, {2, 0, 1}, {2, 1, 0}
        };

        // Предрасчёт попарных расстояний и путей
        int[] allNodes = {start, a, b, c};
        java.util.Map<Long, List<Integer>> pathCache = new java.util.HashMap<>();
        java.util.Map<Long, Long> distCache = new java.util.HashMap<>();

        long bestLen = Long.MAX_VALUE;
        List<Integer> bestOrder = null;
        List<Integer> bestFullPath = null;

        for (int[] perm : permutations) {
            long total = 0;
            List<Integer> full = new ArrayList<>();
            int current = start;
            full.add(current);
            boolean ok = true;

            for (int idx : perm) {
                int next = required[idx];
                long key = (long) current * 1000 + next;
                List<Integer> seg;
                long segLen;
                if (pathCache.containsKey(key)) {
                    seg = pathCache.get(key);
                    segLen = distCache.get(key);
                } else {
                    seg = Dijkstra.path(graph, current, next);
                    segLen = segmentLength(graph, seg);
                    pathCache.put(key, seg);
                    distCache.put(key, segLen);
                }
                if (seg.isEmpty()) { ok = false; break; }
                // избегаем дублирования начальной вершины сегмента
                for (int i = 1; i < seg.size(); i++) full.add(seg.get(i));
                total += segLen;
                current = next;
            }

            if (!ok) continue;

            // возврат в start
            long key = (long) current * 1000 + start;
            List<Integer> seg;
            long segLen;
            if (pathCache.containsKey(key)) {
                seg = pathCache.get(key);
                segLen = distCache.get(key);
            } else {
                seg = Dijkstra.path(graph, current, start);
                segLen = segmentLength(graph, seg);
                pathCache.put(key, seg);
                distCache.put(key, segLen);
            }
            if (seg.isEmpty()) continue;
            for (int i = 1; i < seg.size(); i++) full.add(seg.get(i));
            total += segLen;

            if (total < bestLen) {
                bestLen = total;
                bestOrder = new ArrayList<>();
                for (int idx : perm) bestOrder.add(required[idx]);
                bestFullPath = full;
            }
        }

        return new RouteResult(bestOrder, bestFullPath, bestLen);
    }

    private static long segmentLength(Graph graph, List<Integer> path) {
        if (path.size() < 2) return 0;
        long sum = 0;
        for (int i = 0; i + 1 < path.size(); i++) {
            int u = path.get(i);
            int v = path.get(i + 1);
            long best = Long.MAX_VALUE;
            for (model.Edge e : graph.getNeighbors(u)) {
                if (e.getTo() == v && e.getWeight() < best) best = e.getWeight();
            }
            sum += best;
        }
        return sum;
    }
}
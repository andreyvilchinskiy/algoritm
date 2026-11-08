package Honeymoon;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightGraph {
    private final int cityCount;
    private final List<List<Integer>> adjacency;

    public FlightGraph(int cityCount) {
        this.cityCount = cityCount;
        this.adjacency = new ArrayList<>(cityCount);
        for (int i = 0; i < cityCount; i++) {
            adjacency.add(new ArrayList<>());
        }
    }

    public void addFlight(int from, int to) {
        adjacency.get(from).add(to);
    }

    public int getCityCount() {
        return cityCount;
    }

    public int[] bfs(int source) {
        int[] dist = new int[cityCount];
        Arrays.fill(dist, Integer.MAX_VALUE);

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        dist[source] = 0;
        queue.add(source);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            int nd = dist[v] + 1;
            for (int u : adjacency.get(v)) {
                if (dist[u] == Integer.MAX_VALUE) {
                    dist[u] = nd;
                    queue.add(u);
                }
            }
        }

        return dist;
    }
}

package Honeymoon;

public class HoneymoonPlanner {

    private final FlightGraph graph;

    /** Массив из 4 BFS-таблиц: [0] — из home, [1..3] — из туристических городов. */
    private final int[][] distTable; //граф рейсов
    private final int[] tourists; //индекс родного города
    private final int home; //массив из трёх индексов туристических городов

    public HoneymoonPlanner(FlightGraph graph, int home, int[] tourists) {
        if (tourists.length != 3) {
            throw new IllegalArgumentException("Ожидается ровно 3 туристических города");
        }
        this.graph = graph;
        this.home = home;
        this.tourists = tourists.clone();

        this.distTable = new int[4][];
        this.distTable[0] = graph.bfs(home);
        for (int i = 0; i < 3; i++) {
            this.distTable[1 + i] = graph.bfs(tourists[i]);
        }
    }

    //Возвращает минимальное суммарное число перелётовили -1, если маршрут невозможен.
    public int minFlights() {
        int[][] dist = new int[4][4];
        for (int a = 0; a < 4; a++) {
            if (a == 0) {
                dist[0][0] = 0;
                for (int b = 1; b < 4; b++) {
                    dist[0][b] = distTable[0][tourists[b - 1]];
                }
            } else {
                for (int b = 0; b < 4; b++) {
                    int target = (b == 0) ? home : tourists[b - 1];
                    dist[a][b] = distTable[a][target];
                }
            }
        }

        // все перестановки {1, 2, 3}.
        int[][] perms = {
                {1, 2, 3}, {1, 3, 2},
                {2, 1, 3}, {2, 3, 1},
                {3, 1, 2}, {3, 2, 1}
        };

        int best = Integer.MAX_VALUE;

        for (int[] p : perms) {
            long cost = 0;
            boolean ok = true;

            int[] seq = {0, p[0], p[1], p[2], 0};
            for (int i = 0; i + 1 < seq.length; i++) {
                int d = dist[seq[i]][seq[i + 1]];
                if (d == Integer.MAX_VALUE) {
                    ok = false;
                    break;
                }
                cost += d;
            }

            if (ok && cost < best) {
                best = (int) cost;
            }
        }

        return best == Integer.MAX_VALUE ? -1 : best;
    }
}

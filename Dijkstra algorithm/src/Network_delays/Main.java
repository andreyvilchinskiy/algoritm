package Network_delays;

import java.util.Scanner;
/**
 Ввод:

 5 6 1
 0
 0 1 4
 0 2 2
 2 1 1
 1 3 5
 2 3 8
 3 4 3

 Вывод: 24
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();       // число маршрутизаторов
        int m = sc.nextInt();       // число проводов
        int k = sc.nextInt();       // число точек входа

        int[] entryPoints = new int[k];
        for (int i = 0; i < k; i++) {
            entryPoints[i] = sc.nextInt();
        }

        Graph graph = new Graph(r);
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.addEdge(u, v, w);
        }

        long totalDelay = NetworkDelay.computeTotalDelay(graph, entryPoints);
        System.out.println(totalDelay);

        sc.close();
    }
}
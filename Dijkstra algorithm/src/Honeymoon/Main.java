package Honeymoon;

import java.util.Scanner;

/**
 Ввод:
 4 8
 0 1 2 3
 0 1
 1 2
 2 3
 3 0
 0 2
 2 1
 1 3
 3 1

 Вывод:4

 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int c = sc.nextInt();   // число городов
        int f = sc.nextInt();   // число прямых рейсов

        int home = sc.nextInt();
        int[] tourists = {sc.nextInt(), sc.nextInt(), sc.nextInt()};

        FlightGraph graph = new FlightGraph(c);
        for (int i = 0; i < f; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.addFlight(u, v);
        }

        HoneymoonPlanner planner = new HoneymoonPlanner(graph, home, tourists);
        System.out.println(planner.minFlights());

        sc.close();
    }
}
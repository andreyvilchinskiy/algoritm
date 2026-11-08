import algorithm.EntryPointOptimizer;
import algorithm.NetworkAnalyzer;
import algorithm.RoutePlanner;
import io.InputReader;
import model.RouteResult;

/**
Ввод:
5 6
0 1 10
1 2 5
2 3 7
3 4 3
0 2 20
1 4 15
1
2
0
1 3 4
0 2 4
 */
public class Main {

    public static void main(String[] args) throws Exception {
        InputReader reader = new InputReader();
        InputReader.ParsedInput in = reader.read();

        System.out.println("===== Задача 1. Расстояния до Интернета =====");
        NetworkAnalyzer analyzer = new NetworkAnalyzer(in.graph, in.entryPoints);
        long[] dist = analyzer.getDistances();
        int[] nearest = analyzer.getNearestEntries();

        for (int i = 0; i < dist.length; i++) {
            System.out.printf("Маршрутизатор %d: расстояние = %d, ближайшая точка входа = %d%n",
                    i, dist[i], nearest[i]);
        }
        System.out.println("Общая задержка сети (сумма): " + analyzer.totalDelay());
        System.out.printf("Средняя задержка: %.2f%n", analyzer.averageDelay());
        int maxIdx = analyzer.maxDelayRouter();
        System.out.printf("Маршрутизатор с максимальной задержкой: %d (расстояние = %d)%n",
                maxIdx, dist[maxIdx]);

        System.out.println();
        System.out.println("===== Задача 2. Оптимальный маршрут специалиста =====");
        RouteResult route = RoutePlanner.planOptimalRoute(
                in.graph, in.start, in.required[0], in.required[1], in.required[2]);

        if (route.getOrder() == null) {
            System.out.println("Маршрут не найден.");
        } else {
            System.out.println("Порядок посещения обязательных точек: " + route.getOrder());
            System.out.println("Полный маршрут: " + route.getFullPath());
            System.out.println("Суммарная длина: " + route.getTotalLength());
        }

        System.out.println();
        System.out.println("===== Задача 3. Выбор дополнительной точки входа =====");
        EntryPointOptimizer.CandidateResult[] results =
                EntryPointOptimizer.evaluate(in.graph, in.entryPoints, in.candidates);

        for (EntryPointOptimizer.CandidateResult r : results) {
            System.out.printf("Кандидат %d: новая задержка = %d, снижение = %d (%.2f%%)%n",
                    r.candidate, r.totalDelay, r.reductionAbsolute, r.reductionPercent);
        }

        EntryPointOptimizer.CandidateResult best = EntryPointOptimizer.best(results);
        System.out.printf("Оптимальный кандидат: %d (снижение %d, %.2f%%)%n",
                best.candidate, best.reductionAbsolute, best.reductionPercent);
    }
}
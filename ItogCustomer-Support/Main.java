import java.util.Arrays;
import java.util.List;

public class Main {

    static Request[] initial() {
        return new Request[]{
                new Request(50, "Ошибка оплаты",           9),
                new Request(30, "Сброс пароля",            4),
                new Request(70, "Интеграция API",          1),
                new Request(20, "Изменение профиля",       2),
                new Request(40, "Ошибка отчёта",           7),
                new Request(60, "Настройка уведомлений",  11),
                new Request(80, "Сбой сервера",           15),
                new Request(10, "Удаление аккаунта",      12),
                new Request(25, "Проблема входа",          3),
                new Request(35, "Экспорт данных",          5),
                new Request(45, "Возврат платежа",         8),
                new Request(55, "Подключение тарифа",     10),
                new Request(65, "Ошибка синхронизации",   12),
                new Request(75, "Недоступна база данных", 14),
                new Request(90, "Обновление реквизитов",   6),
        };
    }

    public static void main(String[] args) {
        var tree = new Tree();

        // Этап 1
        System.out.println("== ЭТАП 1. Дерево ==");
        for (Request r : initial()) tree.insert(r);
        tree.printLevels();

        // Этап 2
        System.out.println("\n== ЭТАП 2. Обходы ==");
        System.out.println("Прямой:       " + ids(tree.preOrder()));
        System.out.println("Симметричный: " + ids(tree.inOrder()));
        System.out.println("Обратный:     " + ids(tree.postOrder()));
        System.out.println("Уровневый:    " + ids(tree.levelOrder()));
        System.out.println("Симметричный обход даёт возрастание id, потому что он работает так:");
        System.out.println("все ключи левого поддерева < корня < все ключи правого.");

        // Этап 3
        System.out.println("\n== ЭТАП 3. Поиск и характеристики ==");
        showSearch(tree, 65);
        showSearch(tree, 99);
        System.out.println("min id = " + tree.findMin());
        System.out.println("max id = " + tree.findMax());
        System.out.println("узлов = " + tree.countNodes());
        System.out.println("листьев = " + tree.countLeaves());
        System.out.println("высота = " + tree.height());
        System.out.println("глубина 65 = " + tree.getDepth(65));
        System.out.println("validate_bst = " + tree.validateBst());

        // Этап 4
        System.out.println("\n== ЭТАП 4. Изменение дерева ==");
        op(tree, "вставка 37",       tree.insert(new Request(37, "Утечка данных", 16)));
        op(tree, "повтор 50",        tree.insert(new Request(50, "Дубликат", 9)));
        op(tree, "удаление 10",      tree.delete(10));
        op(tree, "удаление 35",      tree.delete(35));
        op(tree, "удаление 70",      tree.delete(70));
        op(tree, "удаление 999",     tree.delete(999));

        // Этап 5
        System.out.println("\n== ЭТАП 5. Двоичная куча ==");
        List<Request> remaining = tree.inOrder();
        Request[] data = remaining.toArray(new Request[0]);

        System.out.println("Исходный массив:  " + Heap.str(data));
        Heap.buildMaxHeap(data);
        System.out.println("После buildMaxHeap: " + Heap.str(data));
        printHeapLevels(data);
        System.out.println("isMaxHeap = " + Heap.isMaxHeap(data));

        // Этап 6
        System.out.println("\n== ЭТАП 6. HeapSort ==");
        Request[] sorted = Arrays.copyOf(data, data.length);
        System.out.println("До:    " + Heap.str(sorted));
        Heap.heapSort(sorted);
        System.out.println("После: " + Heap.str(sorted));

        System.out.println("\nИтоговый отчёт:");
        System.out.printf("%-4s %-5s %-10s %s%n", "№", "ID", "Приоритет", "Наименование");
        System.out.println("-".repeat(55));
        for (int i = 0; i < sorted.length; i++) {
            Request r = sorted[i];
            System.out.printf("%-4d %-5d %-10d %s%n", i + 1, r.id, r.priority, r.title);
        }
    }

    // утилиты вывода

    static String ids(List<Request> list) {
        var sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).id);
            if (i < list.size() - 1) sb.append(", ");
        }
        return sb.toString();
    }

    static void showSearch(Tree t, int id) {
        Object[] r = t.search(id);
        Request found = (Request) r[0];
        @SuppressWarnings("unchecked")
        List<Integer> path = (List<Integer>) r[1];
        String p = path.toString().replaceAll("[\\[\\]]", "").replace(", ", " → ");
        System.out.println(found != null
                ? "Поиск " + id + ": найдено " + found + ", путь: " + p
                : "Поиск " + id + ": не найдено, путь: " + p);
    }

    static void op(Tree t, String name, boolean ok) {
        System.out.printf("%-14s %-10s | обход: %s | n=%d | h=%d | bst=%b%n",
                name, ok ? "OK" : "—",
                ids(t.inOrder()), t.countNodes(), t.height(), t.validateBst());
    }

    static void printHeapLevels(Request[] a) {
        int lvl = 0, start = 0;
        while (start < a.length) {
            int end = Math.min(start + (1 << lvl), a.length);
            var sb = new StringBuilder("Уровень " + lvl + ": ");
            for (int i = start; i < end; i++) {
                sb.append(a[i]);
                if (i < end - 1) sb.append(", ");
            }
            System.out.println(sb);
            start = end;
            lvl++;
        }
    }
}
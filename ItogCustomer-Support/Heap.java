// Max-куча на массиве: ключ (priority, id)
public class Heap {

    // Восстановление свойства кучи вниз от узла index
    public static void siftDown(Request[] a, int size, int index) {
        while (true) {
            int l = 2 * index + 1, r = l + 1, big = index;
            if (l < size && a[l].compareTo(a[big]) > 0){
                big = l;
            }
            if (r < size && a[r].compareTo(a[big]) > 0){
                big = r;
            }
            if (big == index) {
                return;
            }
            swap(a, index, big);
            index = big;
        }
    }

    // Построение max-кучи снизу вверх
    public static void buildMaxHeap(Request[] a) {
        for (int i = a.length / 2 - 1; i >= 0; i--) {
            siftDown(a, a.length, i);
        }
    }

    public static boolean isMaxHeap(Request[] a) {
        int n = a.length;
        for (int i = 0; i <= n / 2 - 1; i++) {
            int l = 2 * i + 1, r = l + 1;
            if (l < n && a[l].compareTo(a[i]) > 0) return false;
            if (r < n && a[r].compareTo(a[i]) > 0) return false;
        }
        return true;
    }

    // Сортировка кучей in-place (по возрастанию ключа)
    public static void heapSort(Request[] a) {
        buildMaxHeap(a);
        for (int end = a.length - 1; end > 0; end--) {
            swap(a, 0, end);
            siftDown(a, end, 0);
        }
    }

    private static void swap(Request[] a, int i, int j) {
        Request t = a[i]; a[i] = a[j]; a[j] = t;
    }

    public static String str(Request[] a) {
        var sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
            if (i < a.length - 1){
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
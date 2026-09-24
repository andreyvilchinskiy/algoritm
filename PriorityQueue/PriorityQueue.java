import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PriorityQueue<T> {

    // Элемент кучи: ключ (приоритет) и значение
    private static class Node<T> {
        int key;
        T value;

        Node(int key, T value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<T>[] heap;          // массив элементов
    private int size;                // текущий размер
    private Map<T, Integer> index;   // значение -> индекс в куче (для изменения ключа)

    @SuppressWarnings("unchecked")
    public PriorityQueue(int capacity) {
        heap = new Node[capacity];
        size = 0;
        index = new HashMap<>();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Индексы родителя и детей
    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i)   { return 2 * i + 1; }
    private int right(int i)  { return 2 * i + 2; }

    private void swap(int i, int j) {
        Node<T> tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
        index.put(heap[i].value, i);
        index.put(heap[j].value, j);
    }

    // Просеивание вверх
    private void siftUp(int i) {
        while (i > 0 && heap[parent(i)].key > heap[i].key) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // Просеивание вниз
    private void siftDown(int i) {
        while (true) {
            int smallest = i;
            int l = left(i);
            int r = right(i);

            if (l < size && heap[l].key < heap[smallest].key) smallest = l;
            if (r < size && heap[r].key < heap[smallest].key) smallest = r;

            if (smallest == i) break;
            swap(i, smallest);
            i = smallest;
        }
    }

    // Вставка нового элемента
    public void insert(int key, T value) {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
        Node<T> node = new Node<>(key, value);
        heap[size] = node;
        index.put(value, size);
        siftUp(size);
        size++;
    }

    // Извлечение минимума
    public T extractMin() {
        if (size == 0) return null;
        T minValue = heap[0].value;
        index.remove(minValue);

        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;

        if (size > 0) {
            index.put(heap[0].value, 0);
            siftDown(0);
        }
        return minValue;
    }

    // Просмотр минимума без извлечения
    public T peek() {
        return size == 0 ? null : heap[0].value;
    }

    // Изменение ключа (приоритета)
    public void changeKey(T value, int newKey) {
        Integer i = index.get(value);
        if (i == null) return;

        int oldKey = heap[i].key;
        heap[i].key = newKey;

        if (newKey < oldKey) {
            siftUp(i);
        } else {
            siftDown(i);
        }
    }
}
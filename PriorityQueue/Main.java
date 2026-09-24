public class Main {
    public static void main(String[] args) {
        PriorityQueue<String> PQ = new PriorityQueue<>(5);
        PQ.insert(5, "five");
        PQ.insert(1, "one");
        PQ.insert(3, "three");
        PQ.insert(2, "two");
        PQ.insert(4, "four");
        System.out.println("Длина очереди:"+PQ.size());
        System.out.println("Минимальное в очереди:"+PQ.peek());
        PQ.extractMin();
        System.out.println("Длина очереди:"+PQ.size());
        System.out.println("Минимальное в очереди:"+PQ.peek());
        PQ.changeKey("five",1 );
        System.out.println("Длина очереди:"+PQ.size());
        System.out.println("Минимальное в очереди:"+PQ.peek());
        PQ.extractMin();
        PQ.extractMin();
        System.out.println("Длина очереди:"+PQ.size());
        System.out.println("Очередь пуста:"+PQ.isEmpty());
    }
}
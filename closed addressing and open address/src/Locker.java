/**
 * ЗАДАНИЕ 3. Шкафчики в фитнес-клубе на 10 ячеек.
 * Открытая адресация — двойное хеширование.
 */
public class Locker {

    /** Простое число, меньшее 10 */
    private static final int P = 3;

    private final Integer[] lockers = new Integer[Data.SIZE];

    public static void main(String[] args) {
        new Locker().run();
    }

    public void run() {
        System.out.println("===== ЗАДАНИЕ 3. ШКАФЧИК (двойное хеширование, P=" + P + ") =====");

        for (int client : Data.CLIENTS) {
            int home = client % Data.SIZE;
            int placed = place(client);
            int attempts = attemptsUsed;

            if (placed == -1) {
                System.out.printf("Клиент %d → ВСЕ ШКАФЧИКИ ЗАНЯТЫ%n", client);
            } else if (attempts == 0) {
                System.out.printf("Клиент %d → шкафчик %d (по хешу)%n", client, placed);
            } else {
                System.out.printf("Клиент %d → шкафчик %d (коллизия! хеш был %d, попыток: %d)%n",
                        client, placed, home, attempts);
            }
        }

        printLockers();
    }

    private int attemptsUsed = 0;

    /** Ищем свободный шкафчик */
    private int place(int client) {
        for (int i = 0; i < Data.SIZE; i++) {
            int slot = Math.floorMod(client + i * P, Data.SIZE);
            if (lockers[slot] == null) {
                lockers[slot] = client;
                attemptsUsed = i;
                return slot;
            }
        }
        return -1;
    }

    private void printLockers() {
        System.out.println("\nИтоговые шкафчики:");
        for (int i = 0; i < lockers.length; i++) {
            System.out.printf("  [%d] = %s%n", i,
                    lockers[i] == null ? "—" : lockers[i].toString());
        }
    }
}
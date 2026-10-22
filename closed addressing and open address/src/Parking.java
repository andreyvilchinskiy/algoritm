/**
 * ЗАДАНИЕ 1. Парковка на 10 мест.
 * Открытая адресация — линейное пробирование.
 */
public class Parking {

    private final Integer[] spots = new Integer[Data.SIZE];

    public static void main(String[] args) {
        Parking parking = new Parking();
        parking.run();
    }

    public void run() {
        System.out.println("===== ЗАДАНИЕ 1. ПАРКОВКА (линейное пробирование) =====");

        for (int client : Data.CLIENTS) {
            int home = client % Data.SIZE;
            int placed = place(client);

            if (placed == -1) {
                System.out.printf("Клиент %d → ПАРКОВКА ПЕРЕПОЛНЕНА%n", client);
            } else if (placed == home) {
                System.out.printf("Клиент %d → место %d (по хешу)%n", client, placed);
            } else {
                System.out.printf("Клиент %d → место %d (коллизия! хеш был %d)%n",
                        client, placed, home);
            }
        }

        printSpots();
    }

    /** Линейное пробирование */
    private int place(int client) {
        int home = client % Data.SIZE;
        for (int step = 0; step < Data.SIZE; step++) {
            int spot = (home + step) % Data.SIZE;
            if (spots[spot] == null) {
                spots[spot] = client;
                return spot;
            }
        }
        return -1;
    }

    private void printSpots() {
        System.out.println("\nИтоговая парковка:");
        for (int i = 0; i < spots.length; i++) {
            System.out.printf("  [%d] = %s%n", i,
                    spots[i] == null ? "—" : spots[i].toString());
        }
    }
}
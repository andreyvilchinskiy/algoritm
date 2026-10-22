import java.util.ArrayList;
import java.util.List;

/**
 * ЗАДАНИЕ 2. Гардероб на 10 крючков.
 * Закрытая адресация — раздельное связывание.
 */
public class Wardrobe {

    /** Каждый крючок — цепочка клиентов. */
    private final List<List<Integer>> hooks = new ArrayList<>();

    public Wardrobe() {
        for (int i = 0; i < Data.SIZE; i++) {
            hooks.add(new ArrayList<>());
        }
    }

    public static void main(String[] args) {
        new Wardrobe().run();
    }

    public void run() {
        System.out.println("===== ЗАДАНИЕ 2. ГАРДЕРОБ (раздельное связывание) =====");

        for (int client : Data.CLIENTS) {
            int home = client % Data.SIZE;
            hooks.get(home).add(client);

            if (hooks.get(home).size() == 1) {
                System.out.printf("Клиент %d → крючок %d%n", client, home);
            } else {
                System.out.printf("Клиент %d → крючок %d (коллизия! в цепочке теперь %d вещей)%n",
                        client, home, hooks.get(home).size());
            }
        }

        printHooks();
    }

    private void printHooks() {
        System.out.println("\nИтоговый гардероб:");
        for (int i = 0; i < hooks.size(); i++) {
            System.out.printf("  Крючок %d: %s%n", i, hooks.get(i));
        }
    }
}
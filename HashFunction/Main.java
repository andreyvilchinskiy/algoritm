import java.util.HashMap;
import java.util.Map;

public class Main {

    public static int myHash(String word, int position) {
        int sum = 0;
        for (char c : word.toCharArray()) {
            sum += (int)c;
        }
        return sum * (position + 1);
    }

    public static void main(String[] args) {
        String[] words = {
                "дом", "кот", "лес", "мир", "свет",
                "река", "гора", "звезда", "ветер", "огонь"
        };

        Map<String, Integer> hashTable = new HashMap<>();

        System.out.println("Слово \t Хеш");
        System.out.println("-------------------------");

        for (int i = 0; i < words.length; i++) {
            int hash = myHash(words[i], i);
            hashTable.put(words[i], hash);
            System.out.println(words[i] + " "+"\t" + hash);
        }
        int[] A = {3, 1, 4, 1, 5};

        System.out.println(Duplicate.duplicate(A));
        System.out.println(Duplicate.duplicateSort(A));
        System.out.println(Duplicate.duplicateHash(A));
        System.out.println(Duplicate.duplicateDirect(A, 5));
    }
}
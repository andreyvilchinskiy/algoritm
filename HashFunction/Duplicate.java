import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Duplicate {

    public static boolean duplicate(int[] A) {
        for (int i = 0; i < A.length; i++)
            for (int j = i + 1; j < A.length; j++)
                if (A[i] == A[j]) return true;
        return false;
    }

    public static boolean duplicateSort(int[] A) {
        int[] B = Arrays.copyOf(A, A.length);
        Arrays.sort(B);
        for (int i = 1; i < B.length; i++)
            if (B[i] == B[i - 1]) return true;
        return false;
    }

    public static boolean duplicateHash(int[] A) {
        Set<Integer> seen = new HashSet<>();
        for (int x : A)
            if (!seen.add(x)) return true;
        return false;
    }

    public static boolean duplicateDirect(int[] A, int k) {
        boolean[] seen = new boolean[k + 1];
        for (int x : A) {
            if (seen[x]) return true;
            seen[x] = true;
        }
        return false;
    }
}
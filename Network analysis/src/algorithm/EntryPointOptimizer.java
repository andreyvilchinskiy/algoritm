package algorithm;

import model.Graph;

public class EntryPointOptimizer {

    public static class CandidateResult {
        public final int candidate;
        public final long totalDelay;
        public final long reductionAbsolute;
        public final double reductionPercent;

        public CandidateResult(int candidate, long totalDelay,
                               long reductionAbsolute, double reductionPercent) {
            this.candidate = candidate;
            this.totalDelay = totalDelay;
            this.reductionAbsolute = reductionAbsolute;
            this.reductionPercent = reductionPercent;
        }
    }


    public static CandidateResult[] evaluate(Graph graph, int[] existingEntries, int[] candidates) {
        long baseline = new NetworkAnalyzer(graph, existingEntries).totalDelay();

        CandidateResult[] results = new CandidateResult[candidates.length];
        for (int i = 0; i < candidates.length; i++) {
            int[] newEntries = new int[existingEntries.length + 1];
            System.arraycopy(existingEntries, 0, newEntries, 0, existingEntries.length);
            newEntries[existingEntries.length] = candidates[i];

            long total = new NetworkAnalyzer(graph, newEntries).totalDelay();
            long reduction = baseline - total;
            double percent = baseline == 0 ? 0.0 : 100.0 * reduction / baseline;
            results[i] = new CandidateResult(candidates[i], total, reduction, percent);
        }
        return results;
    }

    public static CandidateResult best(CandidateResult[] results) {
        CandidateResult best = results[0];
        for (CandidateResult r : results) {
            if (r.reductionAbsolute > best.reductionAbsolute) best = r;
        }
        return best;
    }
}
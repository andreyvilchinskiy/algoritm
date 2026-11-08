package io;

import model.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InputReader {

    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer st;

    private String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String line = br.readLine();
            if (line == null) return null;
            st = new StringTokenizer(line);
        }
        return st.nextToken();
    }

    private int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public static class ParsedInput {
        public Graph graph;
        public int[] entryPoints;
        public int start;
        public int[] required;
        public int[] candidates;
    }

    public ParsedInput read() throws IOException {
        ParsedInput in = new ParsedInput();

        int n = nextInt();
        int m = nextInt();
        in.graph = new Graph(n);
        for (int i = 0; i < m; i++) {
            int u = nextInt();
            int v = nextInt();
            long w = Long.parseLong(next());
            in.graph.addEdge(u, v, w);
        }

        int k = nextInt();
        in.entryPoints = new int[k];
        for (int i = 0; i < k; i++) in.entryPoints[i] = nextInt();

        in.start = nextInt();

        in.required = new int[3];
        for (int i = 0; i < 3; i++) in.required[i] = nextInt();

        in.candidates = new int[3];
        for (int i = 0; i < 3; i++) in.candidates[i] = nextInt();

        return in;
    }
}
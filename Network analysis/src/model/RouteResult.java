package model;

import java.util.List;

public class RouteResult {
    private final List<Integer> order;
    private final List<Integer> fullPath;
    private final long totalLength;

    public RouteResult(List<Integer> order, List<Integer> fullPath, long totalLength) {
        this.order = order;
        this.fullPath = fullPath;
        this.totalLength = totalLength;
    }

    public List<Integer> getOrder() {
        return order;
    }

    public List<Integer> getFullPath() {
        return fullPath;
    }

    public long getTotalLength() {
        return totalLength;
    }
}
package jvlopes.bicycle.fleet.application.dto;

import java.util.List;

public class PageResponse<T> {
    private final List<T> content;
    private final long totalElements;

    public PageResponse(List<T> content, long totalElements) {
        this.content = content;
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public long getTotalElements() {
        return totalElements;
    }
}

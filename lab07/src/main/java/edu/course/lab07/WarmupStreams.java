package edu.course.lab07;

import java.util.Comparator;
import java.util.List;

public final class WarmupStreams {

    private WarmupStreams() {
    }

    public static List<String> normalizeAndSort(List<String> source) {
        return source.stream()
                .filter(s -> s != null && !s.isEmpty())
                .map(String::toUpperCase)
                .sorted(Comparator.comparingInt(String::length)
                .thenComparing(Comparator.naturalOrder()))
                .toList();
    }
}
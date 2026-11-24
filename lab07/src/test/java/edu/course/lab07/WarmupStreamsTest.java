package edu.course.lab07;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WarmupStreamsTest {

    @Test
    void shouldFilterUpperCaseAndSortByLengthThenAlphabeticallyWithoutMutatingSource() {
        List<String> input = new ArrayList<>(List.of("java", "", "git", "maven", "api"));
        List<String> snapshot = List.copyOf(input);

        List<String> result = WarmupStreams.normalizeAndSort(input);

        assertEquals(List.of("API", "GIT", "JAVA", "MAVEN"), result);
        assertEquals(snapshot, input);
    }
}
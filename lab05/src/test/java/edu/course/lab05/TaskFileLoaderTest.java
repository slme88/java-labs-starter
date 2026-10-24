package edu.course.lab05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskFileLoaderTest {

    private static final String HEADER = "id;title;category;estimatedHours;status";

    @TempDir
    Path tempDir;

    private Path writeFile(String... lines) throws IOException {
        Path file = tempDir.resolve("tasks.csv");
        Files.write(file, List.of(lines), StandardCharsets.UTF_8);
        return file;
    }

    @Test
    void loadsCorrectFile() throws IOException {
        Path file = writeFile(
                HEADER,
                "T-101;Prepare release;backend;8;READY",
                "T-102;Write docs;docs;3;DONE"
        );

        LoadResult result = new TaskFileLoader().load(file);

        assertEquals(2, result.getTasks().size());
        assertEquals(0, result.getErrors().size());

        Task first = result.getTasks().get(0);
        assertEquals("T-101", first.getId());
        assertEquals("Prepare release", first.getTitle());
        assertEquals("backend", first.getCategory());
        assertEquals(8, first.getEstimatedHours());
        assertEquals(TaskStatus.READY, first.getStatus());
    }

    @Test
    void handlesCyrillic() throws IOException {
        Path file = writeFile(
                HEADER,
                "T-201;Подготовить релиз;бэкенд;5;IN_PROGRESS"
        );

        LoadResult result = new TaskFileLoader().load(file);

        assertEquals(1, result.getTasks().size());
        assertEquals(0, result.getErrors().size());
        assertEquals("Подготовить релиз", result.getTasks().get(0).getTitle());
        assertEquals("бэкенд", result.getTasks().get(0).getCategory());
    }

    @Test
    void reportsEmptyField() throws IOException {
        Path file = writeFile(HEADER, "T-301;;backend;4;READY");

        LoadResult result = new TaskFileLoader().load(file);

        assertEquals(0, result.getTasks().size());
        assertEquals(1, result.getErrors().size());

        ValidationError error = result.getErrors().get(0);
        assertEquals(2, error.getLineNumber());
        assertEquals("title", error.getField());
    }

    @Test
    void reportsWrongNumber() throws IOException {
        Path file = writeFile(HEADER, "T-401;Oops;backend;abc;READY");

        LoadResult result = new TaskFileLoader().load(file);

        assertEquals(0, result.getTasks().size());
        assertEquals(1, result.getErrors().size());
        assertEquals("estimatedHours", result.getErrors().get(0).getField());
    }

    @Test
    void reportsUnknownStatus() throws IOException {
        Path file = writeFile(HEADER, "T-501;Oops;backend;2;WHATEVER");

        LoadResult result = new TaskFileLoader().load(file);

        assertEquals(0, result.getTasks().size());
        assertEquals(1, result.getErrors().size());
        assertEquals("status", result.getErrors().get(0).getField());
    }

    @Test
    void reportsWrongColumnCount() throws IOException {
        Path file = writeFile(HEADER, "T-601;Only three;backend");

        LoadResult result = new TaskFileLoader().load(file);

        assertEquals(0, result.getTasks().size());
        assertEquals(1, result.getErrors().size());
        assertEquals(2, result.getErrors().get(0).getLineNumber());
    }

    @Test
    void mixesGoodAndBadLines() throws IOException {
        Path file = writeFile(
                HEADER,
                "T-701;Good one;backend;3;READY",
                "bad-line",
                "T-703;Another good;frontend;5;DONE",
                "T-704;Bad hours;backend;xyz;READY",
                ""
        );

        LoadResult result = new TaskFileLoader().load(file);

        assertEquals(2, result.getTasks().size());
        assertEquals(2, result.getErrors().size());

        assertEquals(3, result.getErrors().get(0).getLineNumber());
        assertEquals(5, result.getErrors().get(1).getLineNumber());
    }

    @Test
    void missingFileThrowsSourceError() {
        Path missing = tempDir.resolve("does-not-exist.csv");

        DataLoadingException ex = assertThrows(DataLoadingException.class,
                () -> new TaskFileLoader().load(missing));

        assertNotNull(ex.getCause(), "cause must be preserved");
        assertInstanceOf(IOException.class, ex.getCause());
    }

    @Test
    void badHeaderThrowsSourceError() throws IOException {
        Path file = writeFile("wrong;header;only", "T-1;x;y;1;READY");

        DataLoadingException ex = assertThrows(DataLoadingException.class,
                () -> new TaskFileLoader().load(file));

        assertTrue(ex.getMessage().contains("header"));
    }

    @Test
    void emptyFileThrowsSourceError() throws IOException {
        Path file = writeFile();

        assertThrows(DataLoadingException.class,
                () -> new TaskFileLoader().load(file));
    }
}
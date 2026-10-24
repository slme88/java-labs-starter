package edu.course.lab05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class TaskFileLoader {

    private static final String EXPECTED_HEADER = "id;title;category;estimatedHours;status";
    private static final int FIELD_COUNT = 5;

    public LoadResult load(Path path) {
        List<String> lines;
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DataLoadingException("Cannot read " + path, e);
        }

        if (lines.isEmpty()) {
            throw new DataLoadingException("File is empty: " + path);
        }

        String header = lines.get(0).trim();
        if (!EXPECTED_HEADER.equals(header)) {
            throw new DataLoadingException(
                    "Unexpected header in " + path + ": '" + header + "'");
        }

        List<Task> tasks = new ArrayList<>();
        List<ValidationError> errors = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String raw = lines.get(i);
            if (raw.isBlank()) {
                continue;
            }
            int lineNumber = i + 1; 
            parseLine(raw, lineNumber, tasks, errors);
        }

        return new LoadResult(tasks, errors);
    }

    private void parseLine(String line,
                           int lineNumber,
                           List<Task> tasks,
                           List<ValidationError> errors) {

        String[] parts = line.split(";", -1);
        if (parts.length != FIELD_COUNT) {
            errors.add(new ValidationError(lineNumber, "line",
                    "Expected " + FIELD_COUNT + " fields, got " + parts.length));
            return;
        }

        List<ValidationError> rowErrors = new ArrayList<>();

        String id = parts[0].trim();
        if (id.isEmpty()) {
            rowErrors.add(new ValidationError(lineNumber, "id", "id is blank"));
        }

        String title = parts[1].trim();
        if (title.isEmpty()) {
            rowErrors.add(new ValidationError(lineNumber, "title", "title is blank"));
        }

        String category = parts[2].trim();
        if (category.isEmpty()) {
            rowErrors.add(new ValidationError(lineNumber, "category", "category is blank"));
        }

        int estimatedHours = -1;
        String hoursRaw = parts[3].trim();
        if (hoursRaw.isEmpty()) {
            rowErrors.add(new ValidationError(lineNumber, "estimatedHours",
                    "estimatedHours is blank"));
        } else {
            try {
                estimatedHours = Integer.parseInt(hoursRaw);
                if (estimatedHours <= 0) {
                    rowErrors.add(new ValidationError(lineNumber, "estimatedHours",
                            "estimatedHours must be positive: " + hoursRaw));
                }
            } catch (NumberFormatException e) {
                rowErrors.add(new ValidationError(lineNumber, "estimatedHours",
                        "estimatedHours is not a number: " + hoursRaw));
            }
        }

        TaskStatus status = null;
        String statusRaw = parts[4].trim();
        if (statusRaw.isEmpty()) {
            rowErrors.add(new ValidationError(lineNumber, "status", "status is blank"));
        } else {
            try {
                status = TaskStatus.valueOf(statusRaw.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException e) {
                rowErrors.add(new ValidationError(lineNumber, "status",
                        "Unknown status: " + statusRaw));
            }
        }

        if (!rowErrors.isEmpty()) {
            errors.addAll(rowErrors);
            return;
        }

        tasks.add(new Task(id, title, category, estimatedHours, status));
    }
}
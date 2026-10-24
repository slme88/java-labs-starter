package edu.course.lab05;

import java.util.Objects;

public final class ValidationError {

    private final int lineNumber;
    private final String field;
    private final String message;

    public ValidationError(int lineNumber, String field, String message) {
        this.lineNumber = lineNumber;
        this.field = field;
        this.message = message;
    }

    public int getLineNumber() { return lineNumber; }
    public String getField() { return field; }
    public String getMessage() { return message; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidationError other)) return false;
        return lineNumber == other.lineNumber
                && Objects.equals(field, other.field)
                && Objects.equals(message, other.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineNumber, field, message);
    }

    @Override
    public String toString() {
        return "ValidationError{line=" + lineNumber + ", field='" + field
                + "', message='" + message + "'}";
    }
}
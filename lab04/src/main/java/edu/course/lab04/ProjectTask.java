package edu.course.lab04;

import java.util.Objects;

public final class ProjectTask {

    private final String title;
    private final String assignee;
    private final boolean done;

    public ProjectTask(String title, String assignee, boolean done) {
        this.title    = Objects.requireNonNull(title, "title");
        this.assignee = Objects.requireNonNull(assignee, "assignee");
        this.done     = done;
    }

    public String getTitle()    { return title; }
    public String getAssignee() { return assignee; }
    public boolean isDone()     { return done; }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectTask)) {
            return false;
        }
        ProjectTask other = (ProjectTask) o;
        return done == other.done
                && title.equals(other.title)
                && assignee.equals(other.assignee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, assignee, done);
    }

    @Override
    public String toString() {
        return "ProjectTask{title='" + title + "', assignee='" + assignee + "', done=" + done + "}";
    }
}
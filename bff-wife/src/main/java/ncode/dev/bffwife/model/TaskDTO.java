package ncode.dev.bffwife.model;

import java.time.LocalDate;

public class TaskDTO {
    private String name;
    private String description;
    private PriorityEnum priority;
    private LocalDate dueDate;
    private LocalDate scheduled;
    private boolean done;

    public TaskDTO() {}

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public PriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(final PriorityEnum priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(final LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getScheduled() {
        return scheduled;
    }

    public void setScheduled(final LocalDate scheduled) {
        this.scheduled = scheduled;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(final boolean done) {
        this.done = done;
    }
}

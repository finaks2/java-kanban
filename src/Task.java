import java.util.Objects;

public class Task {

    private final String name;
    private final String description;
    private final Integer ID;
    protected TaskStatus status;

    public Task(String name, String description, TaskStatus status, Integer ID) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getID() {
        return ID;
    }

    public TaskStatus getStatus() {
        return status;
    }


    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(ID, task.ID);
    }

    @Override
    public String toString() {
        return this.getClass().getName() +
                "{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ID=" + ID +
                ", status=" + status +
                '}';
    }
}

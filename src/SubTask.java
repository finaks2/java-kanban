public class SubTask extends Task {
    public final Epic epic;
    public SubTask(String name, String description, TaskStatus status, Epic epic) {
        super(name, description, status);
        this.epic = epic;
    }

}

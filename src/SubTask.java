public class SubTask extends Task {
    Epic epic;
    public SubTask(String name, String description, TaskStatus status, Integer id, Epic epic) {
        super(name, description, status, id);
        this.epic = epic;
    }

}

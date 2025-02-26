public class SubTask extends Task {
    private final Epic epic;

    public SubTask(String name, String description, TaskStatus status, Epic epic) {
        super(name, description, status);
        this.epic = epic;
    }

    public Epic getEpic() {
        return epic;
    }
}

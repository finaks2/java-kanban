import java.util.ArrayList;

public class Epic extends Task {

    ArrayList<SubTask> subTasks;

    public Epic(String name, String description, TaskStatus status, Integer id) {
        super(name, description, status, id);
        this.subTasks = new ArrayList<>();
    }

    public void recalculateStatus() {

        int doneCount = 0;
        int newCount = 0;

        for (SubTask subTask : subTasks) {
            if (subTask.getStatus().equals(TaskStatus.NEW)) {
                newCount++;
            }
            if (subTask.getStatus().equals(TaskStatus.DONE)) {
                doneCount++;
            }
        }

        if (doneCount == subTasks.size()) {
            this.status = TaskStatus.DONE;
        } else if (newCount == subTasks.size()) {
            this.status = TaskStatus.NEW;
        } else {
            this.status = TaskStatus.IN_PROGRESS;
        }
    }
}

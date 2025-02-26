import java.util.ArrayList;

public class Epic extends Task {

    private final ArrayList<SubTask> subTasks;

    public Epic(String name, String description) {
        super(name, description, TaskStatus.NEW);
        this.subTasks = new ArrayList<>();
    }

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    public void addSubTask(SubTask subTask) {
        subTasks.add(subTask);
    }

    public void removeSubTask(SubTask subTask) {
        subTasks.remove(subTask);
    }

    public void recalculateStatus() {

        int doneCount = 0;
        int newCount = 0;

        for (SubTask subTask : subTasks) {
            if (subTask.getStatus().equals(TaskStatus.NEW)) {
                newCount++;
            } else if (subTask.getStatus().equals(TaskStatus.DONE)) {
                doneCount++;
            } else if (subTask.getStatus().equals(TaskStatus.IN_PROGRESS)) {
                this.status = TaskStatus.IN_PROGRESS;
                return;
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

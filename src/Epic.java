import java.util.ArrayList;

public class Epic extends Task {
    public Epic(String name, String description, TaskStatus status, Integer ID) {
        super(name, description, status, ID);
    }

    public void recalculateStatus(TaskManager manager) {
        int taskStatus = 0;

        ArrayList<Task> subTasks = manager.getSubTasksByEpic(this);
        for (Task task: subTasks) {
            if (task.getStatus().equals(TaskStatus.IN_PROGRESS)) {
                taskStatus++;
            }
            if (task.getStatus().equals(TaskStatus.DONE)) {
                taskStatus += 10;
            }
        }

        status = TaskStatus.NEW;
        if (taskStatus == subTasks.size() * 10) {
            status = TaskStatus.DONE;
        } else if (taskStatus > 0 && taskStatus < subTasks.size() * 10) {
            status = TaskStatus.IN_PROGRESS;
        }
    }
}

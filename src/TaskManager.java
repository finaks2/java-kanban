import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, Epic> epics;
    private int taskNumber = 0;

    public TaskManager() {
        this.epics = new HashMap<>();
        this.tasks = new HashMap<>();
    }

    public Integer getNewTaskId() {
        return taskNumber++;
    }

    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void addTask(SubTask subTask, Epic epic) {
        Epic existingEpic = epics.get(epic.getId());

        if (existingEpic == null) {
            epics.put(epic.getId(), epic);
            existingEpic = epic;
        }
        existingEpic.subTasks.add(subTask);
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public void removeTask(Task task) {

        if (task.getClass() == Epic.class) {
            epics.remove(task.getId());
        } else if (task.getClass() == SubTask.class) {
            ((SubTask) task).epic.subTasks.remove(task);
            ((SubTask) task).epic.recalculateStatus();
        } else {
            tasks.remove(task.getId());
        }
    }

    public void updateTask(Task task) {
        if (task.getClass() == Task.class) {
            if (tasks.containsKey(task.getId())) {
                tasks.put(task.getId(), task);
            }
        } else if (task.getClass() == SubTask.class) {
            ArrayList<SubTask> subTasks = ((SubTask) task).epic.subTasks;
            for (int i = 0; i < subTasks.size(); i++) {
                if (subTasks.get(i).equals(task)) {
                    subTasks.set(i, (SubTask) task);
                    ((SubTask) task).epic.recalculateStatus();
                    break;
                }
            }
        } else {
            Epic existingEpic = epics.get(task.getId());
            if (existingEpic != null) {
                ((Epic) task).subTasks.addAll(existingEpic.subTasks);
                epics.put(task.getId(), (Epic) task);
                ((Epic) task).recalculateStatus();
            }
        }
    }

    public void printAllTasks() {
        System.out.println("====== Все задачи ======");
        for (Integer i : tasks.keySet()) {
            System.out.println(tasks.get(i));
        }
        for (Epic epic : epics.values()) {
            System.out.println(epic);
            for (SubTask subTask : epic.subTasks) {
                System.out.println(subTask);
            }
        }
    }

    public void clearTasks() {
        tasks.clear();
        epics.clear();
    }

}

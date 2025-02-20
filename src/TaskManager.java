import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, Integer> taskTree;
    private final HashMap<Integer, Boolean> busyIDs;

    public TaskManager() {
        busyIDs = new HashMap<>();
        tasks = new HashMap<>();
        taskTree = new HashMap<>();
    }

    public Integer getNewTaskID() {
        Integer newID;
        for (Integer i : busyIDs.keySet()) {
            if (!busyIDs.get(i)) {
                newID = i;
                busyIDs.put(i, true);
                return newID;
            }
        }

        newID = busyIDs.size();
        busyIDs.put(newID, true);

        return newID;
    }

    public void addTask(Task task) {
        tasks.put(task.getID(), task);
    }

    public void addTask(SubTask subTask, Epic epic) {
        this.addTask(subTask);
        this.addTask(epic);
        taskTree.put(subTask.getID(), epic.getID());
        epic.recalculateStatus(this);
    }

    public Task getTaskByID(int id) {
        return tasks.get(id);
    }

    public ArrayList<Task> getSubTasksByEpic(Epic epic) {
        ArrayList<Task> subTasks = new ArrayList<>();

        for (Integer taskID : taskTree.keySet()) {
            Epic thisEpic = (Epic) tasks.get(taskTree.get(taskID));
            if (thisEpic.equals(epic)) {
                subTasks.add(tasks.get(taskID));
            }
        }
        return subTasks;
    }

    public void removeTask(Task task) {

        ArrayList<Task> tasksToRemove = new ArrayList<>();
        Epic epicToRecalculate = null;

        tasksToRemove.add(task);

        if (task.getClass() == Epic.class) {
            ArrayList<Task> subTasks = getSubTasksByEpic((Epic) task);
            tasksToRemove.addAll(subTasks);
        }

        if (task.getClass() == SubTask.class) {
            epicToRecalculate = (Epic) tasks.get(taskTree.get(task.getID()));
        }

        for (Task taskToRemove : tasksToRemove) {
            tasks.remove(taskToRemove.getID());
            taskTree.remove(taskToRemove.getID());
            busyIDs.put(taskToRemove.getID(), false);
        }

        if (epicToRecalculate != null) {
            epicToRecalculate.recalculateStatus(this);
        }
    }

    public void updateTask(Task task) {
        tasks.put(task.getID(), task);
        if (task.getClass() == SubTask.class) {
            Epic epicToRecalculate = (Epic) tasks.get(taskTree.get(task.getID()));
            epicToRecalculate.recalculateStatus(this);
        }
    }

    public void printAllTasks() {
        System.out.println("====== Все задачи ======");
        for (Integer i : tasks.keySet()) {
            System.out.println(tasks.get(i));
        }
    }

    public void clearTasks() {
        tasks.clear();
        busyIDs.clear();
        taskTree.clear();
    }

}

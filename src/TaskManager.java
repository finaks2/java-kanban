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
        Integer newId = getNewTaskId();
        task.setId(newId);
        tasks.put(newId, task);
    }

    public void addEpic(Epic epic) {
        Integer newId = getNewTaskId();
        epic.setId(newId);
        epics.put(newId, epic);
    }

    public void addSubTask(SubTask subTask, Epic epic) {
        int newId = getNewTaskId();
        subTask.setId(newId);
        epic.subTasks.add(subTask);
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public SubTask getSubTaskById(int id) {
        SubTask result = null;

        for (Epic epic : epics.values()) {
            for (SubTask subTask : epic.subTasks) {
                if (subTask.getId() == id) {
                    result = subTask;
                    break;
                }
            }
            if (result != null) {
                break;
            }
        }
        return result;
    }

    public void removeTask(Task task) {

        if (task.getClass() == Epic.class) {
            epics.remove(task.getId());
        } else if (task.getClass() == SubTask.class) {
            ((SubTask) task).epic.subTasks.remove((SubTask) task);
            ((SubTask) task).epic.recalculateStatus();
        } else {
            tasks.remove(task.getId());
        }
    }

    public void updateTask(int id, Task task) {
        task.setId(id);

        if (task.getClass() == Task.class) {
            if (tasks.containsKey(id)) {
                tasks.put(id, task);
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
            Epic existingEpic = epics.get(id);
            if (existingEpic != null) {
                ((Epic) task).subTasks.addAll(existingEpic.subTasks);
                epics.put(id, (Epic) task);
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
    }

    public void clearEpics() {
        epics.clear();
    }

    public void clearSubTasks() {
        for (Epic epic : epics.values()) {
            epic.subTasks.clear();
        }
    }
}

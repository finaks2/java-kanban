import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private final HashMap<Integer, Task> tasks;
    private final HashMap<Integer, Epic> epics;
    private final HashMap<Integer, SubTask> subTasks;
    private int taskNumber = 0;

    public TaskManager() {
        this.epics = new HashMap<>();
        this.tasks = new HashMap<>();
        this.subTasks = new HashMap<>();
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

    public void addSubTask(SubTask subTask) {
        int newId = getNewTaskId();
        subTask.setId(newId);
        subTasks.put(newId, subTask);
        subTask.getEpic().addSubTask(subTask);
        subTask.getEpic().recalculateStatus();
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public SubTask getSubTaskById(int id) {
        return subTasks.get(id);
    }

    public ArrayList<SubTask> getSubTasksByEpicId(Integer epicId) {
        Epic epic = getEpicById(epicId);

        if (epic == null) {
            System.out.println("Указан идентификатор несуществующего эпика");
            return new ArrayList<>();
        } else {
            return epic.getSubTasks();
        }
    }

    public void removeTask(Task task) {

        if (task.getClass() == Epic.class) {
            epics.remove(task.getId());
        } else if (task.getClass() == SubTask.class) {
            subTasks.remove(task.getId());
            ((SubTask) task).getEpic().removeSubTask((SubTask) task);
            ((SubTask) task).getEpic().recalculateStatus();
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
            SubTask existingSubTask = subTasks.get(task.getId());

            if (existingSubTask == null) {
                System.out.println("Не найдена подзадача с идентификатором '" + task.getId() + "'");
                return;
            }

            subTasks.put(existingSubTask.getId(), (SubTask) task);
            Epic epic = existingSubTask.getEpic();
            ArrayList<SubTask> epicSubTasks = epic.getSubTasks();
            epicSubTasks.set(epicSubTasks.indexOf(existingSubTask), (SubTask) task);
            epic.recalculateStatus();

        } else {
            Epic existingEpic = epics.get(task.getId());
            if (existingEpic != null) {
                for (SubTask subTask : existingEpic.getSubTasks()) {
                    ((Epic) task).addSubTask(subTask);
                }
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
            for (SubTask subTask : epic.getSubTasks()) {
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
        subTasks.clear();
        for (Epic epic : epics.values()) {
            epic.getSubTasks().clear();
            epic.recalculateStatus();
        }
    }
}

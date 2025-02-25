public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Собрать яблоки",
                "Собрать яблоки в саду, сложить в коробки.",
                TaskStatus.NEW
        );
        taskManager.addTask(task1);

        Task task2 = new Task("Отнести яблоки",
                "Коробки с яблоками отнести в кладовую.",
                TaskStatus.NEW
        );
        taskManager.addTask(task2);

        Epic epic1 = new Epic("Отвезти маму в магазин",
                "Помочь маме с покупками после работы."
        );
        taskManager.addEpic(epic1);

        SubTask subTask1 = new SubTask("Заправить авто",
                "Проверить сколько бензина в авто, при необходимости заправить",
                TaskStatus.NEW,
                epic1
        );
        taskManager.addSubTask(subTask1, epic1);

        SubTask subTask2 = new SubTask("Отвезти маму",
                "Забрать маму с работы и отвезти в магазин",
                TaskStatus.NEW,
                epic1
        );
        taskManager.addSubTask(subTask2, epic1);

        Epic epic2 = new Epic("Спланировать отпуск",
                "Составить план отличного отдыха"
        );
        taskManager.addEpic(epic2);

        SubTask subTask3 = new SubTask("Выбрать место",
                "Составить список мест для отдыха и выбрать самые лучшие",
                TaskStatus.NEW,
                epic2
        );
        taskManager.addSubTask(subTask3, epic2);

        taskManager.printAllTasks();

        Task taskById = taskManager.getTaskById(task1.getId());
        System.out.println("Задача с id '" + taskById.getId() + "' = '" + taskById + "'");

        Epic epicById = taskManager.getEpicById(epic1.getId());
        System.out.println("Эпик с id '" + epicById.getId() + "' = '" + epicById + "'");

        SubTask subTaskById = taskManager.getSubTaskById(subTask2.getId());
        System.out.println("Подзадача с id '" + subTaskById.getId() + "' = '" + subTaskById + "'");

        System.out.println("====== Изменяем состояние подзадачи '" + subTask1.getName() + "' эпика '" + epic1.getName() + "' ======");
        SubTask subTask4 = new SubTask("Заправить авто",
                "Проверить сколько бензина в авто, при необходимости заправить",
                TaskStatus.DONE,
                epic1
        );
        taskManager.updateTask(subTask1.getId(), subTask4);

        System.out.println("Новое состояние эпика: " + epic1);
        System.out.println("====== Удаляем полностью эпик '" + epic1.getName() + "' ======");
        taskManager.removeTask(epic1);
        taskManager.printAllTasks();

        System.out.println("====== Удаляем все задачи ======");
        taskManager.clearTasks();
        taskManager.printAllTasks();

        System.out.println("====== Удаляем все подзадачи ======");
        taskManager.clearSubTasks();
        taskManager.printAllTasks();

        System.out.println("====== Удаляем все эпики ======");
        taskManager.clearEpics();
        taskManager.printAllTasks();
    }
}

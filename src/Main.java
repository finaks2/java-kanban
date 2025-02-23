public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Собрать яблоки",
                "Собрать яблоки в саду, сложить в коробки.",
                TaskStatus.NEW,
                taskManager.getNewTaskId()
        );

        Task task2 = new Task("Отнести яблоки",
                "Коробки с яблоками отнести в кладовую.",
                TaskStatus.NEW,
                taskManager.getNewTaskId()
        );
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        Epic epic1 = new Epic("Отвезти маму в магазин",
                "Помочь маме с покупками после работы.",
                TaskStatus.NEW,
                taskManager.getNewTaskId()
        );
        SubTask subTask1 = new SubTask("Заправить авто",
                "Проверить сколько бензина в авто, при необходимости заправить",
                TaskStatus.NEW,
                taskManager.getNewTaskId(),
                epic1
        );
        SubTask subTask2 = new SubTask("Отвезти маму",
                "Забрать маму с работы и отвезти в магазин",
                TaskStatus.NEW,
                taskManager.getNewTaskId(),
                epic1
        );
        taskManager.addTask(subTask1, epic1);
        taskManager.addTask(subTask2, epic1);

        Epic epic2 = new Epic("Спланировать отпуск",
                "Составить план отличного отдыха",
                TaskStatus.NEW,
                taskManager.getNewTaskId()
        );
        SubTask subTask3 = new SubTask("Выбрать место",
                "Составить список мест для отдыха и выбрать самые лучшие",
                TaskStatus.NEW,
                taskManager.getNewTaskId(),
                epic2
        );
        taskManager.addTask(subTask3, epic2);

        taskManager.printAllTasks();

        System.out.println("====== Изменяем состояние подзадачи '" + subTask1.getName() + "' эпика '" + epic1.getName() + "' ======");
        SubTask subTask4 = new SubTask("Заправить авто",
                "Проверить сколько бензина в авто, при необходимости заправить",
                TaskStatus.DONE,
                subTask1.getId(),
                epic1
        );
        taskManager.updateTask(subTask4);

        System.out.println("Новое состояние эпика: " + epic1);
        System.out.println("====== Удаляем полностью эпик '" + epic1.getName() + "' ======");
        taskManager.removeTask(epic1);
        taskManager.printAllTasks();

        System.out.println("====== Удаляем все задачи ======");
        taskManager.clearTasks();
        taskManager.printAllTasks();
    }
}

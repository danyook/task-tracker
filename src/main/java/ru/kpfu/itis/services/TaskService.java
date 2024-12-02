package ru.kpfu.itis.services;

import ru.kpfu.itis.dao.TaskDAO;
import ru.kpfu.itis.entities.Task;

import java.util.List;

public class TaskService {
    private static TaskService INSTANCE;
    private final TaskDAO taskDAO = TaskDAO.getInstance();

    private TaskService() {
        ;
    }

    public static TaskService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TaskService();
        }
        return INSTANCE;
    }

    public List<Task> findAll() {
        return taskDAO.findAll();
    }

    public Task findOne(int id) {
        return taskDAO.findById(id);
    }

    public List<Task> findBySectionId(int id) {
        return taskDAO.findBySectionId(id);
    }

    public void save(Task task) {
        taskDAO.save(task);
    }

    public void update(int id, Task updatedTask) {
        taskDAO.update(id, updatedTask);
    }

    public void delete(int id) {
        taskDAO.delete(id);
    }

    public void setDone(int id) {
        taskDAO.setDone(id);
    }

    public void setNotDone(int id) {
        taskDAO.setNotDone(id);
    }
}

package ru.kpfu.itis.services;

import ru.kpfu.itis.dao.TaskDAO;
import ru.kpfu.itis.entities.Task;

import java.util.Date;
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

    public List<Task> findNotDoneTasksBySectionId(int sectionId) {
        return taskDAO.findNotDoneTasksBySectionId(sectionId);
    }

    public List<Task> findDoneTasks(int personId) {
        return taskDAO.findDoneTasks(personId);
    }

    public void save(Task task) {
        taskDAO.saveToTask(task);
    }

    public void update(int id, Task updatedTask) {
        taskDAO.update(id, updatedTask);
    }

    public void delete(int id) {
        taskDAO.delete(id);
    }

    public void setDone(Task doneTask, Date date, int userId) {
        taskDAO.setDone(doneTask.getId());
        taskDAO.saveToArchive(doneTask, date, userId);
    }

    public void setNotDone(int id) {
        taskDAO.setNotDone(id);
        taskDAO.deleteFromArchive(id);
    }
}

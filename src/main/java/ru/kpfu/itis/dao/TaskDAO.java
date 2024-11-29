package ru.kpfu.itis.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.kpfu.itis.dao.mappers.TaskMapper;
import ru.kpfu.itis.entities.Task;
import ru.kpfu.itis.entities.enums.TaskStatus;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;
import ru.kpfu.itis.util.JdbcTemplateProvider;

import java.util.List;

public class TaskDAO {
    private static TaskDAO INSTANCE;
    private ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
    private JdbcTemplate jdbcTemplate = JdbcTemplateProvider.getJdbcTemplate();

    private TaskDAO() {
    }

    public static TaskDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TaskDAO();
        }
        return INSTANCE;
    }


    public List<Task> findAll() {
        return jdbcTemplate.query("SELECT * FROM Task", new TaskMapper());
    }

    public Task findById(int id) {
        return jdbcTemplate.query("SELECT * FROM Task WHERE id=?", new TaskMapper(), id).
                stream().findAny().orElse(null);
    }

    public List<Task> findBySectionId(int section_id) {
        return jdbcTemplate.query("SELECT * FROM Task WHERE section_id=?", new TaskMapper(), section_id);
    }

    public void save(Task task) {
        jdbcTemplate.update("INSERT INTO Task(name, description, status, date_of_add, section_id) VALUES(?, ?, ?, ?, ?)",
                task.getName(), task.getDescription(), task.getTaskStatus().name(),
                task.getDateOfAdd(), task.getSection().getId());

    }

    public void update(int id, Task updatedTask) {
        jdbcTemplate.update("UPDATE Task SET name=?, description=?, section_id=? WHERE id=?",
                updatedTask.getName(), updatedTask.getDescription(),
                updatedTask.getSection().getId(), id);
    }

    public void setDone(int id) {
        jdbcTemplate.update("UPDATE Task SET status=? WHERE id=?",
                TaskStatus.DONE.name(), id);
    }

    public void setNotDone(int id) {
        jdbcTemplate.update("UPDATE Task SET status=? WHERE id=?",
                TaskStatus.NOTDONE.name(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Task WHERE id=?", id);
    }
}

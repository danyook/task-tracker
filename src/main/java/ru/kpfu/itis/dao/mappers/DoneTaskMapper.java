package ru.kpfu.itis.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kpfu.itis.entities.Task;
import ru.kpfu.itis.entities.enums.TaskStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DoneTaskMapper implements RowMapper<Task> {
    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task task = new Task();

        task.setId(rs.getInt("id"));
        task.setName(rs.getString("name"));
        task.setDateOfAdd(rs.getTimestamp("date_of_done"));

        return task;

    }
}

package ru.kpfu.itis.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kpfu.itis.dao.UserDAO;
import ru.kpfu.itis.entities.Team;
import ru.kpfu.itis.services.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamMapper implements RowMapper<Team> {

    private final UserDAO userDAO;

    public TeamMapper(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
        Team team = new Team();

        team.setId(rs.getInt("id"));
        team.setName(rs.getString("name"));
        team.setOwner(userDAO.findById(rs.getInt("owner_id")));

        return team;
    }
}

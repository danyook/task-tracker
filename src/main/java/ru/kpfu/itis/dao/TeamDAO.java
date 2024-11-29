package ru.kpfu.itis.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.kpfu.itis.dao.mappers.TeamMapper;
import ru.kpfu.itis.entities.Team;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.DbException;
import ru.kpfu.itis.util.JdbcTemplateProvider;

import java.util.List;

public class TeamDAO {
    private static TeamDAO INSTANCE;
    private ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
    private JdbcTemplate jdbcTemplate = JdbcTemplateProvider.getJdbcTemplate();
    private UserDAO userDAO = UserDAO.getInstance();

    private TeamDAO() {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static TeamDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TeamDAO();
        }
        return INSTANCE;
    }


    public List<Team> findAll() {
        return jdbcTemplate.query("SELECT * FROM Team", new TeamMapper(userDAO));
    }

    public Team findById(int id) {
        return jdbcTemplate.query("SELECT * FROM Team WHERE id=?", new TeamMapper(userDAO), id).
                stream().findAny().orElse(null);
    }

    public List<Team> findByPersonId(int personId) {
        return jdbcTemplate.query
                ("SELECT * FROM Team JOIN person_team " +
                        "ON Team.id = person_team.person_id " +
                        "and person_team.person_id = ?", new TeamMapper(userDAO), personId);
    }

    public void save(Team team) {
        jdbcTemplate.update("INSERT INTO Team(name) VALUES(?)",
                team.getName());
    }

    public void update(int id, Team updatedTeam) {
        jdbcTemplate.update("UPDATE Team SET name=? WHERE id=?",
                updatedTeam.getName(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Team WHERE id=?", id);
    }

    public void addPersonToTeam(int personId, int teamId) {
        jdbcTemplate.update("INSERT INTO person_team(person_id, team_id) values(?,?)", personId, teamId);
    }

}

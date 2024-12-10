package ru.kpfu.itis.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.kpfu.itis.dao.mappers.TeamMapper;
import ru.kpfu.itis.entities.Team;
import ru.kpfu.itis.util.ConnectionProvider;
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
                        "ON Team.id = person_team.team_id " +
                        "and person_team.person_id = ?", new TeamMapper(userDAO), personId);
    }

    public int save(Team team) {
//        jdbcTemplate.update("INSERT INTO Team(name, owner_id) VALUES(?,?)",
//                team.getName(), team.getOwner().getId());
//        return team;

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Team")
                .usingGeneratedKeyColumns("id"); // Укажите имя столбца сгенерированного ключа

        // Подготовка параметров
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", team.getName());
        parameters.addValue("owner_id", team.getOwner().getId());

        // Выполнение вставки и получение сгенерированного ключа
        return jdbcInsert.executeAndReturnKey(parameters).intValue();
    }

    public void update(int id, Team updatedTeam) {
        jdbcTemplate.update("UPDATE Team SET name=?, owner_id=? WHERE id=?",
                updatedTeam.getName(), updatedTeam.getOwner().getId(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Team WHERE id=?", id);
    }

    public void addPersonToTeam(int personId, int teamId) {
        jdbcTemplate.update("INSERT INTO person_team(person_id, team_id) values(?,?)", personId, teamId);
    }

}

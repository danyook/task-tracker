package ru.kpfu.itis.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.kpfu.itis.dao.mappers.UserMapper;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.JdbcTemplateProvider;
import ru.kpfu.itis.util.PassEncrypt;

import java.util.List;

public class UserDAO {
    private static UserDAO INSTANCE;
    private ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
    private JdbcTemplate jdbcTemplate = JdbcTemplateProvider.getJdbcTemplate();

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserDAO();
        }
        return INSTANCE;
    }


    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM Person", new UserMapper());
    }

    public User findById(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new UserMapper(), id)
                .stream().findAny().orElse(null);
    }

    public List<User> findByTeamId(int teamId) {
        return jdbcTemplate.query
                ("""
                        SELECT * FROM Person JOIN person_team
                        ON Person.id = person_team.person_id 
                        and person_team.team_id = ?""", new UserMapper(), teamId);
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO Person(username, name, surname, password) VALUES(?, ?, ?, ?)",
                user.getUsername(), user.getName(), user.getSurname(), user.getPassword());
    }

    public void update(int id, User updatedUser) {
        System.out.println(updatedUser);
        jdbcTemplate.update("UPDATE Person SET username=?, name=?, surname=? WHERE id=?",
                updatedUser.getUsername(), updatedUser.getName(), updatedUser.getSurname(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public User findByUsername(String username) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE username=?", new UserMapper(), username)
                .stream().findAny().orElse(null);
    }

    public void updatePassword(int id, String pass) {
        jdbcTemplate.update("UPDATE Person SET password=? WHERE id=?",
                PassEncrypt.encrypt(pass), id);
    }

}

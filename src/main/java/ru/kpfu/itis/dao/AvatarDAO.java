package ru.kpfu.itis.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.kpfu.itis.dao.mappers.AvatarMapper;
import ru.kpfu.itis.dao.mappers.SectionMapper;
import ru.kpfu.itis.entities.Avatar;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.JdbcTemplateProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AvatarDAO {

    private static AvatarDAO INSTANCE;
    private ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
    private JdbcTemplate jdbcTemplate = JdbcTemplateProvider.getJdbcTemplate();
    private AvatarMapper mapper = new AvatarMapper();

    private AvatarDAO() {

    }

    public static AvatarDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AvatarDAO();
        }
        return INSTANCE;
    }

    public String findUrlByUserId(int userId) {
        Avatar avatar = jdbcTemplate.query("SELECT * FROM avatars WHERE user_id = ?", mapper, userId).stream().findAny().orElse(null);
        if (avatar == null) {
            return null;
        } else {
            return avatar.getUrl();
        }
    }

    public void save(int userId, String url) {
        jdbcTemplate.update("INSERT INTO avatars (user_id, url) values (?, ?)", userId, url);
    }

    public void update(int userId, String url) {
        jdbcTemplate.update("UPDATE avatars SET url = ? WHERE user_id = ?", url, userId);
    }

    public void delete(int userId) {
        jdbcTemplate.update("DELETE FROM avatars WHERE user_id = ?", userId);
    }
}

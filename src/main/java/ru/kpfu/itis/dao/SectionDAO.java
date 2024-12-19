package ru.kpfu.itis.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.kpfu.itis.dao.mappers.SectionMapper;
import ru.kpfu.itis.entities.Section;
import ru.kpfu.itis.entities.enums.SectionRole;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.util.JdbcTemplateProvider;

import java.util.List;

public class SectionDAO {
    private static SectionDAO INSTANCE;
    private ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
    private JdbcTemplate jdbcTemplate = JdbcTemplateProvider.getJdbcTemplate();
    private SectionMapper mapper = new SectionMapper();

    private SectionDAO() {

    }

    public static SectionDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SectionDAO();
        }
        return INSTANCE;
    }


    public List<Section> findAll() {
        return jdbcTemplate.query("SELECT * FROM Section", mapper);
    }

    public List<Section> findByUserId(int userId) {
        return jdbcTemplate.query("SELECT * FROM Section WHERE person_id=? AND role=?",
                mapper, userId, SectionRole.SOLO.name());
    }

    public List<Section> findByTeamId(int teamId) {
        return jdbcTemplate.query("SELECT * FROM Section WHERE team_id=? AND role=?",
                mapper, teamId, SectionRole.TEAM.name());
    }

    public Section findById(int id) {
        return jdbcTemplate.query("SELECT * FROM Section WHERE id=?", mapper, id).
                stream().findAny().orElse(null);
    }

    public void save(Section section) {
        System.out.println(section);
        if (section.getRole().equals(SectionRole.SOLO) && section.getUser() != null) {
            jdbcTemplate.update("INSERT INTO Section(name, type, role, person_id) VALUES(?, ?, ?, ?)",
                    section.getName(), section.getType().name(), section.getRole().name(), section.getUser().getId());
        } else if (section.getRole().equals(SectionRole.TEAM) && section.getTeam() != null) {
            jdbcTemplate.update("INSERT INTO Section(name, type, role, team_id) VALUES(?, ?, ?, ?)",
                    section.getName(), section.getType().name(), section.getRole().name(), section.getTeam().getId());
        } else {
            System.out.println("Section role exception");
        }

    }

    public void update(int id, Section updatedSection) {
        jdbcTemplate.update("UPDATE Section SET name=?, type=?, role=? WHERE id=?",
                updatedSection.getName(), updatedSection.getType().name(), updatedSection.getRole().name(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Section WHERE id=?", id);
    }
}

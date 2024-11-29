package ru.kpfu.itis.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kpfu.itis.entities.Section;
import ru.kpfu.itis.entities.enums.SectionRole;
import ru.kpfu.itis.entities.enums.SectionType;
import ru.kpfu.itis.services.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SectionMapper implements RowMapper<Section> {

    @Override
    public Section mapRow(ResultSet rs, int rowNum) throws SQLException {
        Section section = new Section();

        section.setId(rs.getInt("id"));
        section.setName(rs.getString("name"));
        section.setType(SectionType.valueOf(rs.getString("type")));
        section.setRole(SectionRole.valueOf(rs.getString("role")));

        return section;
    }
}

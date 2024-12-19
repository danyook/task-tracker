package ru.kpfu.itis.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kpfu.itis.entities.Avatar;


import java.sql.ResultSet;
import java.sql.SQLException;

public class AvatarMapper implements RowMapper<Avatar> {
    @Override
    public Avatar mapRow(ResultSet rs, int rowNum) throws SQLException {
        Avatar avatar = new Avatar();

        avatar.setId(rs.getInt("id"));
        avatar.setUrl(rs.getString("url"));

        return avatar;

    }
}
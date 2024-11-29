package ru.kpfu.itis.util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTemplateProvider {

    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate getJdbcTemplate() {

        if (jdbcTemplate == null) {
            DbConfig dbConfig = new DbConfig();

            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(DbConfig.getDatabaseDriver());
            dataSource.setUrl(DbConfig.getDatabaseUrl());
            dataSource.setUsername(DbConfig.getDatabaseUsername());
            dataSource.setPassword(DbConfig.getDatabasePassword());
            jdbcTemplate = new JdbcTemplate(dataSource);
        }

        return jdbcTemplate;

    }


}

package ru.kpfu.itis.util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JdbcTemplateProvider {



    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate getJdbcTemplate() {

        String PROD_DB_HOST = System.getenv("PROD_DB_HOST");
        String PROD_DB_PORT = System.getenv("PROD_DB_PORT");
        String PROD_DB_NAME = System.getenv("PROD_DB_NAME");
        String PROD_DB_USERNAME = System.getenv("PROD_DB_USERNAME");
        String PROD_DB_PASSWORD = System.getenv("PROD_DB_PASSWORD");

        if (jdbcTemplate == null) {
            DbConfig dbConfig = new DbConfig();

            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(DbConfig.getDatabaseDriver());

            dataSource.setUrl("jdbc:postgresql://%s:%s/%s"
                    .formatted(PROD_DB_HOST, PROD_DB_PORT, PROD_DB_NAME));

            dataSource.setUsername(PROD_DB_USERNAME);
            dataSource.setPassword(PROD_DB_PASSWORD);
            jdbcTemplate = new JdbcTemplate(dataSource);
        }

        return jdbcTemplate;

    }


}

package ru.kpfu.itis.util;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@Slf4j
@WebListener
public class FlywayMigrationListener implements ServletContextListener {

    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
//    private static final PropertiesUtil propertiesUtil = "db.url";


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            String url = "url db";
            String user = "usernmae db";
            String password = "pwd db";

            Flyway flyway = Flyway
                    .configure()
                    .dataSource(url, user, password)
                    .load();

            flyway.migrate();
            log.info("Миграции выполнены успешно!");
        } catch (Exception e) {
            log.error("Ошибка миграции", e);
        }
    }
}

package ru.kpfu.itis;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.kpfu.itis.dao.SectionDAO;
import ru.kpfu.itis.dao.TaskDAO;
import ru.kpfu.itis.dao.TeamDAO;
import ru.kpfu.itis.dao.UserDAO;
import ru.kpfu.itis.services.*;
import ru.kpfu.itis.util.ConnectionProvider;
import ru.kpfu.itis.exception.DbException;
import ru.kpfu.itis.util.JdbcTemplateProvider;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Slf4j
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {

            ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

            JdbcTemplate jdbcTemplate = JdbcTemplateProvider.getJdbcTemplate();

            SectionDAO sectionDAO = SectionDAO.getInstance();
            TaskDAO taskDAO = TaskDAO.getInstance();
            UserDAO userDAO = UserDAO.getInstance();
            TeamDAO teamDAO = TeamDAO.getInstance();

            SectionService sectionService = SectionService.getInstance();
            TaskService taskService = TaskService.getInstance();
            UserService userService = UserService.getInstance();
            TeamService teamService = TeamService.getInstance();
            CloudinaryService cloudinaryService = CloudinaryService.getInstance();

            sce.getServletContext().setAttribute("sectionDAO", sectionDAO);
            sce.getServletContext().setAttribute("taskDAO", taskDAO);
            sce.getServletContext().setAttribute("teamDAO", teamDAO);
            sce.getServletContext().setAttribute("userDAO", userDAO);

            sce.getServletContext().setAttribute("sectionService", sectionService);
            sce.getServletContext().setAttribute("taskService", taskService);
            sce.getServletContext().setAttribute("teamService", teamService);
            sce.getServletContext().setAttribute("userService", userService);
            sce.getServletContext().setAttribute("cloudinaryService", cloudinaryService);

        } catch (DbException e) {
            log.info("Initialization failed");
        }

    }
}
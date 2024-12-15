package ru.kpfu.itis.servlets.task;

import ru.kpfu.itis.entities.*;
import ru.kpfu.itis.entities.enums.TaskStatus;
import ru.kpfu.itis.services.SectionService;
import ru.kpfu.itis.services.TaskService;
import ru.kpfu.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@WebServlet("/task/new")
public class CreateTaskServlet extends HttpServlet {

    private SectionService sectionService;
    private UserService userService;
    private TaskService taskService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        sectionService = (SectionService) getServletContext().getAttribute("sectionService");
        userService = (UserService) getServletContext().getAttribute("userService");
        taskService = (TaskService) getServletContext().getAttribute("taskService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sectionIdParam = req.getParameter("section_id");
        req.setAttribute("section_id", sectionIdParam);
        getServletContext().getRequestDispatcher("/WEB-INF/views/task/new.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        TaskStatus taskStatus = TaskStatus.NOTDONE;
        Date utilDate = new Date();
        Timestamp timestamp = new Timestamp(utilDate.getTime());
        int section_id = Integer.parseInt(req.getParameter("section_id"));

        Section section = sectionService.findOne(section_id);

        Task task = new Task();

        task.setName(name);
        task.setDescription(description);
        task.setTaskStatus(taskStatus);
        task.setDateOfAdd(timestamp);
        task.setSection(section);

        taskService.save(task);

        resp.sendRedirect(req.getContextPath() + "/solo-section/" + section_id);

    }

}

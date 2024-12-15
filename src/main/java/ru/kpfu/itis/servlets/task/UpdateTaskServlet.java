package ru.kpfu.itis.servlets.task;

import ru.kpfu.itis.entities.Section;
import ru.kpfu.itis.entities.Task;
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

@WebServlet("/task/edit")
public class UpdateTaskServlet extends HttpServlet {
    private SectionService sectionService;
    private TaskService taskService;
    private UserService userService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        sectionService = (SectionService) getServletContext().getAttribute("sectionService");
        taskService = (TaskService) getServletContext().getAttribute("taskService");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskIdParam = req.getParameter("task_id");
        req.setAttribute("task_id", taskIdParam);
        String sectionIdParam = req.getParameter("section_id");
        req.setAttribute("section_id", sectionIdParam);

        getServletContext().getRequestDispatcher("/WEB-INF/views/task/edit.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int taskId = Integer.parseInt(req.getParameter("task_id"));
        int sectionId = Integer.parseInt(req.getParameter("section_id"));

        String name = req.getParameter("name");
        String description = req.getParameter("description");


        Section section = sectionService.findById(sectionId);

        Task task = new Task();

        task.setName(name);
        task.setDescription(description);
        task.setSection(section);

        taskService.update(taskId,task);

        resp.sendRedirect(req.getContextPath() + "/task/" + taskId + "?section_id=" + sectionId);
    }
}

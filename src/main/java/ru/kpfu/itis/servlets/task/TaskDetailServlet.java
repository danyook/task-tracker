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
import java.util.logging.Handler;

@WebServlet("/task/*")
public class TaskDetailServlet extends HttpServlet {

    private TaskService taskService;
    private SectionService sectionService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        taskService = (TaskService) getServletContext().getAttribute("taskService");
        sectionService = (SectionService) getServletContext().getAttribute("sectionService");
    }

    private Integer extractTaskId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.matches("/\\d+")) {
            return Integer.parseInt(pathInfo.substring(1));
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sectionIdParam = req.getParameter("section_id");
        System.out.println(sectionIdParam);
        Integer sectionId = Integer.parseInt(sectionIdParam);
        req.setAttribute("section_id", sectionIdParam);

        Integer taskId = extractTaskId(req, resp);
        if (taskId == null) return;

        Task task = taskService.findOne(taskId);
        Section section = sectionService.findOne(sectionId);
        if (task != null) {
            req.setAttribute("task", task);
            req.setAttribute("section", section);
            getServletContext().getRequestDispatcher("/WEB-INF/views/task/detail.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("_method");
        if ("DELETE".equalsIgnoreCase(method)) {
            doDelete(req, resp); // Перенаправляем к методу `doDelete`
        } else {
            // Другие POST запросы можем обрабатывать здесь или отправить ошибку
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sectionId = Integer.parseInt(req.getParameter("section_id"));

        Integer taskId = extractTaskId(req, resp);
        if (taskId == null) return;

        taskService.delete(taskId);
        resp.sendRedirect(req.getContextPath() + "/solo-section/" + sectionId);
    }
}

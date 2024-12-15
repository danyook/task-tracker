package ru.kpfu.itis.servlets.task;

import ru.kpfu.itis.entities.Task;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.services.SectionService;
import ru.kpfu.itis.services.TaskService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

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

        Integer sectionId = Integer.parseInt(sectionIdParam);
        req.setAttribute("section_id", sectionIdParam);

        Integer taskId = extractTaskId(req, resp);
        if (taskId == null) return;

        Task task = taskService.findById(taskId);
        if (task != null) {
            req.setAttribute("task", task);
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
        } else if ("PATCH".equals(method)) {
            doPatch(req, resp);
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


    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int taskId = extractTaskId(req, resp);
        int sectionId = Integer.parseInt(req.getParameter("section_id"));
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Task doneTask = taskService.findById(taskId);

        taskService.setDone(doneTask, new Date(), user.getId());
        resp.sendRedirect(req.getContextPath() + "/solo-section/" + sectionId);
    }
}

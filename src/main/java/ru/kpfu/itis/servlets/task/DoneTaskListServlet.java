package ru.kpfu.itis.servlets.task;

import ru.kpfu.itis.entities.Section;
import ru.kpfu.itis.entities.Task;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.services.SectionService;
import ru.kpfu.itis.services.TaskService;
import ru.kpfu.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/done-tasks")
public class DoneTaskListServlet extends HttpServlet {

    private TaskService taskService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        taskService = (TaskService) getServletContext().getAttribute("taskService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("doneTasks", taskService.findDoneTasks(user.getId()));
        getServletContext().getRequestDispatcher("/WEB-INF/views/section/done-tasks.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int taskId = Integer.parseInt(req.getParameter("task_id"));

        taskService.setNotDone(taskId);

        resp.sendRedirect(req.getContextPath() + "/done-tasks");
    }
}
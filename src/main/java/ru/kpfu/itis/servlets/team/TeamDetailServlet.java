package ru.kpfu.itis.servlets.team;


import ru.kpfu.itis.entities.Section;
import ru.kpfu.itis.entities.Team;
import ru.kpfu.itis.services.SectionService;
import ru.kpfu.itis.services.TaskService;
import ru.kpfu.itis.services.TeamService;
import ru.kpfu.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/team/*")
public class TeamDetailServlet extends HttpServlet {
    private SectionService sectionService;
    private TaskService taskService;
    private UserService userService;
    private TeamService teamService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        sectionService = (SectionService) getServletContext().getAttribute("sectionService");
        taskService = (TaskService) getServletContext().getAttribute("taskService");
        userService = (UserService) getServletContext().getAttribute("userService");
        teamService = (TeamService) getServletContext().getAttribute("teamService");
    }

    private Integer extractSectionId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
        Integer teamId = extractSectionId(req, resp);
        if (teamId == null) return;

        Team team = teamService.findOne(teamId);
        if (team != null) {
            req.setAttribute("team", team);
            req.setAttribute("users", userService.findByTeamId(teamId));//todo сделать получение команд по айди того человека, кто авторизован

            getServletContext().getRequestDispatcher("/WEB-INF/views/team/detail.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("_method");
        if ("DELETE".equalsIgnoreCase(method)) {
            doDelete(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer teamId = extractSectionId(req, resp);
        if (teamId == null) return;

        teamService.delete(teamId);
        resp.sendRedirect(req.getContextPath() + "/team");
    }
}


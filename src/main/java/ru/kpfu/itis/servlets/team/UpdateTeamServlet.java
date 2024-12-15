package ru.kpfu.itis.servlets.team;


import ru.kpfu.itis.entities.Team;
import ru.kpfu.itis.entities.User;
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
import java.util.List;

@WebServlet("/team/edit")
public class UpdateTeamServlet extends HttpServlet {
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teamIdParam = req.getParameter("team_id");
        int teamId = Integer.parseInt(teamIdParam);
        Team team = teamService.findById(teamId);
        req.setAttribute("team", team);
        List<User> teamMembers = userService.findByTeamId(teamId);
        req.setAttribute("teamMembers", teamMembers);
        getServletContext().getRequestDispatcher("/WEB-INF/views/team/edit.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int teamId = Integer.parseInt(req.getParameter("team_id"));
        Team team = teamService.findById(teamId);

        // Assuming user authentication info is stored in the session
        User user = (User) req.getSession().getAttribute("user");

        // Check if the current user is the owner of the team
        if (!team.getOwner().equals(user)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not authorized to update this team.");
            return;
        }

        String name = req.getParameter("name");
        int newOwnerId = Integer.parseInt(req.getParameter("owner"));
        User newOwner = userService.findById(newOwnerId);

        team.setName(name);
        team.setOwner(newOwner);

        teamService.update(teamId, team);

        resp.sendRedirect(req.getContextPath() + "/team/" + teamId);
    }
}


package ru.kpfu.itis.servlets.team;


import ru.kpfu.itis.entities.Section;
import ru.kpfu.itis.entities.Team;
import ru.kpfu.itis.entities.enums.SectionRole;
import ru.kpfu.itis.entities.enums.SectionType;
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
        req.setAttribute("team_id", teamIdParam);
        getServletContext().getRequestDispatcher("/WEB-INF/views/team/edit.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("team_id"));

        String name = req.getParameter("name");
        User user = userService.findOne(1);//todo сделать назначение пользователя, которого выбрал владелец

        Team team = new Team();

        team.setName(name);
        team.setOwner(user);

        teamService.update(id, team);

        resp.sendRedirect(req.getContextPath() + "/team/" + id);
    }

}

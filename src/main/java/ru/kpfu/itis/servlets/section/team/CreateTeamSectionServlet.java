package ru.kpfu.itis.servlets.section.team;

import ru.kpfu.itis.entities.Section;
import ru.kpfu.itis.entities.Team;
import ru.kpfu.itis.entities.enums.SectionRole;
import ru.kpfu.itis.entities.enums.SectionType;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.services.SectionService;
import ru.kpfu.itis.services.TeamService;
import ru.kpfu.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//todo поменять под с solo на team
@WebServlet("/team-section/new")
public class CreateTeamSectionServlet extends HttpServlet {

    private SectionService sectionService;
    private UserService userService;
    private TeamService teamService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        sectionService = (SectionService) getServletContext().getAttribute("sectionService");
        userService = (UserService) getServletContext().getAttribute("userService");
        teamService = (TeamService) getServletContext().getAttribute("teamService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teamIdParam = req.getParameter("team_id");
        req.setAttribute("team_id", teamIdParam);

        getServletContext().getRequestDispatcher("/WEB-INF/views/section/team/new.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        SectionRole sectionRole = SectionRole.TEAM;
        SectionType sectionType = SectionType.valueOf(req.getParameter("type"));

        String teamIdParam = req.getParameter("team_id");
        if (teamIdParam == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        int teamId = Integer.parseInt(teamIdParam);
        Team team = teamService.findOne(teamId);

        Section section = new Section();

        section.setName(name);
        section.setRole(sectionRole);
        section.setType(sectionType);
        section.setTeam(team);

        sectionService.save(section);

        resp.sendRedirect(req.getContextPath() + "/team-section?team_id=" + teamId);

    }

}

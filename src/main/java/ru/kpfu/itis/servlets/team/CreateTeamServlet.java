package ru.kpfu.itis.servlets.team;

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

@WebServlet("/team/new")
public class CreateTeamServlet extends HttpServlet {

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

        getServletContext().getRequestDispatcher("/WEB-INF/views/team/new.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        Team team = new Team();
        team.setName(name);
        team.setOwner(user);

        if (name == null || name.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/team");
        } else {
            int savedTeamId = teamService.save(team);
            teamService.addPersonToTeam(user.getId(), savedTeamId);

            resp.sendRedirect(req.getContextPath() + "/team");
        }

    }


}

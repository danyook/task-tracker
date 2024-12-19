package ru.kpfu.itis.servlets.section.team;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.services.SectionService;
import ru.kpfu.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/team-section")
public class TeamSectionListServlet extends HttpServlet {

    private SectionService sectionService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        sectionService = (SectionService) getServletContext().getAttribute("sectionService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teamIdParam = req.getParameter("team_id");
        if (teamIdParam == null || teamIdParam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            int teamId = Integer.parseInt(teamIdParam);
            req.setAttribute("sections", sectionService.findByTeamId(teamId));
            req.setAttribute("team_id", teamId);
            getServletContext().getRequestDispatcher("/WEB-INF/views/section/team/list.jsp").forward(req, resp);
        }

    }

}

package ru.kpfu.itis.servlets.team;

import ru.kpfu.itis.entities.Team;
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
import java.io.IOException;

@WebServlet("/team/add-user")
public class AddPersonToTeamServlet extends HttpServlet {

    private UserService userService;
    private TeamService teamService;


    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("userService");
        teamService = (TeamService) getServletContext().getAttribute("teamService");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long teamId = Long.parseLong(request.getParameter("team_id"));
        request.setAttribute("teamId", teamId);
        request.getRequestDispatcher("/WEB-INF/views/team/add-user.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int teamId = Integer.parseInt(req.getParameter("team_id"));
        String username = req.getParameter("username");
        User user = userService.findByUsername(username);

        boolean success = false;
        if (user != null) {
            success = teamService.addPersonToTeam(user.getId(), teamId);
        }

        if (success) {
            resp.sendRedirect(req.getContextPath() + "/team/" + teamId);
        } else {
            req.setAttribute("error", "Не удалось добавить участника. Проверьте имя пользователя и попробуйте снова.");
            req.setAttribute("teamId", teamId);
            req.getRequestDispatcher("/WEB-INF/views/team/add-user.jsp").forward(req, resp);
        }
    }
}

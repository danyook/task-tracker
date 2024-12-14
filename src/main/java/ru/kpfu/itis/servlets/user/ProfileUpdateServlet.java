package ru.kpfu.itis.servlets.user;

import ru.kpfu.itis.entities.User;
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

@WebServlet("/profile/edit")
public class ProfileUpdateServlet extends HttpServlet {

    private UserService userService;
    private TeamService teamService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("userService");
        teamService = (TeamService) getServletContext().getAttribute("teamService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/user/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");


        HttpSession httpSession = req.getSession();
        User user = (User) httpSession.getAttribute("user");

        User updatedUser = new User();

        updatedUser.setUsername(username);
        updatedUser.setName(name);
        updatedUser.setSurname(surname);
        updatedUser.setPassword(user.getPassword());

        userService.update(user.getId(), updatedUser);

        httpSession.setAttribute("user", updatedUser);
        httpSession.setMaxInactiveInterval(60 * 60);
        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}

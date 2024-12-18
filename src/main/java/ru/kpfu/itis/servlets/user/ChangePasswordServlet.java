package ru.kpfu.itis.servlets.user;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.exception.NotCorrectPasswordException;
import ru.kpfu.itis.exception.NotFoundUserException;
import ru.kpfu.itis.exception.OldPasswordDoesNotMatchException;
import ru.kpfu.itis.services.UserService;
import ru.kpfu.itis.util.PassEncrypt;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/password")
public class ChangePasswordServlet extends HttpServlet {

    private UserService userService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user/change-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPass = PassEncrypt.encrypt(req.getParameter("oldPass"));
        String newPassOne = PassEncrypt.encrypt(req.getParameter("newPassOne"));
        String newPassTwo = PassEncrypt.encrypt(req.getParameter("newPassTwo"));

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        int userId = user.getId();

        try {
            userService.updatePassword(userId, oldPass, newPassOne, newPassTwo);
            resp.sendRedirect(req.getContextPath() + "/profile");
        } catch (NotFoundUserException | OldPasswordDoesNotMatchException | NotCorrectPasswordException e) {
            req.setAttribute("error", e.getMessage());

            req.getRequestDispatcher("/WEB-INF/views/user/change-password.jsp").forward(req, resp);
        }

    }
}
package ru.kpfu.itis.servlets.user;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.exception.NotFoundUserException;
import ru.kpfu.itis.services.UserService;
import ru.kpfu.itis.util.PassEncrypt;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = PassEncrypt.encrypt(req.getParameter("password"));

        try {
            if (userService.checkPass(username, password)) {

                User user = userService.findByUsername(username);

                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("user", user);
                httpSession.setMaxInactiveInterval(60 * 60);
                resp.sendRedirect(req.getContextPath() + "/section");

            } else {
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        } catch (NotFoundUserException e) {
            req.setAttribute("error", "Такого username не существует.");
            resp.sendRedirect(req.getContextPath() + "/login");
        }

    }
}

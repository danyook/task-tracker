package ru.kpfu.itis.servlets.user;

import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.services.SectionService;
import ru.kpfu.itis.services.TaskService;
import ru.kpfu.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UserService userService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получаем данные от пользователя
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(name + " " + surname);

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            // Если данные некорректные, отправляем обратно на регистрацию
            req.setAttribute("error", "Пожалуйста, введите имя пользователя и пароль.");
            req.getRequestDispatcher("/WEB-INF/views/user/registration.jsp").forward(req, resp);
            return;
        }

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setPassword(password);
        System.out.println(user);
        userService.save(user);

        // Сохранение информации пользователя в сессии
        HttpSession session = req.getSession();
        session.setAttribute("user", user);


        // Перенаправляем пользователя на страницу профиля
        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}






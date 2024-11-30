package ru.kpfu.itis.servlets.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Удаляем текущую сессию
        HttpSession session = req.getSession(false); // Получаем текущую сессию, если она существует
        if (session != null) {
            session.invalidate(); // Уничтожаем сессию
        }

        // Перенаправляем пользователя на главную страницу или страницу входа
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
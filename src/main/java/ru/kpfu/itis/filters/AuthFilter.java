package ru.kpfu.itis.filters;

import ru.kpfu.itis.entities.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Инициализация фильтра (если необходимо)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        User user = null;

        if (session != null) {
            user = (User) session.getAttribute("user");
        }

        String uri = httpRequest.getRequestURI();

        // Проверяем, авторизован ли пользователь
        if (user == null && !uri.contains("login") && !uri.contains("registration")) {
            // Если пользователь не авторизован, перенаправляем на страницу /login
            System.out.println(1);
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            System.out.println(2);
        } else {
            // Если пользователь авторизован, продолжить выполнение цепочки фильтров/сервлетов
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Очистка ресурсов фильтра (если необходимо)
    }
}

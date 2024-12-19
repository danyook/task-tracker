package ru.kpfu.itis.servlets.user;

import com.cloudinary.Cloudinary;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.services.UserService;
import ru.kpfu.itis.util.CloudinaryUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class UserDetailServlet extends HttpServlet {

    private UserService userService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getId();
        req.setAttribute("urlPhoto", userService.getPhotoUrl(userId));
        getServletContext().getRequestDispatcher("/WEB-INF/views/user/profile.jsp").forward(req, resp);
    }
}

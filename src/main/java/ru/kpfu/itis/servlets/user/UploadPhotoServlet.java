package ru.kpfu.itis.servlets.user;

import lombok.extern.slf4j.Slf4j;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.services.CloudinaryService;
import ru.kpfu.itis.services.TeamService;
import ru.kpfu.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UncheckedIOException;

@Slf4j
@WebServlet("/profile/upload-photo")
@MultipartConfig(
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
public class UploadPhotoServlet extends HttpServlet{
    private CloudinaryService cloudinaryService;
    private UserService userService;



    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) getServletContext().getAttribute("userService");
        cloudinaryService = (CloudinaryService) getServletContext().getAttribute("cloudinaryService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            String oldUrl = user.getProfilePicture();

            String url = cloudinaryService.uploadPhoto(req, oldUrl != null);

            userService.updateUserProfilePhoto(user.getId(), url);

        } catch (Exception e) {
            log.info("не получилось обновить");
        }


    }
}
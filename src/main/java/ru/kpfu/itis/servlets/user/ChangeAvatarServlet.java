package ru.kpfu.itis.servlets.user;

import com.cloudinary.Cloudinary;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.services.UserService;
import ru.kpfu.itis.util.CloudinaryUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;

@WebServlet("/profile/change-avatar")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024) // 5 MB
public class ChangeAvatarServlet extends HttpServlet {
    private UserService userService;
    private final Cloudinary cloudinary = CloudinaryUtil.getInstance();

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
        req.getRequestDispatcher("/WEB-INF/views/user/change-avatar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getId();

        String method = req.getParameter("_method");
        if (method != null && method.equalsIgnoreCase("DELETE")) {
            userService.deletePhoto(userId);
            resp.sendRedirect(req.getContextPath() + "/profile");
            return;
        }

        Part filePart = req.getPart("file");
        if (filePart != null && filePart.getSize() > 0) {
            InputStream fileContent = filePart.getInputStream();
            File tempFile = File.createTempFile("upload_", ".tmp");
            Files.copy(fileContent, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            try {
                Map uploadResult = cloudinary.uploader().upload(tempFile, Map.of());
                String avatarUrl = (String) uploadResult.get("secure_url");

                userService.uploadPhoto(userId, avatarUrl);

                resp.sendRedirect(req.getContextPath() + "/profile");
            } catch (Exception e) {
                throw new RuntimeException("Error uploading avatar", e);
            } finally {
                tempFile.delete();
            }
        }
    }
}

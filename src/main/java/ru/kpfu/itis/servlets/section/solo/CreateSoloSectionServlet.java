package ru.kpfu.itis.servlets.section.solo;

import ru.kpfu.itis.entities.Section;
import ru.kpfu.itis.entities.enums.SectionRole;
import ru.kpfu.itis.entities.enums.SectionType;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.services.SectionService;
import ru.kpfu.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/solo-section/new")
public class CreateSoloSectionServlet extends HttpServlet {

    private SectionService sectionService;
    private UserService userService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        sectionService = (SectionService) getServletContext().getAttribute("sectionService");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/section/solo/new.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        SectionRole sectionRole = SectionRole.SOLO;
        SectionType sectionType = SectionType.valueOf(req.getParameter("type").toUpperCase());

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        Section section = new Section();

        section.setName(name);
        section.setRole(sectionRole);
        section.setType(sectionType);
        section.setUser(user);

        sectionService.save(section);

        resp.sendRedirect(req.getContextPath() + "/solo-section");

    }

}

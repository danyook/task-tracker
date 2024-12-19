package ru.kpfu.itis.servlets.section.team;

import ru.kpfu.itis.entities.Section;
import ru.kpfu.itis.entities.enums.SectionRole;
import ru.kpfu.itis.entities.enums.SectionType;
import ru.kpfu.itis.entities.User;
import ru.kpfu.itis.services.SectionService;
import ru.kpfu.itis.services.TaskService;
import ru.kpfu.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//todo поменять под с solo на team
@WebServlet("/team-section/edit")
public class UpdateTeamSectionServlet extends HttpServlet {
    private SectionService sectionService;
    private TaskService taskService;
    private UserService userService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        sectionService = (SectionService) getServletContext().getAttribute("sectionService");
        taskService = (TaskService) getServletContext().getAttribute("taskService");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sectionIdParam = req.getParameter("section_id");
        req.setAttribute("section_id", sectionIdParam);
        getServletContext().getRequestDispatcher("/WEB-INF/views/section/team/edit.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("section_id"));

        String name = req.getParameter("name");
        SectionRole sectionRole = SectionRole.TEAM;
        SectionType sectionType = SectionType.valueOf(req.getParameter("type").toUpperCase());

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        Section section = new Section();

        section.setName(name);
        section.setRole(sectionRole);
        section.setType(sectionType);
        section.setUser(user);

        sectionService.update(id, section);

        resp.sendRedirect(req.getContextPath() + "/team-section/" + id);
    }

}

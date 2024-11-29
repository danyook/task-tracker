package ru.kpfu.itis.servlets.section;

import ru.kpfu.itis.entities.Section;
import ru.kpfu.itis.services.SectionService;
import ru.kpfu.itis.services.TaskService;
import ru.kpfu.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/section/*")
public class SoloSectionDetailServlet extends HttpServlet {
    private SectionService sectionService;
    private TaskService taskService;
    private UserService userService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        sectionService = (SectionService) getServletContext().getAttribute("sectionService");
        taskService = (TaskService) getServletContext().getAttribute("taskService");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    private Integer extractSectionId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo != null && pathInfo.matches("/\\d+")) {
            return Integer.parseInt(pathInfo.substring(1));
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer sectionId = extractSectionId(req, resp);
        if (sectionId == null) return;

        Section section = sectionService.findOne(sectionId);
        if (section != null) {
            req.setAttribute("section", section);
            req.setAttribute("tasks", taskService.findBySectionId(sectionId));
            getServletContext().getRequestDispatcher("/WEB-INF/views/section/detail.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("_method");
        if ("DELETE".equalsIgnoreCase(method)) {
            doDelete(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer sectionId = extractSectionId(req, resp);
        if (sectionId == null) return;

        sectionService.delete(sectionId);
        resp.sendRedirect(req.getContextPath() + "/section");
    }
}


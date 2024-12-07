//package ru.kpfu.itis.servlets.section.team;
//
//import ru.kpfu.itis.services.SectionService;
//import ru.kpfu.itis.services.UserService;
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/section")
//public class SoloSectionListServlet extends HttpServlet {
//
//    private SectionService sectionService;
//    private UserService userService;
//
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//        sectionService = (SectionService) getServletContext().getAttribute("sectionService");
//        userService = (UserService) getServletContext().getAttribute("userService");
//
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("sections", sectionService.findByUserId(1));
//        getServletContext().getRequestDispatcher("/WEB-INF/views/section/list.jsp").forward(req, resp);
//
//    }
//
//}

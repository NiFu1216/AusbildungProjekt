package servlet;

import dao.SeminarDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/seminare")
public class SeminarSucheServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SeminarDAO dao = new SeminarDAO();

        try {
            request.setAttribute("kurse", dao.getAlleKurse());
            request.setAttribute("sprachen", dao.getAlleSprachen());
            request.getRequestDispatcher("/seminare.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("fehlermeldung", e.getMessage());
            request.getRequestDispatcher("/fehler.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        session.setAttribute("kurs", request.getParameter("kurs"));
        session.setAttribute("sprache", request.getParameter("sprache"));
        response.sendRedirect("seminarAuswahl");
    }
}
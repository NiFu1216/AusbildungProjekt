package servlet;

import dao.TeilnehmerDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Person;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            TeilnehmerDAO dao = new TeilnehmerDAO();
            request.setAttribute("personen", dao.getAllePersonen());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } catch (Exception e) {

            request.setAttribute("fehlermeldung", e.getMessage());
            request.getRequestDispatcher("/fehler.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        String svnr = request.getParameter("svnr");
        TeilnehmerDAO dao = new TeilnehmerDAO();
        Person p = dao.findePerson(svnr);
        boolean admin = "0000000000".equals(svnr);

        session.setAttribute("vorname", p.getVorname());
        session.setAttribute("nachname", p.getNachname());
        session.setAttribute("svnr", svnr);
        session.setAttribute("admin", admin);

        response.sendRedirect("index.jsp");
    }

}
package servlet;

import dao.TeilnehmerDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/teilnehmer")
public class TeilnehmerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Boolean admin = (Boolean) session.getAttribute("admin");

        try {

            if (admin != null && admin) {

                TeilnehmerDAO dao = new TeilnehmerDAO();
                request.setAttribute("teilnehmerListe", dao.getAllePersonen());
                request.getRequestDispatcher("/teilnehmer.jsp").forward(request, response);

            } else {

                session.setAttribute("teilnehmerSVNr", session.getAttribute("svnr"));
                response.sendRedirect("reservierung");
            }
        } catch (Exception e) {

            request.setAttribute("fehlermeldung", e.getMessage());
            request.getRequestDispatcher("/fehler.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        session.setAttribute("teilnehmerSVNr", request.getParameter("svnr"));
        response.sendRedirect("reservierung");
    }
}
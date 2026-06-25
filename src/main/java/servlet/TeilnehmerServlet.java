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

        TeilnehmerDAO dao = new TeilnehmerDAO();

        try {
            request.setAttribute("teilnehmerListe", dao.getAlleTeilnehmer());
            request.getRequestDispatcher("/teilnehmer.jsp").forward(request, response);

        } catch(Exception e){

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
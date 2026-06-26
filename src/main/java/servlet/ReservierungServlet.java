package servlet;

import dao.ReservierungDAO;
import jakarta.servlet.ServletException;
import model.Reservierung;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

@WebServlet("/reservierung")
public class ReservierungServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        String kurs = (String) session.getAttribute("kurs");
        String sprache = (String) session.getAttribute("sprache");
        String datum = (String) session.getAttribute("datum");
        String uhrzeit = (String) session.getAttribute("uhrzeit");
        String teilnehmer = (String) session.getAttribute("teilnehmerSVNr");

        try {
            ReservierungDAO dao = new ReservierungDAO();
            int nummer = dao.naechsteNummer();
            Reservierung r = new Reservierung(nummer, teilnehmer, Date.valueOf(datum), Time.valueOf(uhrzeit));
            dao.reservieren(r);

            request.setAttribute("nummer", nummer);
            request.setAttribute("kurs", kurs);
            request.setAttribute("sprache", sprache);
            request.setAttribute("datum", datum);
            request.setAttribute("uhrzeit", uhrzeit);
            request.setAttribute("teilnehmer", teilnehmer);

            request.getRequestDispatcher("/reservierung.jsp").forward(request, response);

        } catch(Exception e){
            request.setAttribute("fehlermeldung", e.getMessage());
            request.getRequestDispatcher("/fehler.jsp").forward(request, response);
        }
    }
}
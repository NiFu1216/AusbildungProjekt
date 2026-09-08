package servlet;

import dao.SeminarDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/seminarAuswahl")
public class SeminarAuswahlServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String kurs = (String) session.getAttribute("kurs");
        String sprache = (String) session.getAttribute("sprache");
        SeminarDAO dao = new SeminarDAO();

        try {

            request.setAttribute("seminare", dao.findeSeminare(kurs, sprache));
            request.getRequestDispatcher("/seminarAuswahl.jsp").forward(request, response);

        } catch (Exception e) {
            if (kurs == null || sprache == null) {

                request.setAttribute("fehlermeldung", "Session abgelaufen.");
                request.getRequestDispatcher("/fehler.jsp").forward(request, response);
            } else {

                request.setAttribute("fehlermeldung", e.getMessage());
                request.getRequestDispatcher("/fehler.jsp").forward(request, response);
            }
        }}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String seminarParam = request.getParameter("seminar");

        // 1. Prüfen, ob überhaupt eine Auswahl getroffen wurde
        if (seminarParam == null || seminarParam.isEmpty()) {
            request.setAttribute("fehlermeldung", "Bitte wählen Sie ein Seminar aus.");
            request.getRequestDispatcher("/fehler.jsp").forward(request, response);
            return;
        }

        try {
            // Parameter aufspalten (z. B. "2026-09-01|09:00:00")
            String[] teile = seminarParam.split("\\|");
            String datum = teile[0];
            String uhrzeit = teile[1];

            // Daten in der Session speichern für das spätere ReservierungServlet
            session.setAttribute("datum", datum);
            session.setAttribute("uhrzeit", uhrzeit);

            // Weiterleitung zur eigentlichen Buchung / Reservierung
            response.sendRedirect("reservierung");

        } catch (Exception e) {
            // Im Fehlerfall NIEMALS unvorbereitet an seminarAuswahl.jsp leiten,
            // sondern kontrolliert an fehler.jsp:
            request.setAttribute("fehlermeldung", "Fehler bei der Seminarauswahl: " + e.getMessage());
            request.getRequestDispatcher("/fehler.jsp").forward(request, response);
        }
    }
}
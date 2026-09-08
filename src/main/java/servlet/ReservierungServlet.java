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

        // Attribute aus der Session auslesen
        String kurs = (String) session.getAttribute("kurs");
        String sprache = (String) session.getAttribute("sprache");
        String datum = (String) session.getAttribute("datum");
        String uhrzeit = (String) session.getAttribute("uhrzeit");
        String teilnehmer = (String) session.getAttribute("teilnehmerSVNr");

        // 1. ABSICHERUNG: Prüfen, ob Session-Attribute fehlen oder die Session abgelaufen ist
        if (datum == null || uhrzeit == null || teilnehmer == null) {
            request.setAttribute("fehlermeldung", "Ungültiger Aufruf oder Ihre Sitzung ist abgelaufen. Bitte starten Sie die Buchung erneut.");
            request.getRequestDispatcher("/fehler.jsp").forward(request, response);
            return; // Beendet die Methode, um Folgefehler zu vermeiden
        }

        try {
            // 2. ABSICHERUNG: Uhrzeit auf korrektes Format HH:MM:SS bringen (falls nur HH:MM geliefert wurde)
            if (uhrzeit.length() == 5) {
                uhrzeit += ":00";
            }

            ReservierungDAO dao = new ReservierungDAO();

            // 3. PRÜFUNG: Hat der Teilnehmer dieses Seminar bereits gebucht?
            if (dao.hatBereitsSeminarGebucht(teilnehmer, Date.valueOf(datum), Time.valueOf(uhrzeit))) {
                request.setAttribute("fehlermeldung", "Es existiert bereits eine Reservierung für dieses Seminar.");
                request.getRequestDispatcher("/fehler.jsp").forward(request, response);
                return;
            }

            // 4. PRÜFUNG: Ist die angemeldete Person überhaupt ein zugelassener Teilnehmer?
            if (!dao.istTeilnehmer(teilnehmer)) {
                request.setAttribute("fehlermeldung", "Sie sind kein Teilnehmer! Nur Teilnehmer dürfen Seminare buchen!");
                request.getRequestDispatcher("/fehler.jsp").forward(request, response);
                return;
            }

            // 5. BUCHUNG DURCHFÜHREN
            int nummer = dao.naechsteNummer();
            Reservierung r = new Reservierung(nummer, teilnehmer, Date.valueOf(datum), Time.valueOf(uhrzeit));
            dao.reservieren(r);

            // Daten für die Bestätigungsseite (reservierung.jsp) an den Request geheftet
            request.setAttribute("nummer", nummer);
            request.setAttribute("kurs", kurs);
            request.setAttribute("sprache", sprache);
            request.setAttribute("datum", datum);
            request.setAttribute("uhrzeit", uhrzeit);
            request.setAttribute("teilnehmer", teilnehmer);

            // Erfolgreiche Weiterleitung zur Bestätigungsseite
            request.getRequestDispatcher("/reservierung.jsp").forward(request, response);

        } catch (Exception e) {
            // 6. ABSICHERUNG: Sicherstellen, dass e.getMessage() nicht null ist
            String msg = (e.getMessage() != null && !e.getMessage().isEmpty())
                    ? e.getMessage()
                    : "Ein Fehler ist aufgetreten (" + e.getClass().getSimpleName() + ").";

            request.setAttribute("fehlermeldung", msg);

            // Prüfen, ob die Antwort bereits an den Browser gesendet wurde
            if (!response.isCommitted()) {
                request.getRequestDispatcher("/fehler.jsp").forward(request, response);
            }
        }
    }
}
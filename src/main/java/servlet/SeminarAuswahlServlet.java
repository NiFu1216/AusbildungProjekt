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
            protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String seminarIdParam = req.getParameter("seminarId");

                // Prüfen, ob eine Auswahl getroffen wurde
                if (seminarIdParam == null || seminarIdParam.isEmpty()) {
                    // Falls nichts ausgewählt wurde, zurück zur Auswahl oder Fehler abfangen
                    req.setAttribute("fehler", "Bitte wähle ein Seminar aus!");
                    req.getRequestDispatcher("/seminarAuswahl.jsp").forward(req, resp);
                    return;
                }

                try {
                    int seminarId = Integer.parseInt(seminarIdParam);
                    HttpSession session = req.getSession();
                    session.setAttribute("gewaehltesSeminarId", seminarId);

                    resp.sendRedirect(req.getContextPath() + "/reservierung");
                } catch (NumberFormatException e) {
                    resp.sendRedirect(req.getContextPath() + "/seminare");
                }
            }
}
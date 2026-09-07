package servlet;

import dao.KundenDAO;
import model.Person;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/kunden")
public class KundenVerwaltungServlet extends HttpServlet {

    private final KundenDAO kundenDAO = new KundenDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Boolean admin = (session != null) ? (Boolean) session.getAttribute("admin") : null;

        // Nur für Admins freigegeben
        if (admin == null || !admin) {
            resp.sendRedirect(req.getContextPath() + "/startseite.jsp");
            return;
        }

        String action = req.getParameter("action");
        String q = req.getParameter("q");

        if ("edit".equals(action)) {
            String svnr = req.getParameter("svnr");
            Person p = kundenDAO.getKundeBySvnr(svnr);
            req.setAttribute("bearbeiterKunde", p);
        }

        List<Person> liste = kundenDAO.sucheKunden(q);
        req.setAttribute("kundenListe", liste);
        req.setAttribute("suchbegriff", q);

        req.getRequestDispatcher("/kundenVerwaltung.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        Boolean admin = (session != null) ? (Boolean) session.getAttribute("admin") : null;
        if (admin == null || !admin) {
            resp.sendRedirect(req.getContextPath() + "/startseite.jsp");
            return;
        }

        String action = req.getParameter("action");
        String svnr = req.getParameter("svnr");
        String vorname = req.getParameter("vorname");
        String nachname = req.getParameter("nachname");
        String plz = req.getParameter("postleitzahl");
        String ort = req.getParameter("ort");
        String strasse = req.getParameter("strasse");
        String hausnummer = req.getParameter("hausnummer");

        Person p = new Person(svnr, vorname, nachname, plz, ort, strasse, hausnummer);

        if ("update".equals(action)) {
            kundenDAO.kundenBearbeiten(p);
        } else {
            kundenDAO.kundenAnlegen(p);
        }

        resp.sendRedirect(req.getContextPath() + "/kunden");
    }
}
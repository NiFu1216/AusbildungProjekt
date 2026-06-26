package servlet;

import dao.ReservierungDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/reservierungen")
public class ReservierungenServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Boolean admin = (Boolean) session.getAttribute("admin");
        ReservierungDAO dao = new ReservierungDAO();

        try {
            if (admin != null && admin){

                request.setAttribute("reservierungen", dao.alleReservierungenMitNamen());
            } else {

                request.setAttribute("reservierungen", dao.reservierungenVonTeilnehmer((String) session.getAttribute("svnr")));

            }

            request.getRequestDispatcher("/reservierungen.jsp").forward(request, response);

        } catch(Exception e){

            request.setAttribute("fehlermeldung", e.getMessage());
            request.getRequestDispatcher("/fehler.jsp").forward(request, response);
        }
    }
}

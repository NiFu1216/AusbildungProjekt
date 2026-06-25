package servlet;

import dao.ReservierungDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/reservierungen")
public class ReservierungenServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            ReservierungDAO dao = new ReservierungDAO();
            request.setAttribute("reservierungen", dao.alleReservierungenMitNamen());
            request.getRequestDispatcher("/reservierungen.jsp").forward(request, response);
        } catch(Exception e){

            request.setAttribute("fehlermeldung", e.getMessage());
            request.getRequestDispatcher("/fehler.jsp").forward(request, response);
        }
    }
}

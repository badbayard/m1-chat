package tp_mif03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = res.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Init</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Init at " + req.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");//setting the content type
        response.sendRedirect("index.html");
        processRequest(request, response);
        if (request.getParameter("Deco") != null) {
            HttpSession session=request.getSession();
            session.invalidate();
        }

    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) // pour la méthode POST
            throws ServletException, IOException {
        res.setContentType("text/html");
        String pseudo = req.getParameter("pseudo"); //récupération du pseudo
        String nom_salon = req.getParameter("nom_salon");//récupération du nom du salon
        HttpSession session = req.getSession(true); // récupération de la session
        session.setAttribute("pseudo",pseudo);
        session.setAttribute("nom_salon",nom_salon);
        res.sendRedirect("interface.html");
        processRequest(req,res);

    }
}

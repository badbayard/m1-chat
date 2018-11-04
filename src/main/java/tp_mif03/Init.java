package tp_mif03;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;


public class Init extends HttpServlet {

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

    public void doGet(HttpServletRequest req, HttpServletResponse res) // pour la méthode GET
            throws ServletException, IOException {

        res.setContentType("text/html");//setting the content type
        PrintWriter pw = res.getWriter();//get the stream to write the data
        //res.setStatus(res.SC_MOVED_TEMPORARILY);
        //res.setHeader("location","index.html");
        res.sendRedirect("index.html");
        processRequest(req, res);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) // pour la méthode POST
        throws ServletException, IOException {
            res.setContentType("text/html");
            PrintWriter pw = res.getWriter();
            String pseudo = req.getParameter("pseudo"); //récupération du pseudo
            String nom_salon = req.getParameter("nom_salon");//récupération du nom du salon
            HttpSession session = req.getSession(true); // récupération de la session
            session.setAttribute("pseudo",pseudo);
            session.setAttribute("nom_salon",nom_salon);
            res.sendRedirect("interface.html");
            processRequest(req,res);

    }
}


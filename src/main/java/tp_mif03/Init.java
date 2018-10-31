package tp_mif03;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;



public class Init extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) // pour la méthode GET
            throws ServletException, IOException {
        res.setContentType("text/html");//setting the content type
        PrintWriter pw = res.getWriter();//get the stream to write the data
        res.setStatus(res.SC_MOVED_TEMPORARILY);
        res.setHeader("location","index.html");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) // pour la méthode POST
        throws ServletException, IOException {
            res.setContentType("text/html");
            PrintWriter pw = res.getWriter();
            String pseudo = req.getParameter("pseudo"); //récupération du pseudo
            HttpSession session = req.getSession(); // récupération de la session
            //pw.println("<h1>"+pseudo+"</h1>");
            //pw.println("<h2>"+session+"</h2>");
            res.setStatus(res.SC_MOVED_TEMPORARILY);
            res.setHeader("location","interface.html");

    }
}


/*
public void doPost(HttpServletRequest req, HttpServletResponse res){
    throws ServletException, IOException{
            String pseudo = req.getParameter("pseudo");
        }
}
*/
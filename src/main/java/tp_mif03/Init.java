package tp_mif03;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;



public class Init extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html");//setting the content type
        PrintWriter pw = res.getWriter();//get the stream to write the data
        res.setStatus(res.SC_MOVED_TEMPORARILY);
        res.setHeader("location","index.html");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
            res.setContentType("text/html");
            PrintWriter pw = res.getWriter();
            String pseudo = req.getParameter("pseudo");
            pw.println("<h1>"+pseudo+"</h1>");

    }
}


/*
public void doPost(HttpServletRequest req, HttpServletResponse res){
    throws ServletException, IOException{
            String pseudo = req.getParameter("pseudo");
        }
}
*/
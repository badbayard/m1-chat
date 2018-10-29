import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
public class Init extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)
            throws ServletException,IOException
    {
        res.setContentType("text/html");//setting the content type
        PrintWriter pw=res.getWriter();//get the stream to write the data
        pw.println("<h1>Hello world</h1>");
    }}  ​
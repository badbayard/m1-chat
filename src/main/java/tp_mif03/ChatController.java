package tp_mif03;

import org.springframework.web.bind.annotation.PostMapping;
import tp_mif03.bean.GestionMessages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping ("/chat")
public class ChatController extends HttpServlet {

    private GestionMessages gM;

    @Autowired
    public ChatController() {
        this.gM =  new GestionMessages();
    }

//    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
//            throws ServletException, IOException {
//        res.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = res.getWriter()) {
//
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Init</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Init at " + req.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }

    @GetMapping
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<h1>"+ request.getAttribute("nom_salon") +"</h1>");

        for (Message m : gM.getSalon( request.getParameter("nom_salon")))
        {
            out.println("<p>" + m.toString() + "</p>");
        }
        response.sendRedirect("index.html");

    }

    @PostMapping
    public void doPost(HttpServletRequest req, HttpServletResponse res) // pour la méthode POST
            throws ServletException, IOException {
        res.setContentType("text/html");
        String pseudo = req.getParameter("pseudo"); //récupération du pseudo
        String nom_salon = req.getParameter("nom_salon");//récupération du nom du salon
        HttpSession session = req.getSession(true); // récupération de la session
        session.setAttribute("pseudo",pseudo);
        session.setAttribute("nom_salon",nom_salon);
        gM.addSalon(nom_salon);
        res.sendRedirect("chat.html");
    }



}

package tp_mif03.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import tp_mif03.Model.GestionMessages;
import tp_mif03.Model.GestionUtilisateurs;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Init")
public class Init extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) // pour la méthode POST
            throws ServletException, IOException {
        HttpSession session = req.getSession(true); // récupération de la session
        ServletContext sContext = getServletContext();


        String pseudo = req.getParameter("pseudo"); //récupération du pseudo
        String nom_salon = req.getParameter("nom_salon");//récupération du nom du salon

        session.setAttribute("pseudo",pseudo);
        session.setAttribute("nom_salon",nom_salon);

        GestionMessages gM = (GestionMessages) sContext.getAttribute("gM");
        GestionUtilisateurs gU = (GestionUtilisateurs) sContext.getAttribute("gU");
        if(gU == null) {
            sContext.setAttribute("gU", gU);
            res.sendRedirect("user404.jsp");
        }
        else if(!gU.getListeUtilisateurs().contains(req.getParameter("pseudo"))) {
            sContext.setAttribute("gU", gU);
            res.sendRedirect("user404.jsp");
        }
        else if(gU.getListeUtilisateurs().contains(req.getParameter("pseudo"))){
            sContext.setAttribute("gU", gU);

            if(gM == null) {
                gM = new GestionMessages();
                gM.addSalon(nom_salon);
                sContext.setAttribute("gM", gM);
            }
            else if(gM.getSalon(nom_salon) == null) {
                gM.addSalon(nom_salon);
                sContext.setAttribute("gM", gM);
            }
            else {
                sContext.setAttribute("gM", gM);
            }

            Cookie cookieSalon = new Cookie("nom_salon", req.getParameter("nom_salon"));
            Cookie cookiePseudo = new Cookie("pseudo", req.getParameter("pseudo"));
            int nbMessage = gM.getSalon(nom_salon).size();
            Cookie cookieNbMessage = new Cookie("nbMessage", Integer.toString(nbMessage));
            cookieSalon.setMaxAge(86400);
            cookieNbMessage.setMaxAge(86400);
            res.addCookie(cookieSalon);
            res.addCookie(cookiePseudo);
            res.addCookie(cookieNbMessage);

            res.sendRedirect("chat.jsp");
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession(true); // récupération de la session

        String pseudo = req.getParameter("pseudo"); //récupération du pseudo
        String nom_salon = req.getParameter("nom_salon");//récupération du nom du salon

        session.setAttribute("pseudo",pseudo);
        session.setAttribute("nom_salon",nom_salon);
        res.sendRedirect("index.html");

    }




}

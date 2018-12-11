package tp_mif03.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import tp_mif03.Model.GestionMessages;
import tp_mif03.Model.GestionUtilisateurs;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ClientPreAjax/Init")
public class Init extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) // pour la méthode POST
            throws ServletException, IOException {
        HttpSession session = req.getSession(true); // récupération de la session
        ServletContext sContext = getServletContext();


        String username = req.getParameter("username"); //récupération du pseudo
        String nom_salon = req.getParameter("nom_salon");//récupération du nom du salon

        session.setAttribute("username",username);
        session.setAttribute("nom_salon",nom_salon);

        GestionMessages gM = (GestionMessages) sContext.getAttribute("gM");
        GestionUtilisateurs gU = (GestionUtilisateurs) sContext.getAttribute("gU");
        if(gU == null) {
//            throw new CustomException("E404", "The user list is empty");
            res.sendRedirect("userNotFound.html");
        }
        else if(!gU.getListeUtilisateurs().contains(req.getParameter("username"))) {
//            throw new CustomException("E404", "The user is not on the user list");
            res.sendRedirect("userNotFound.html");
        }
        else if(gU.getListeUtilisateurs().contains(req.getParameter("username"))){
            sContext.setAttribute("gU", gU);

            if(gM == null) {
                gM = new GestionMessages();
                gM.ajouterSalon(nom_salon);
                sContext.setAttribute("gM", gM);
            }
            else if(gM.getSalon(nom_salon) == null) {
                gM.ajouterSalon(nom_salon);
                sContext.setAttribute("gM", gM);
            }
            else {
                sContext.setAttribute("gM", gM);
            }

            Cookie cookieSalon = new Cookie("nom_salon", req.getParameter("nom_salon"));
            Cookie cookieUsername = new Cookie("username", req.getParameter("username"));
            int nbMessage = gM.getSalon(nom_salon).size();
            Cookie cookieNbMessage = new Cookie("nbMessage", Integer.toString(nbMessage));
            cookieSalon.setMaxAge(86400);
            cookieNbMessage.setMaxAge(86400);
            res.addCookie(cookieSalon);
            res.addCookie(cookieUsername);
            res.addCookie(cookieNbMessage);

            res.sendRedirect("chat.jsp");

        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession(true); // récupération de la session

        String username = req.getParameter("username"); //récupération du pseudo
        String nom_salon = req.getParameter("nom_salon");//récupération du nom du salon

        session.setAttribute("username",username);
        session.setAttribute("nom_salon",nom_salon);
        res.sendRedirect("interface.html");

    }




}

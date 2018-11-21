package tp_mif03.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import tp_mif03.Model.GestionMessages;
import tp_mif03.Model.GestionUtilisateurs;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
                System.out.println(" i says hewwo");
        if(gU == null) {
            gU = new GestionUtilisateurs();
            gU.nouveauUtilisateur(pseudo);
            System.out.println(" i is null");
            sContext.setAttribute("gU", gU);

        }
        else if(!gU.getListeUtilisateurs().contains(req.getParameter("nom_salon"))) {
            System.out.println(" i has not a user");
            gU.nouveauUtilisateur(pseudo);
            sContext.setAttribute("gU", gU);

        }
        else {
            System.out.println(" i had a user");
            sContext.setAttribute("gU", gU);

        }

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

        res.sendRedirect("chat.html");
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

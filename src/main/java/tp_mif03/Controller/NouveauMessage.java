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

@WebServlet("/ClientPreAjax/NouveauMessage")
public class NouveauMessage extends HttpServlet {


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) // pour la méthode POST
            throws ServletException, IOException {
        HttpSession session = req.getSession(true); // récupération de la session
        ServletContext sContext = getServletContext();

        String newUsername = (String) session.getAttribute("username");
        String newNom_salon = (String) session.getAttribute("nom_salon");
        String msg = req.getParameter("message");


        GestionMessages gM = (GestionMessages) sContext.getAttribute("gM");
        GestionUtilisateurs gU = (GestionUtilisateurs) sContext.getAttribute("gU");
        if(gU.getListeUtilisateurs().contains(session.getAttribute("username"))){
            gM.addMsg(newNom_salon, newUsername, msg);
            res.sendRedirect("chat.jsp");
        }
        else {
            res.sendRedirect("chat.jsp"); //petit fix de redirection
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.sendRedirect("chat.jsp");

    }




}

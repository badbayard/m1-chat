package tp_mif03.Controller;

import tp_mif03.Model.GestionUtilisateurs;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/chat")
public class Filtre  extends HttpServlet implements Filter {

    private FilterConfig configuration;

    public void doFilter(ServletRequest servletReq, ServletResponse servletRes, FilterChain chaine) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletReq;
        HttpServletResponse response = (HttpServletResponse) servletRes;
        ServletContext sContext = configuration.getServletContext();
        GestionUtilisateurs gU = (GestionUtilisateurs) sContext.getAttribute("gestionUtilisateurs");
        HttpSession session = request.getSession();

        if(gU == null){
            response.sendRedirect("index.html");
            // sans le return, le filtre ne stoppe pas
            return;
        }

        if(request.getParameter("pseudo") != null){
            session.setAttribute("pseudo", request.getParameter("pseudo"));
            session.setAttribute("nom_salon", request.getParameter("nom_salon"));
        }

        if(!gU.getListeUtilisateurs().contains(session.getAttribute("pseudo"))){
            session.invalidate();
            response.sendRedirect("index.html");
            return;
        }

        if(session.getAttribute("pseudo") == null){
            response.sendRedirect("index.html");
        } else {
            chaine.doFilter(request, response);
        }
    }

    public void init(FilterConfig configuration) throws ServletException {
        this.configuration = configuration;
    }
}

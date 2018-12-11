package tp_mif03.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tp_mif03.Model.GestionUtilisateurs;

import javax.servlet.ServletContext;


@Controller
public class SpringController  {

    private ServletContext context;


    public SpringController(ServletContext context) {
        this.context = context;
    }



    @RequestMapping(value = "/backoffice", method = RequestMethod.GET)
    public String init() {
        return "backoffice";
    }

    @RequestMapping(value = "/backoffice/salon/{salon}", method = RequestMethod.GET)
    public String GetSalon(@PathVariable String salon, @RequestParam(value = "nbId", required = false, defaultValue = "0") int nbId, Model m) {
        m.addAttribute("salon", salon);
        if(nbId == 0) {
            return "salon";
        }
        else {
            m.addAttribute("nbId", nbId);
            return "msgInfo";
        }

    }


    @RequestMapping(value = "/backoffice/newuser",  method = RequestMethod.POST)
    public String PostNewUser(@RequestParam(value = "user") String user ,Model m){
        GestionUtilisateurs gUBO;
        if(context.getAttribute("gU") == null){
            gUBO = new GestionUtilisateurs();
        }else{
            gUBO = (GestionUtilisateurs) context.getAttribute("gU");
        }
        gUBO.nouveauUtilisateur(user);
        context.setAttribute("gU", gUBO);
        m.addAttribute("username", user);
        return "addedUser";
    }

    @RequestMapping(value = "/backoffice/index.html", method = RequestMethod.GET)
    public String SalonGet() {
        return "interface.html";
    }


}

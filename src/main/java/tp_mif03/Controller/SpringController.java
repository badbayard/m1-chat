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



    @RequestMapping("/backoffice")
    public String init() {
        return "backoffice";
    }

    @GetMapping("/backoffice/salon/{salon}")
    public String SalonGet(@PathVariable String salon, @RequestParam(value = "nbId", required = false, defaultValue = "0") int nbId, Model m) {
        m.addAttribute("salon", salon);
        if(nbId == 0) {
            return "salon";
        }
        else {
            m.addAttribute("nbId", nbId);
            return "msgInfo";
        }

    }


    @PostMapping("/backoffice/users")
    public String AddUserPOST(@RequestParam(value = "user") String user ,Model m){
        GestionUtilisateurs gUBO;
        if(context.getAttribute("gU") == null){
            gUBO = new GestionUtilisateurs();
        }else{
            gUBO = (GestionUtilisateurs) context.getAttribute("gU");
        }
        gUBO.nouveauUtilisateur(user);
        context.setAttribute("gU", gUBO);
        m.addAttribute("user", user);
        return "newUser";
    }

    @GetMapping("/backoffice/index.html")
    public String SalonGet() {
        return "/index.html";
    }


}

package tp_mif03.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import tp_mif03.Model.GestionMessages;
import tp_mif03.Model.GestionUtilisateurs;
import tp_mif03.Model.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.*;


//malheureusement, on n'a pas arrivé a que le rest controller charge le contenu des requetes PUT sous format json,
//on sait pourtant que les fonctions marchent a priori, vu quelles lancent bien des exceptions
@org.springframework.web.bind.annotation.RestController
public class RestController {


    private ServletContext context;
//    on reprend le context du spring controller
    @Autowired
    public void SpringController(ServletContext context) {
        this.context = context;
    }

//  on recupere la liste des salon auxquels l'utilisateur a participé
    @RequestMapping(value = "/backoffice/users/{user}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<String> getListeSalonsUtilisateur(@PathVariable String user) throws Exception {
        GestionMessages gM = (GestionMessages) context.getAttribute("gM");
        GestionUtilisateurs gU = (GestionUtilisateurs) context.getAttribute("gU");

       //on verifie si l'utilisateur existe, le cas echeant on lance des exception
       if(!gU.getGestionUtilisateurs().contains(user)) {
           throw new CustomException("E404", "The user is not on the user list");
       }

        //nous allons stocker les noms des salons auxquels l'utilisateur a participé
        List<String> listeSalons = new ArrayList<>();

        //idee extraite de https://stackoverflow.com/questions/16246821/how-to-get-values-and-keys-from-hashmap/39432604#39432604
        for (Map.Entry<String, ArrayList<Message>> entry : gM.getGestionMessages().entrySet()) {
            String key = entry.getKey();
            List<Message> tampon = entry.getValue();
            for (int i = 0; i < tampon.size(); i++) {
                if (tampon.get(i).getPseudo().equalsIgnoreCase(user)) {
                    listeSalons.add(key);
                    break;
                }
            }
        }
        return listeSalons;

    }

    // modification du pseudo, le nouveau pseudo passe en parametre sur l'URL
    @RequestMapping(value = "backoffice/users/{user}", method = RequestMethod.PUT, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void modifyUser(@PathVariable String user, @RequestParam String newPseudo) {
        GestionMessages gM = (GestionMessages) context.getAttribute("gM");
        GestionUtilisateurs gU = (GestionUtilisateurs) context.getAttribute("gU");

        //on verifie si l'utilisateur existe, le cas echeant on lance des exception
        if(!gU.getGestionUtilisateurs().contains(user)) {
            throw new CustomException("E404", "The user hasnt been added (yet)");
        }

        gU.nouveauUtilisateur(user);
        gU.supprimeUtilisateur(newPseudo);

        for (Map.Entry<String, ArrayList<Message>> entry : gM.getGestionMessages().entrySet()) {
            for (int i = 0; i < entry.getValue().size(); i++) {
                if (entry.getValue().get(i).getPseudo().equalsIgnoreCase(user)) {
                    entry.getValue().get(i).setPseudo(newPseudo);
                }
            }
        }
        context.setAttribute("gestionMessage", gM);
        context.setAttribute("gestionUtilisateurs", gU);
    }

    //pour un salon, Récupérer la liste des messages (on met le json sur l'url pour le differencier de l'affichage)
    @RequestMapping(value = "backoffice/salon/{salon}/json", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ArrayList<Message> listeMessagesSalon(@PathVariable String salon) {
        GestionMessages gM = (GestionMessages) context.getAttribute("gM");

        //on verifie si l'utilisateur existe, le cas echeant on lance des exception
        if(!gM.getGestionMessages().containsKey(salon)) {
            throw new CustomException("E404", "This salon hasnt been added (yet)");
        }
        ArrayList<Message> messagesSalon = new ArrayList<>();
        for (int i = 0; i < gM.getSalon(salon).size(); i++) {
            messagesSalon.add(gM.getSalon(salon).get(i));
        }
        return messagesSalon;
    }

    //pour un salon, Récupérer le nombre de messages
    @RequestMapping(value = "backoffice/salon/{salon}/nbMessages", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody int getNbMessagesSalon(@PathVariable String salon) {
        GestionMessages gM = (GestionMessages) context.getAttribute("gM");

        //on verifie si l'utilisateur existe, le cas echeant on lance des exception
        if(!gM.getGestionMessages().containsKey(salon)) {
            throw new CustomException("E404", "This salon hasnt been added (yet)");
        }

        int nbMessages = gM.getSalon(salon).size();
        return nbMessages;
    }

    //pour un salon, Récupérer tous les messages envoyés après un message donné, dont l'id sera passé en paramètre
    //a faire

    //pour un salon, ajouter un message
    @RequestMapping(value = "backoffice/salon/{salon}/NouveauMessage", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void NouveauMessage(@PathVariable String salon, @RequestParam(value = "pseudo") String pseudo, @RequestParam(value = "texte") String texte) {
        GestionMessages gM = (GestionMessages) context.getAttribute("gestionMessage");
        GestionUtilisateurs gU = (GestionUtilisateurs) context.getAttribute("gestionUtilisateurs");

        //on verifie si l'utilisateur existe, le cas echeant on lance des exception
        if(!gU.getGestionUtilisateurs().contains(pseudo)) {
            throw new CustomException("E404", "The user hasnt been added (yet)");
        }

        Message message = new Message(pseudo, texte, gM.getSalon(salon).size()+1);
        gM.getSalon(salon).add(message);
        context.setAttribute("gM", gM);
    }

    //pour un salon, suppriñer un salon
    @RequestMapping(value = "backoffice/salon/{salon}/delete", method = RequestMethod.DELETE, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void supprimeSalon(@PathVariable String salon) {
        GestionMessages gM = (GestionMessages) context.getAttribute("gM");

        //on verifie si l'utilisateur existe, le cas echeant on lance des exception
        if(!gM.getGestionMessages().containsKey(salon)) {
            throw new CustomException("E404", "This salon hasnt been added (yet)");
        }

        gM.supprimerSalon(salon);
    }

    //pour un message, Récupérer les informations du message
    @RequestMapping(value = "backoffice/salon/{salon}/message/{nbId}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Message infoMessage(@PathVariable String salon, @PathVariable int nbId) {
        GestionMessages gM = (GestionMessages) context.getAttribute("gM");

        //on verifie si l'utilisateur existe, le cas echeant on lance des exception
        if(!gM.getGestionMessages().containsKey(salon)) {
            throw new CustomException("E404", "This salon hasnt been added (yet)");
        }
        //on verifie si l'utilisateur existe, le cas echeant on lance des exception
        else if(gM.getSalon(salon).size() < nbId) {
            throw new CustomException("E404", "There is no message with this id");
        }

        Message message = gM.getMessage(salon, nbId);
        return message;
    }


    //pour un message, Modifier le contenu du dernier message d'un salon
    //a faire

    //pour un message, Supprimer le dernier message d'un salon
    //a faire

}

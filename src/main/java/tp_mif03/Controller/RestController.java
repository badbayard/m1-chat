package tp_mif03.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import tp_mif03.Model.GestionMessages;
import tp_mif03.Model.GestionUtilisateurs;
import tp_mif03.Model.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.*;

@org.springframework.web.bind.annotation.RestController
public class RestController {


    private ServletContext context;
//    on reprend le context du spring controller
    @Autowired
    public void SpringController(ServletContext context) {
        this.context = context;
    }

//  on recupere la liste des salon auxquels l'utilisateur a participé
    @RequestMapping(value = "/backoffice/users/{user}/salons", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<String> getListeSalonsUtilisateur(@PathVariable String user) throws Exception {
        GestionMessages gM = (GestionMessages) context.getAttribute("gM");
        GestionUtilisateurs gU = (GestionUtilisateurs) context.getAttribute("gU");

       //on verifie si l'utilisateur existe, le cas echeant on lance des exception
       if(!gU.getListeUtilisateurs().contains(user)) {
           throw new CustomException("E404", "The user is not on the user list");
       }

        //nous allons stocker les noms des salons auxquels l'utilisateur a participé
        List<String> listeSalons = new ArrayList<>();

        //idee extraite de https://stackoverflow.com/questions/16246821/how-to-get-values-and-keys-from-hashmap/39432604#39432604
        for (Map.Entry<String, ArrayList<Message>> entry : gM.getGestionMessages().entrySet()) {
            String key = entry.getKey();
            List<Message> tampon = entry.getValue();
            for (int i = 0; i < tampon.size(); i++) {
                if (tampon.get(i).getUsername().equalsIgnoreCase(user)) {
                    listeSalons.add(key);
                    break;
                }
            }
        }
        return listeSalons;

    }

    //  on recupere la liste des salons
    @RequestMapping(value = "/backoffice/salons", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<String> getSalons() {
        GestionMessages gM = (GestionMessages) context.getAttribute("gM");

        //nous allons stocker les noms des salons auxquels l'utilisateur a participé
        List<String> listeSalons = new ArrayList<>();

        //idee extraite de https://stackoverflow.com/questions/16246821/how-to-get-values-and-keys-from-hashmap/39432604#39432604
        for (Map.Entry<String, ArrayList<Message>> entry : gM.getGestionMessages().entrySet()) {
            String key = entry.getKey();
            listeSalons.add(key);
        }
        return listeSalons;

    }

    //  on recupere la liste des utilisateurs
    @RequestMapping(value = "/backoffice/users", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<String> getUsers() {
        GestionUtilisateurs gU = (GestionUtilisateurs) context.getAttribute("gU");

        return gU.getListeUtilisateurs();

    }

    // modification du pseudo, le nouveau pseudo passe en parametre sur l'URL
    @RequestMapping(value = "/backoffice/users/{user}", method = RequestMethod.PUT, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void modifyUser(@PathVariable String username, @RequestParam String newUsername) {
        GestionMessages gM = (GestionMessages) context.getAttribute("gM");
        GestionUtilisateurs gU = (GestionUtilisateurs) context.getAttribute("gU");

        //on verifie si l'utilisateur existe, le cas echeant on lance des exception
        if(!gU.getListeUtilisateurs().contains(username)) {
            throw new CustomException("E404", "The user hasnt been added (yet)");
        }

        gU.nouveauUtilisateur(username);
        gU.supprimeUtilisateur(newUsername);

        for (Map.Entry<String, ArrayList<Message>> entry : gM.getGestionMessages().entrySet()) {
            for (int i = 0; i < entry.getValue().size(); i++) {
                if (entry.getValue().get(i).getUsername().equals(username)) {
                    entry.getValue().get(i).setUsername(newUsername);
                }
            }
        }
        context.setAttribute("gestionMessage", gM);
        context.setAttribute("gestionUtilisateurs", gU);
    }

    //pour un salon, Récupérer la liste des messages (on met le json sur l'url pour le differencier de l'affichage)
    @RequestMapping(value = "/backoffice/salon/{salon}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity listeMessagesSalon(@PathVariable String salon) {
        GestionMessages gM = (GestionMessages) context.getAttribute("gM");

        //on verifie si l'utilisateur existe, le cas echeant on lance des exception
        if(!gM.getGestionMessages().containsKey(salon)) {
            throw new CustomException("E404", "This salon hasnt been added (yet)");
        }
        List<Message> messagesSalon = new ArrayList<>();
        for (int i = 0; i < gM.getSalon(salon).size(); i++) {
            messagesSalon.add(gM.getSalon(salon).get(i));
        }
//        return messagesSalon;
        return new ResponseEntity(messagesSalon, HttpStatus.OK);
    }

    //pour un salon, Récupérer le nombre de messages
    @RequestMapping(value = "/backoffice/salon/{salon}/nbMessages", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
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
    @RequestMapping(value = "/backoffice/salon/{salon}/NouveauMessage", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void NouveauMessage(@PathVariable String salon, @RequestParam(value = "username") String username, @RequestParam(value = "texte") String texte) {
        GestionMessages gM = (GestionMessages) context.getAttribute("gM");
        GestionUtilisateurs gU = (GestionUtilisateurs) context.getAttribute("gU");

        //on verifie si l'utilisateur existe, le cas echeant on lance des exception
        if(!gU.getListeUtilisateurs().contains(username)) {
            throw new CustomException("E404", "The user hasnt been added (yet)");
        }

        Message message = new Message(username, texte, gM.getSalon(salon).size()+1);
        gM.getSalon(salon).add(message);
        context.setAttribute("gM", gM);
    }

    //ajouter un salon
    @RequestMapping(value = "/backoffice/addsalon", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void addSalon(@RequestParam(value = "salon") String salon) {
        GestionMessages gM = (GestionMessages) context.getAttribute("gM");
        if(gM == null) {
            gM = new GestionMessages();
            gM.ajouterSalon(salon);
            context.setAttribute("gM", gM);
        }
        else if(gM.getSalon(salon) == null) {
            gM.ajouterSalon(salon);
            context.setAttribute("gM", gM);
        }
        else {
            context.setAttribute("gM", gM);
        }
    }

    //pour un salon, suppriñer un salon
    @RequestMapping(value = "/backoffice/salon/{salon}/delete", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody void deleteSalon(@PathVariable String salon) {
        GestionMessages gM = (GestionMessages) context.getAttribute("gM");

        //on verifie si l'utilisateur existe, le cas echeant on lance des exception
        if(!gM.getGestionMessages().containsKey(salon)) {
            throw new CustomException("E404", "This salon hasnt been added (yet)");
        }

        gM.supprimerSalon(salon);
    }

    //pour un message, Récupérer les informations du message
    @RequestMapping(value = "/backoffice/salon/{salon}/message/{nbId}", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
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

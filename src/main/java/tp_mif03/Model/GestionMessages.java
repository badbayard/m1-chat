package tp_mif03.Model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.String;

public class GestionMessages {
    private HashMap<String, ArrayList<Message>> listeSalons;

    public GestionMessages() {
        listeSalons = new HashMap<String, ArrayList<Message>>();
    }

    public void ajouterSalon(String nomSalon) {
        if(!listeSalons.containsKey(nomSalon)) {
            ArrayList<Message> listeMessage = new ArrayList<Message>();
            listeSalons.put(nomSalon, listeMessage);
        }
    }

    public void supprimerSalon(String nomSalon) {
        if(listeSalons.containsKey(nomSalon)) {
            listeSalons.remove(nomSalon);
        }
    }

    public ArrayList<Message> getSalon(String nomSalon) {
        return listeSalons.get(nomSalon);
    }

    public void addMsg(String nomSalon, String username, String message) {
        if(listeSalons.containsKey(nomSalon)) {
            int nbId = listeSalons.get(nomSalon).size();
            Message newMsg = new Message(username , message, nbId+1);
            listeSalons.get(nomSalon).add(newMsg);
        }
    }

    public Message getMessage(String salon, int nbId) {
        ArrayList<Message> test = listeSalons.get(salon);
        Message msg;
        if(nbId > test.size()) {
            msg = new Message("ERROR" , "theres is no message with this id", 0);
            return msg;
        }
        for(Message m : test){
            if(m.getNbId() == nbId) {
                msg = m;
                return msg;
            }
        }
        msg = new Message("ERROR" , "theres is no message with this id", 0);
        return msg;
    }

    public HashMap<String, ArrayList<Message>> getGestionMessages() {
        return listeSalons;
    }
}

package tp_mif03.bean;

import tp_mif03.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.String;

public class GestionMessages {
    private HashMap<String, ArrayList<Message>> sList;

    public GestionMessages() {
        sList = new HashMap<String, ArrayList<Message>>();
    }

    public void addSalon(String nomSalon) {
        if(!sList.containsKey(nomSalon)) {
            ArrayList<Message> listeMessage = new ArrayList<Message>();
            sList.put(nomSalon, listeMessage);
        }
    }

    public ArrayList<Message> getSalon(String nomSalon) {
        return sList.get(nomSalon);
    }

    public void addMsg(String nomSalon, String nomUser, String message) {
        Message newMsg = new Message(nomUser , message);
        sList.get(nomSalon).add(newMsg);
    }
}

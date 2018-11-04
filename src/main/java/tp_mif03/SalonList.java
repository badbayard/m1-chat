package tp_mif03;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.String;

public class SalonList {
    private HashMap<String, ArrayList<Message>> sList;

    public SalonList() {
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

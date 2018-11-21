package tp_mif03.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.String;

public class GestionMessages {
    private HashMap<String, ArrayList<Message>> sList;

    public GestionMessages() {
        sList = new HashMap<String, ArrayList<Message>>();
    }

    public void addSalon(String nomSalon) {
        System.out.println("flag1");
        if(!sList.containsKey(nomSalon)) {
            ArrayList<Message> listeMessage = new ArrayList<Message>();
            sList.put(nomSalon, listeMessage);
            ArrayList<Message> test = sList.get(nomSalon);
            if(test != null) {
                System.out.println(nomSalon);
            }
            else {
                System.out.println("je suis né mais en fait non");
            }
        }
    }

    public ArrayList<Message> getSalon(String nomSalon) {
        return sList.get(nomSalon);
    }

    public void addMsg(String nomSalon, String nomUser, String message) {

        ArrayList<Message> test = sList.get(nomSalon);
        if(test != null) {
            System.out.println(" i is not null " + nomSalon);
        }
        else {
            System.out.println("i is null " + nomSalon);
        }
        Message newMsg = new Message(nomUser , message);
        sList.get(nomSalon).add(newMsg);
        for (Message m : sList.get(nomSalon) )
        {
            System.out.println(m.toString());
        }
    }
}

package tp_mif03.Model;

public class Message {
    private String username;
    private String texte;
    private final int nbId;

    public Message(String username, String texte, int nbId) {
        this.username = username;
        this.texte = texte;
        this.nbId = nbId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String newUsername) {
        username = newUsername;
    }

    public String getTexte() {
        return texte;
    }

    public int getNbId() {
        return nbId;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    @Override
    public String toString() {
        return username+ " : " + texte ;
    }

}

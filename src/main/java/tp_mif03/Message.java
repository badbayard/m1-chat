package tp_mif03;

public class Message {
    private final String pseudo;
    private String texte;
    private String nom_salon;

    public Message(String pseudo, String texte) {
        this.pseudo = pseudo;
        this.texte = texte;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getNomSalon(){ return nom_salon ;}

    @Override
    public String toString() {
        return pseudo+ " : " + texte ;
    }
}

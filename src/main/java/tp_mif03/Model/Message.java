package tp_mif03.Model;

public class Message {
    private final String pseudo;
    private String texte;
    private final int nbId;
    private String nom_salon;

    public Message(String pseudo, String texte, int nbId) {
        this.pseudo = pseudo;
        this.texte = texte;
        this.nbId = nbId;
    }

    public String getPseudo() {
        return pseudo;
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

    public String getNomSalon(){ return nom_salon ;}

    @Override
    public String toString() {
        return pseudo+ " : " + texte ;
    }
}

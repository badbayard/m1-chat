public class Message {
    private final String pseudo;
    private String texte;

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
}

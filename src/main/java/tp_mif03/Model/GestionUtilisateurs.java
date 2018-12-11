package tp_mif03.Model;


import java.util.ArrayList;
import java.util.List;

public class GestionUtilisateurs {
    private List<String> listeUtilisateurs;

    public GestionUtilisateurs(){
        listeUtilisateurs = new ArrayList<>();
    }

    public void setListeUtilisateurs(List<String> listeUtilisateurs){
        this.listeUtilisateurs = listeUtilisateurs;
    }

    public List<String> getListeUtilisateurs(){return this.listeUtilisateurs;}


    public void nouveauUtilisateur(String utilisateur){
        if(!listeUtilisateurs.contains(utilisateur)) {
            listeUtilisateurs.add(utilisateur);
        }
    }

    public void supprimeUtilisateur(String utilisateur){
        listeUtilisateurs.remove(utilisateur);
    }

}

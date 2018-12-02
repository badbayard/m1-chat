package tp_mif03.Model;

import java.util.HashSet;
import java.util.Set;

public class GestionUtilisateurs {
    private Set<String> listeUtilisateurs;

    public GestionUtilisateurs(){
        listeUtilisateurs = new HashSet<>();
    }

    public void setListeUtilisateurs(Set<String> listeUtilisateurs){
        this.listeUtilisateurs = listeUtilisateurs;
    }

    public Set<String> getGestionUtilisateurs(){
        return listeUtilisateurs;
    }

    public void nouveauUtilisateur(String utilisateur){
        listeUtilisateurs.add(utilisateur);
    }

    public void supprimeUtilisateur(String utilisateur){
        listeUtilisateurs.remove(utilisateur);
    }
}

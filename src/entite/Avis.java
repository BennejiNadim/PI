/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author Rzouga
 */
public class Avis {
        
        private int id ;
        private String chxAvis ;
        private String contenu ;

    public Avis( String chxAvis, String contenu) {
      
        this.chxAvis = chxAvis;
        this.contenu = contenu;
    }

    public Avis() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChxAvis() {
        return chxAvis;
    }

    public void setChxAvis(String chxAvis) {
        this.chxAvis = chxAvis;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", chxAvis=" + chxAvis + ", contenu=" + contenu + '}';
    }
        
        
        
    
}

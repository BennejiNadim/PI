/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;



/**
 *
 * @author USER
 */
public class Vehicule  extends Equipement {
    

    public enum utilisation {
        NULL,
        vehicule_personnel,
        vehicule_livraison
    }
     static int Id_courant;
    private String matricule;
    private String couleur;
    private utilisation utilisation;
    private Integer km;

    public Vehicule(String matricule, String couleur, utilisation utilisation, int km, String nom, etat etat) {
        super(nom, etat);
        this.matricule = matricule;
        this.couleur = couleur;
        this.utilisation = utilisation;
        this.km = km;
      
    }
    

    public Vehicule(String nom, etat etat) {
        super(nom, etat);
        
    }
    
    public String getMatricule() {
        return matricule;
    }

    public String getCouleur() {
        return couleur;
    }

    public utilisation getUtilisation() {
        return utilisation;
    }
    

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setUtilisation(utilisation utilisation) {
        this.utilisation = utilisation;
    }

    public void setKM(Integer km) {
        this.km = km;
    }

    public Integer getKM() {
        return km;
    }

    @Override
    public String toString() {
        return "Vehicule{" + "id="+getId()+" , nom=" +getNom()+", matricule=" + matricule + ", couleur=" + couleur + ", utilisation=" + utilisation + ", km=" + km +", etat=" + getEtat()+ '}';
    }

  
    
    
    
    
}

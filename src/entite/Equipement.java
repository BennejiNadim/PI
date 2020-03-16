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
public class Equipement {
 public enum etat {
        NULL,
       disponnible,
       en_panne,
       vendu,
       corbeille,
       en_maintenance
    }
 private int id;
 private String nom;
 private etat etat;
 private etat etat0;
 private float prix;

    public Equipement(String nom, etat etat) {
        this.nom = nom;
        this.etat = etat;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }



    public etat getEtat0() {
        return etat0;
    }

    public void setEtat0(etat etat0) {
        this.etat0 = etat0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public etat getEtat() {
        
        return etat;
    }

    public void setEtat(etat etat) {
        etat0=this.etat;
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Equipement{" + "id=" + id + ", nom=" + nom + ", etat=" + etat + '}';
    }

    
 
}

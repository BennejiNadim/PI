/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author Djemaiel
 */
public class livraison {
   private int id_livaraison ;
    private int   id_commande ;
    private float  total;
    private String etat;
    private String trucking;
    public livraison() {
    }

    public livraison(int id_livaraison, int id_commande, float total, String etat) {
        this.id_livaraison = id_livaraison;
        this.id_commande = id_commande;
        this.total = total;
        this.etat = etat;
    }

    public String getTrucking() {
        return trucking;
    }

    public void setTrucking(String trucking) {
        this.trucking = trucking;
    }
    

    public int getId_livaraison() {
        return id_livaraison;
    }

    public int getId_commande() {
        return id_commande;
    }

    public float getTotal() {
        return total;
    }

    public String getEtat() {
        return etat;
    }

    public void setId_livaraison(int id_livaraison) {
        this.id_livaraison = id_livaraison;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "livraison{" + "id_livaraison=" + id_livaraison + ", id_commande=" + id_commande + ", total=" + total + ", etat=" + etat + ", trucking=" + trucking + '}';
    }
                           
}

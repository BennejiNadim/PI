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
public class Materiels extends Equipement {
   
    private Integer qte;

    public Materiels(String nom, etat etat) {
        super(nom, etat);
    }

    public Materiels(int qte , String nom, etat etat) {
        super(nom, etat);
        this.qte = qte;
    }

    public Integer  getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    @Override
    public String toString() {
        return "Materiels{" + "id="+getId()+ ", nom=" +getNom()+", etat=" + getEtat()+ ", qte=" + qte + '}';
    }




   
 
}

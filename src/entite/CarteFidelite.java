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
public class CarteFidelite {
    private int id;
    private String idUser;
    private int nbPoints;
    private String dateDebut;
    private String dateFin;
    private String type;

    public CarteFidelite( String idUser, int nbPoints, String dateDebut, String dateFin, String type) {
        this.id = id;
        this.idUser = idUser;
        this.nbPoints = nbPoints;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.type = type;
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(int nbPoints) {
        this.nbPoints = nbPoints;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CarteFidelite() {
    }

    @Override
    public String toString() {
        return "CarteFidelite{" + "id=" + id + ", idUser=" + idUser + ", nbPoints=" + nbPoints + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", type=" + type + '}';
    }
    
    
    
}

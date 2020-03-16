/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author USER
 */
public class HistoriqueEquipement {

    public HistoriqueEquipement() {
 
    }

    
    public enum operation {
        NULL,
    achat,
    vente,
    corbeille,
    panne,
    maintenance,
    disponnible;
}
    public enum equipement {
        NULL,
        vehicule,
        materiels
    }
 private int id;
 private Date date;
 private operation operation;
 private equipement eq;
 private Integer qte;
 private Integer id_m;
 private Integer id_v;
 private float prix;
private Integer id_f=0 ;



    public HistoriqueEquipement(Date date, operation operation, equipement eq, Integer qte, Integer id_m, Integer id_v, float prix) {
        this.date = date;
        this.operation = operation;
        this.eq = eq;
        this.qte = qte;
        this.id_m = id_m;
        this.id_v = id_v;
        this.prix = prix;
    }

    public Integer getId_f() {
        return id_f;
    }

    public void setId_f(Integer id_f) {
        this.id_f = id_f;
    }

 

    public HistoriqueEquipement(Date date, operation operation, equipement eq, Integer qte, Integer id_m, Integer id_v) {
        this.date = date;
        this.operation = operation;
        this.eq = eq;
        this.qte = qte;
        this.id_m = id_m;
        this.id_v = id_v;
    }
 

    public HistoriqueEquipement(Date date, operation operation, equipement eq, int qte,Float prix) {
        this.date = date;
        this.operation = operation;
        this.eq = eq;
        this.qte = qte;
        this.prix=prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public operation getOperation() {
        return operation;
    }

    public void setOperation(operation operation) {
        this.operation = operation;
    }

    
    public equipement getEq() {
        return eq;
    }

    public void setEq(equipement eq) {
        this.eq = eq;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public Integer getId_m() {
        return id_m;
    }

    public void setId_m(Integer id_m) {
        this.id_m = id_m;
    }

    public Integer getId_v() {
        return id_v;
    }

    public void setId_v(Integer id_v) {
        this.id_v = id_v;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

   

    @Override
    public String toString() {
        return "HistoriqueEquipement{" + "id=" + id + ", date=" + date + ", operation=" + operation + ", eq=" + eq + ", qte=" + qte + ", id_m=" + id_m + ", id_v=" + id_v + ", prix=" + prix + '}';
    }
    


   

    
 
}

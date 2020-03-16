/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import javafx.scene.control.CheckBox;

/**
 *
 * @author ASUS
 */
public class Marque {

    private int id_marque;
    private String nom_marque;
    private CheckBox ch_box;

    public Marque(int id_marque, String nom_marque) {
        this.id_marque = id_marque;
        this.nom_marque = nom_marque;
        this.ch_box=new CheckBox();
    }

    public int getId_marque() {
        return id_marque;
    }

    public String getNom_marque() {
        return nom_marque;
    }

    public void setId_marque(int id_marque) {
        this.id_marque = id_marque;
    }

    public void setNom_marque(String nom_marque) {
        this.nom_marque = nom_marque;
    }

    public CheckBox getCh_box() {
        return ch_box;
    }
    

}

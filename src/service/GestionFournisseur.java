/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Fournisseur;
import entite.MouvementProduit;
import util.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class GestionFournisseur {

    private Connection con = ConnexionBD.getinstance().getCnx();

    /**
     * ****************AJOUT
     * @param F
     */
    public void ajouterFournisseur(Fournisseur F) {
        Statement st;
        try {
            st = con.createStatement();
            String req = "insert into Fournisseur values(" + F.getId_fournisseur() + ",'" + F.getNom()
                    + "'," + F.getNumero() + ",'" + F.getEmail() + "')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(GestionFournisseur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * ******AFFICHAGE*****************
     */
    public void afficherFournisseurs() {
        PreparedStatement pt;
        try {
            pt = con.prepareStatement("select * from Fournisseur");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("Mouvement [id_fournisseur : " + rs.getInt(1) + ",nom : " + rs.getNString(2) + " , numero :" + rs.getInt(3) + " , Email : " + rs.getString(4) + " , destination : " + rs.getString(4) + "]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionFournisseur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * **************SUPPRIMER********************
     */
    public void supprimerFournisseur(int id) {
        PreparedStatement pt;
        try {
            pt = con.prepareStatement("delete from Fournisseur where id_fournisseur = ?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionFournisseur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * ***********UPDATE******************
     */
    public void modifierFournisseur(Fournisseur f) {
        PreparedStatement pt;
        try {
            pt = con.prepareStatement("update Fournisseur set nom = ? , numero = ? , Email = ?where id_fournisseur = ?");
            pt.setString(1, f.getNom());
            pt.setInt(2, f.getNumero());
            pt.setString(3, f.getEmail());
            pt.setInt(4, f.getId_fournisseur());
            pt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GestionFournisseur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Produit;
import entite.Produit;
import util.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class GestionProduit {

    private Connection con = ConnexionBD.getinstance().getCnx();

    /**
     * **********AJOUT***********************
     */
    public void ajouterProduit(Produit P) {
        Statement st;
        try {
            st = con.createStatement();
            String req = "insert into Produit values('" + P.getRef_produit() + "','" + P.getNom_produit() + "','" + P.getMarque() + "','" + P.getCategorie() + "'," + P.getQuantité_stock() + " , " + P.getQuantité_magasin() + " , " + P.getPrix_vente() + " , " + P.getPrix_achat() + ")";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(GestionProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * ************************Recherche Produit*************************************
     */
    public Produit rechercherProduit(int id) {
        PreparedStatement pt;
        Produit P = null;
        try {
            pt = con.prepareStatement("select * from Produit where ref_produit=" + id);
            ResultSet rs = pt.executeQuery();
            rs.next();
            P = new Produit(id, rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getFloat(7), rs.getFloat(8));
        } catch (SQLException ex) {
            Logger.getLogger(GestionProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

        return P;
    }

    public ObservableList<Produit> rechercherProduitParNom(String s) {
        ObservableList<Produit> ob = FXCollections.observableArrayList();
        PreparedStatement pt;
        Produit P = null;
        try {
            pt = con.prepareStatement("select * from Produit where nom_produit like '%" + s + "%'");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                P = new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getFloat(7), rs.getFloat(8));
                ob.add(P);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ob;
    }

    /**
     * ******AFFICHAGE********************
     */
    public ResultSet pdf() {

        ObservableList<Produit> ob = FXCollections.observableArrayList();
        PreparedStatement pt;
        ResultSet rs=null ;
        try {
            pt = con.prepareStatement("select * from Produit");
            rs = pt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(GestionProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ObservableList<Produit> afficherProduit() {
        ObservableList<Produit> ob = FXCollections.observableArrayList();
        PreparedStatement pt;
        ResultSet rs = null;
        try {
            pt = con.prepareStatement("select * from Produit");
            rs = pt.executeQuery();
            while (rs.next()) {
                ob.add(new Produit(rs.getInt("ref_produit"), rs.getString("nom_produit"), rs.getString("marque"), rs.getString("categorie"), rs.getInt("quantité_stock"), rs.getInt("quantité_magasin"), rs.getFloat("prix_vente"), rs.getFloat("prix_achat")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ob;
    }

    public ObservableList<Produit> afficherProduitParCategorie(String categorie) {
        ObservableList<Produit> ob = FXCollections.observableArrayList();
        PreparedStatement pt;
        Produit p;
        try {
            pt = con.prepareStatement("select * from Produit where categorie = '" + categorie + "'");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                p = new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
                ob.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ob;

    }

    public ObservableList<Produit> afficherProduitParMarque(String Marque) {
        ObservableList<Produit> ob = FXCollections.observableArrayList();
        PreparedStatement pt;
        try {
            pt = con.prepareStatement("select * from Produit where marque = '" + Marque + "'");
            ResultSet rs = pt.executeQuery();
            Produit p = new Produit();
            while (rs.next()) {
                p = new Produit(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
                ob.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ob;

    }

    public Boolean RechercherProduitParRef(int ref) {
        try {
            PreparedStatement pt;
            ResultSet rs = null;
            try {
                pt = con.prepareStatement("select * from Produit where ref_produit = " + ref);
                rs = pt.executeQuery();
            } catch (SQLException ex) {
                Logger.getLogger(GestionProduit.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
    
    public float getProduitParRef(int ref) {
        try {
            PreparedStatement pt;
            ResultSet rs = null;
            try {
                pt = con.prepareStatement("select prix_achat from Produit where ref_produit = " + ref);
                rs = pt.executeQuery();
            } catch (SQLException ex) {
                Logger.getLogger(GestionProduit.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;

    }

    /**
     * ************SUPPRESSION**********************
     */
    public void supprimerProduit(int ref) {
        PreparedStatement pt;
        try {
            pt = con.prepareStatement("delete from Produit where ref_produit = ?");
            pt.setInt(1, ref);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * ************MODIFICATION********************
     */
    public void modifierProduit(Produit p) {
        PreparedStatement pt;
        try {
            pt = con.prepareStatement("update produit set nom_produit = ? , marque = ? , categorie = ? , quantité_stock = ? , quantité_magasin = ? , prix_vente = ? , prix_achat = ?  where ref_produit = ?");
            pt.setString(1, p.getNom_produit());
            pt.setString(2, p.getMarque());
            pt.setString(3, p.getCategorie());
            pt.setInt(4, p.getQuantité_stock());
            pt.setInt(5, p.getQuantité_magasin());
            pt.setFloat(6, p.getPrix_vente());
            pt.setFloat(7, p.getPrix_achat());
            pt.setInt(8, p.getRef_produit());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

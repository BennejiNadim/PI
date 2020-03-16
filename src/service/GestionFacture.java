/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Facture;
import entite.Facture.etat;
import entite.Facture.typef;
import util.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Wael
 */
public class GestionFacture {

    private final Connection cnx;
    public ObservableList<Facture> Oblist = FXCollections.observableArrayList();

    public GestionFacture() {
        this.cnx = ConnexionBD.getinstance().getCnx();

    }

    public void ajouterFacture(Facture f) {
        Statement st;
        try {
            st = cnx.createStatement();

            String req = "insert into Facture values(" + f.getId() + ",'" + f.getDateFacturation() + "','" + f.getEtatFacture() + "','" + f.getMontant() + "','" + f.getClientLogin() + "','" + f.getSupplierId() + "','" + f.getEmployeLogin() + "','" + f.getTypeFacture() + "')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(GestionFacture.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateFactureList();

    }

    public void afficherFacture() {
        try {
            PreparedStatement pt = cnx.prepareStatement("select * from Facture");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {

                if (rs.getString(8).startsWith("achat")) {
                    System.out.println("Facture [ id: " + rs.getInt(1) + " dateFacturation: " + rs.getDate(2) + " etatFacture: " + rs.getString(3) + " Monton: " + rs.getBigDecimal(4) + " Supplier: " + rs.getInt(6) + " Type: " + rs.getString(8) + "]");
                } else if (rs.getString(8).startsWith("vente")) {
                    System.out.println("Facture [ id: " + rs.getInt(1) + " dateFacturation: " + rs.getDate(2) + " etatFacture: " + rs.getString(3) + " Monton: " + rs.getBigDecimal(4) + " Client: " + rs.getString(5) + " Type: " + rs.getString(8) + "]");
                } else if (rs.getString(8).equals("salaire")) {
                    System.out.println("Facture [ id: " + rs.getInt(1) + " dateFacturation: " + rs.getDate(2) + " etatFacture: " + rs.getString(3) + " Monton: " + rs.getBigDecimal(4) + " Employe: " + rs.getString(7) + " Type: " + rs.getString(8) + "]");
                } else {
                    System.out.println("Facture [ id: " + rs.getInt(1) + " dateFacturation: " + rs.getDate(2) + " etatFacture: " + rs.getString(3) + " Monton: " + rs.getBigDecimal(4) + " Type: " + rs.getString(8) + "]");
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionFacture.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifierFacture(int id, Facture f) {
        PreparedStatement pt;
        try {
            pt = cnx.prepareStatement("update Facture set dateFacturation = ?, etatFacture = ?, montant = ?, ClientLogin = ?, SupplierId = ?, EmployeLogin = ?, typeFacture = ?  where id = ?");
            pt.setDate(1, f.getDateFacturation());
            pt.setString(2, f.getEtatFacture().toString());
            pt.setBigDecimal(3, f.getMontant());
            if (f.getClientLogin() != null) {
                pt.setString(4, f.getClientLogin());
            } else {
                pt.setString(4, "");
            }
            pt.setInt(5, f.getSupplierId());
            if (f.getEmployeLogin() != null) {
                pt.setString(6, f.getEmployeLogin());
            } else {
                pt.setString(6, "");
            }
            pt.setString(7, f.getTypeFacture().toString());
            pt.setInt(8, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionFacture.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateFactureList();

    }

    public void supprimerFacture(int id) {
        PreparedStatement pt;
        try {
            pt = cnx.prepareStatement("delete from Facture where id = ?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionFacture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateFactureList() {
        try {
            PreparedStatement pt = cnx.prepareStatement("select * from Facture");
            ResultSet rs = pt.executeQuery();
            Oblist.clear();
            while (rs.next()) {

                etat et;

                if (rs.getString(3).equals("payed")) {
                    et = etat.payed;
                } else {
                    et = etat.not_payed;
                }
                Facture f = new Facture(et, rs.getBigDecimal(4), rs.getString(5), rs.getString(7), rs.getInt(6), StringtoTypef(rs.getString(8)));
                f.setId(rs.getInt(1));
                f.setDateFacturation(rs.getDate(2));
                if(f.getId()!=0)
                Oblist.add(f);

            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionFacture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private typef StringtoTypef(String ch) {
        switch (ch) {
            case "vente_produit":
                return Facture.typef.vente_produit;

            case "achat_produit":
                return Facture.typef.achat_produit;

            case "vente_materiel":
                return Facture.typef.vente_materiel;

            case "achat_materiel":
                return Facture.typef.achat_materiel;

            case "taxe":
                return Facture.typef.taxe;

            case "salaire":
                return Facture.typef.salaire;

            case "in":
                return Facture.typef.in;

            case "out":
            default:
                return Facture.typef.out;
        }

    }
}

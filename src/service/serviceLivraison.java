/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import entite.livraison;
import java.util.ArrayList;
import util.ConnexionBD;

/**
 *
 * @author Djemaiel
 */
public class serviceLivraison {

    private Connection con = ConnexionBD.getinstance().getCnx();

    public void ajouterlivraison(livraison l) {
        try {
            Statement st = con.createStatement();
            String req = "insert into livraison values(" + l.getId_livaraison() + ",'" + l.getEtat() + "'," + l.getTotal() + "," + l.getId_commande() + ",'"+l.getTrucking()+"')";

            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(serviceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void afficherlivraison() {
        try {
            PreparedStatement pt = con.prepareStatement("select * from livraison");
            ResultSet rs = pt.executeQuery();

            while (rs.next()) {
                System.out.println("livraison [id:" + rs.getInt(1) + " ,commande: " + rs.getInt(2) + ", total" + rs.getFloat(3) + ",etat: '" + rs.getString(4) + "']");
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifierlivraison(int id, String nom) {
        try {
            PreparedStatement pt = con.prepareStatement("update livraison set etat= ? where id_livraison=?");
            pt.setString(1, nom);
            pt.setInt(2, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerlivraison(livraison l) {
        try {
            PreparedStatement pt = con.prepareStatement("delete from livraison where id_livraison = ?");
            pt.setInt(1, l.getId_livaraison());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<livraison> getlivraison() {
        ArrayList<livraison> liv = new ArrayList();
        try {
            PreparedStatement pt = con.prepareStatement("select * from livraison");
            ResultSet rs = pt.executeQuery();

            while (rs.next()) {
                livraison l = new livraison();
                l.setEtat(rs.getString(2));
                l.setTotal(rs.getFloat(3));
                l.setId_livaraison(rs.getInt(4));
                l.setId_livaraison(rs.getInt(1));
                l.setTrucking(rs.getString(5));
                liv.add(l);
                //    System.out.println("livraison [id:"+rs.getInt(1)+" ,commande: "+rs.getInt(2)+", total"+rs.getFloat(3)+",etat: '"+rs.getString(4)+"']");

            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liv;

    }

    static String getAlphaNumericString() {

        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(12);

        for (int i = 0; i < 12; i++) {

            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb 
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

}

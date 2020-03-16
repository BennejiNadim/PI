/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.CarteFidelite;
import entite.NewEntity;
import entite.User;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ConnexionBD;

/**
 *
 * @author Djemaiel
 */
public class serviceCarteFid {

    private Connection con = ConnexionBD.getinstance().getCnx();

    public void ajoutercarte(CarteFidelite c) {
        try {
            Statement st = con.createStatement();
            String req = "INSERT INTO `carte_fidelite` (`Id_user`, `nb_point`, `date_debut`, `date_fin`, `type`) VALUES ('" + c.getIdUser() + "', '" + c.getNbPoints() + "', '" + c.getDateDebut() + "', '" + c.getDateFin() + "', '" + c.getType() + "');";

            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(serviceCarteFid.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void affichercarte() {
        try {
            PreparedStatement pt = con.prepareStatement("select * from carte_fidelite");
            ResultSet rs = pt.executeQuery();

            while (rs.next()) {
                System.out.println("carte [id:" + rs.getInt(1) + " ,user: " + rs.getInt(2) + ", nb point  '" + rs.getInt(3) + "',date debut : '" + rs.getString(4) + "' date fin  " + rs.getString(5) + "type '" + rs.getString(6) + "']");
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceCarteFid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifiercarte(int id, int nb_pt) {
        try {
            PreparedStatement pt = con.prepareStatement("update carte_fidelite set  nb_point =? where id_CF=?");
            pt.setInt(1, nb_pt);
            pt.setInt(2, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceCarteFid.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimercarte(CarteFidelite l) {
        try {
            PreparedStatement pt = con.prepareStatement("delete from carte_fidelite where id_CF= ?");
            pt.setInt(1, l.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(serviceLivraison.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<CarteFidelite> getAll() {
        ArrayList<CarteFidelite> array = new ArrayList();
        try {
            PreparedStatement pt = con.prepareStatement("select * from carte_fidelite");
            ResultSet rs = pt.executeQuery();

            while (rs.next()) {
                CarteFidelite c = new CarteFidelite();
                c.setId(rs.getInt(1));
                c.setId(rs.getInt("Id_CF"));
                c.setDateDebut(rs.getString(4));
                c.setDateFin(rs.getString(5));
                c.setIdUser(rs.getString(2));
                c.setType(rs.getString(6));
                c.setNbPoints(rs.getInt(3));
                array.add(c);
                //  System.out.println("carte [id:"+rs.getInt(1)+" ,user: "+rs.getInt(2)+", nb point  '"+rs.getInt(3)+"',date debut : '"+rs.getString(4)+"' date fin  "+rs.getString(5)+"type '"+rs.getString(6)+"']");
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceCarteFid.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }

    public int nbreCarte() {
        try {
            PreparedStatement pt = con.prepareStatement("select count(*) as total from carte_fidelite");
            ResultSet rs = pt.executeQuery();

            while (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceCarteFid.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<NewEntity> getNomUser() {
        ArrayList<NewEntity> array = new ArrayList();

        try {

            PreparedStatement pt = con.prepareStatement("select * from user u right join carte_fidelite cf on cf.Id_user=u.login");
            ResultSet rs = pt.executeQuery();

            while (rs.next()) {
                NewEntity n = new NewEntity();
                User u = new User();
                CarteFidelite cf = new CarteFidelite();
                cf.setId(rs.getInt("Id_CF"));
                u.setNom(rs.getString("nom"));
                u.setEmail(rs.getString("email"));
                cf.setType(rs.getString("type"));
                cf.setDateDebut(rs.getString("date_debut"));
                cf.setDateFin(rs.getString("date_fin"));
                cf.setNbPoints(rs.getInt("nb_point"));

                n.setUser(u);
                n.setCartefidelite(cf);
                array.add(n);
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceCarteFid.class.getName()).log(Level.SEVERE, null, ex);
        }

        return array;
    }

    public ArrayList<NewEntity> CarteUtiliser() {
        ArrayList<NewEntity> array = new ArrayList();

        try {

            PreparedStatement pt = con.prepareStatement("select * from user u inner join carte_fidelite cf on cf.Id_user=u.login");

            ResultSet rs = pt.executeQuery();

            while (rs.next()) {
                NewEntity n = new NewEntity();
                User u = new User();
                CarteFidelite cf = new CarteFidelite();
                cf.setId(rs.getInt("Id_CF"));
                u.setNom(rs.getString("nom"));
                u.setEmail(rs.getString("email"));
                cf.setType(rs.getString("type"));
                cf.setDateDebut(rs.getString("date_debut"));
                cf.setDateFin(rs.getString("date_fin"));
                cf.setNbPoints(rs.getInt("nb_point"));

                n.setUser(u);
                n.setCartefidelite(cf);
                array.add(n);
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceCarteFid.class.getName()).log(Level.SEVERE, null, ex);
        }

        return array;

    }

    public ArrayList<NewEntity> CarteStock() {
        ArrayList<NewEntity> array = new ArrayList();

        try {

            PreparedStatement pt = con.prepareStatement("select * from carte_fidelite where Id_user=?");
             pt.setString(1, "null");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                NewEntity n = new NewEntity();
                CarteFidelite cf = new CarteFidelite();
                cf.setId(rs.getInt("Id_CF"));
                cf.setType(rs.getString("type"));
                cf.setDateDebut(rs.getString("date_debut"));
                cf.setDateFin(rs.getString("date_fin"));
                cf.setNbPoints(rs.getInt("nb_point"));
                n.setCartefidelite(cf);
                array.add(n);
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceCarteFid.class.getName()).log(Level.SEVERE, null, ex);
        }

        return array;
    }
}

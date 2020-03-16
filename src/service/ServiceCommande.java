/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Commande;
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
public class ServiceCommande {
      private Connection con=ConnexionBD.getinstance().getCnx();
    
    public void ajouterComande (Commande l){
        try {
            Statement st = con.createStatement();
            String req = "INSERT INTO `commande` ( id_user, date,total) VALUES ( '"+l.getId_user()+"', '"+l.getDate()+"',"+l.getTotal()+")";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int derniereCommande(){
            int c = 0;
        try {
            PreparedStatement pt =con.prepareStatement("select * from commande order by id desc limit 1");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
                c = rs.getInt(1);
//System.out.println("commande [id:"+rs.getInt(1)+" ,date: "+rs.getString(2)+",id_user: "+rs.getInt(2)+"]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }        
            return c;
    }
    
    public void afficherCommande (int id_user) {
        try {
            PreparedStatement pt =con.prepareStatement("select * from commande where id_user="+id_user);
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
                System.out.println("commande [id:"+rs.getInt(1)+" ,date: "+rs.getString(2)+",id_user: "+rs.getInt(2)+"]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifierCommande (int id,float total){
        try {
            PreparedStatement pt = con.prepareStatement("update commande set id_user= ? where id=?");
            pt.setFloat(1,total);
            pt.setInt(2,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerCommande (Commande c) {
        try {
            PreparedStatement pt =con.prepareStatement("delete from commande where id = ?");
            pt.setInt(1, c.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public ArrayList<Commande> getCommande () {
         ArrayList<Commande> array=new ArrayList();
        try {
            PreparedStatement pt =con.prepareStatement("select * from commande where id_user="+2);
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
              //  System.out.println("commande [id:"+rs.getInt(1)+" ,date: "+rs.getString(2)+",id_user: "+rs.getInt(2)+"]");
              Commande c=new Commande();
              c.setDate(rs.getString(3));
            c.setTotal(rs.getFloat(4));
            c.setId(rs.getInt(1));
            array.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        return array;
    }
}
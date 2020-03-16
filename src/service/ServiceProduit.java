/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ConnexionBD;

/**
 *
 * @author Djemaiel
 */
public class ServiceProduit {
      private Connection con=ConnexionBD.getinstance().getCnx();
      
    public int prixProduit(int id){
            int c = 0;
        try {
            PreparedStatement pt =con.prepareStatement("select prix_vente from produit where ref_produit = "+id);
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
    public ArrayList<Produit> getProduit(){
        ArrayList<Produit> p=new ArrayList();
        try {
            PreparedStatement pt =con.prepareStatement("select * from produit");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
                Produit produit=new Produit();
                produit.setRef_produit(rs.getInt(1));
                 produit.setNom_produit(rs.getString(2));
                  produit.setPrix_vente(rs.getFloat("prix_vente"));
                
                        
                p.add(produit);
//System.out.println("commande [id:"+rs.getInt(1)+" ,date: "+rs.getString(2)+",id_user: "+rs.getInt(2)+"]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }        
            return p;
    }
    
}

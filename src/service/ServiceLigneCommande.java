/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.LigneCommmande;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ConnexionBD;

/**
 *
 * @author Djemaiel
 */
public class ServiceLigneCommande {
       private Connection con=ConnexionBD.getinstance().getCnx();
    
    public void ajouterLigneComande (LigneCommmande l){
        try {
            Statement st = con.createStatement();
            String req = "INSERT INTO ligne_commande ( `quantite`, `Id_commande`, `id_produit`) VALUES ('"+l.getQuantite()+"', '"+l.getId_commande()+"', '"+l.getId_produit()+"');";
            
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void afficherCommande (int id_user) {
        try {
            PreparedStatement pt =con.prepareStatement("select * from commande where id_user="+id_user);
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
                System.out.println("commande [id:"+rs.getInt(1)+" ,total: "+rs.getFloat(2)+",id_user: "+rs.getInt(3)+"]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifierCommande (int id,float total){
        try {
            PreparedStatement pt = con.prepareStatement("update commande set total= ? where id=?");
            pt.setFloat(1,total);
            pt.setInt(2,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerCommande (LigneCommmande lc) {
        try {
            PreparedStatement pt =con.prepareStatement("delete from commande where id = ?");
            pt.setInt(1, lc.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

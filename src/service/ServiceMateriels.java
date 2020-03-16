 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Equipement;
import entite.Equipement.etat;
import entite.HistoriqueEquipement;
import entite.Materiels;
import util.ConnexionBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Material;
import javax.swing.text.html.HTML;

/**
 *
 * @author USER
 */
public class ServiceMateriels {
     private Connection con = ConnexionBD.getinstance().getCnx();
      ServiceEquipement se = new ServiceEquipement();
   ServiceHistoriqueEquipement sh = new ServiceHistoriqueEquipement();
     Calendar c1 = Calendar.getInstance();
     Date d = new Date (c1.getTimeInMillis());
     
              
     public void ajouterMateriels (Materiels m) {   
       HistoriqueEquipement h=   new HistoriqueEquipement(d, HistoriqueEquipement.operation.achat, HistoriqueEquipement.equipement.materiels, m.getQte(),m.getPrix());
       if (!m.getEtat().equals(etat.disponnible)){
           h.setOperation(sh.etatToOperation(m.getEtat()));
       }
       Materiels mc = new Materiels("NULL", etat.NULL);
       mc = chercheParNomEtat(m.getNom(), m.getEtat());
         
       if (mc.getNom().equals("NULL")) {
         try {
             Statement st = con.createStatement();
             String req = "insert into materiels values (" +m.getId()+ ",'"+m.getNom()+"','"+m.getQte()+"','"+ m.getEtat()+"')";
             st.executeUpdate(req);
              h.setId_v(0);
           Materiels m0=   chercheParNomEtat(m.getNom(), m.getEtat());
           System.out.println(m0);
            h.setId_m(m0.getId());                 
          sh.ajouterHistoriqueEquipemnt(h);
         } catch (SQLException ex) {
             Logger.getLogger(ServiceMateriels.class.getName()).log(Level.SEVERE, null, ex);
         }
       }
       else {
           System.out.println("Materiels déja ajouter");
        }
}
  public boolean checkNomEtat (String nom , etat e){
         Materiels m = new Materiels("NULL", etat.NULL);      
 try {    Statement st = con.createStatement();
          PreparedStatement pt =con.prepareStatement("select * from materiels where nom = ? and etat = ?");  
          pt.setString(1, nom);
          pt.setString(2,e.toString());
          ResultSet rest = pt.executeQuery();
           while (rest.next()) {
              m.setId(rest.getInt("id"));
              m.setNom(rest.getString("nom"));
              m.setQte(rest.getInt("qte"));
              m.setEtat(se.stringToEtat(rest.getString("etat"))); 
           }
           if (!m.getNom().equals("NULL")) {
               return true;
           }
      } catch (SQLException ex) {
          Logger.getLogger(ServiceVehicule.class.getName()).log(Level.SEVERE, null, ex);
      }
//      
      
      return false;
  }
     
     public Materiels chercheParNomEtat(String nom,etat e) {      
          
         Materiels m = new Materiels("NULL", etat.NULL);      
 try {    Statement st = con.createStatement();
          PreparedStatement pt =con.prepareStatement("select * from materiels where nom = ? and etat = ?");  
          pt.setString(1, nom);
          pt.setString(2,e.toString());
          ResultSet rest = pt.executeQuery();
           while (rest.next()) {
              m.setId(rest.getInt("id"));
              m.setNom(rest.getString("nom"));
              m.setQte(rest.getInt("qte"));
              m.setEtat(se.stringToEtat(rest.getString("etat"))); 
           }
      } catch (SQLException ex) {
          Logger.getLogger(ServiceVehicule.class.getName()).log(Level.SEVERE, null, ex);
      }
//      
        return m;
    }
      public ObservableList <Materiels> afficherMateriels () {
           ObservableList obm = FXCollections.observableArrayList();
         try {
             PreparedStatement pt =con.prepareStatement("select * from `materiels`");
             ResultSet rs = pt.executeQuery();             
             while(rs.next()){
                 Materiels m = new Materiels("", etat.NULL);
                            
                m.setId(rs.getInt(1));
              m.setNom(rs.getString(2));
             m.setQte(rs.getInt(3));
             m.setEtat(se.stringToEtat(rs.getString(4)));
             if(m.getId()!=0)
              obm.add(m);
               
             }
         } catch (SQLException ex) {
             Logger.getLogger(ServiceMateriels.class.getName()).log(Level.SEVERE, null, ex);
         }
         return obm;
      }
      
      
       public void modifierMateriels (int id, Materiels m){  
         try {
             PreparedStatement pt = con.prepareStatement("update materiels set nom = ?, qte = ?, etat = ? where id=?");
             pt.setString(1,m.getNom());
             pt.setInt(2,m.getQte());
             pt.setString(3, m.getEtat().toString());
             pt.setInt(4, id);
             pt.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(ServiceMateriels.class.getName()).log(Level.SEVERE, null, ex);
         }
       }
         
       
       
         public Materiels chercheParId(int id) {                
         Materiels m =new Materiels("", etat.NULL);         
 try {    Statement st = con.createStatement();
          PreparedStatement pt =con.prepareStatement("select * from materiels where id = ? ");  
          pt.setInt(1, id);
          ResultSet rest = pt.executeQuery();
           while (rest.next()) {
              m.setId(rest.getInt("id"));
              m.setNom(rest.getString("nom"));
              m.setQte(rest.getInt("qte"));
              m.setEtat(se.stringToEtat(rest.getString("etat"))); 
           }
      } catch (SQLException ex) {
          Logger.getLogger(ServiceVehicule.class.getName()).log(Level.SEVERE, null, ex);
      }     
        return m;
    }
         
         
         
       public void supprimerMateriels(int id) {  
           sh.supprimerHM(id);
         try {
             PreparedStatement pt =con.prepareStatement("delete from materiels where id = ?");
             pt.setInt(1, id);
             pt.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(ServiceMateriels.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
      
       
       public void changerEtat (int id , Integer qte , Equipement.etat e,Float p){
           Materiels m0 = chercheParId(id);
           Materiels m1 = chercheParNomEtat(m0.getNom(), e);
           if (!(m0.getEtat().equals(Equipement.etat.NULL))) {
               if (!(m1.getEtat().equals(Equipement.etat.NULL))) {
            int qte1 = m0.getQte()-qte;
            int qte2 = m1.getQte()+qte;
                 if (qte2 >= 0) {
                        m0.setQte(qte1);
                        modifierMateriels(id, m0);                       
                        m1.setQte(qte2);
                        modifierMateriels(m1.getId(), m1);
                        System.out.println(sh.etatToOperation(m1.getEtat()));
                         HistoriqueEquipement h1 = new HistoriqueEquipement(d, sh.etatToOperation(m1.getEtat()), HistoriqueEquipement.equipement.materiels, qte, p);
                   h1.setId_m(id);
                   h1.setId_v(0);
                        sh.ajouterHistoriqueEquipemnt(h1);
                        System.out.println("done if");
                    }
                    else {
                         System.out.println("mech mawjoud");
                    }
               }
               else {
                   int qte1 = m0.getQte()-qte;
                   m0.setQte(qte1);
                   modifierMateriels(id, m0);  
                   Materiels m = new Materiels(qte, m0.getNom(), e);
                   m.setPrix(p);
                   ajouterMateriels(m);
                  
                   System.out.println("done else");
               }         
           }
           else {
               System.out.println("Id incorrect");
           }
       }
       

}
     


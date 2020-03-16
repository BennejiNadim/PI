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
import entite.Vehicule;
import util.ConnexionBD;
import com.sun.javafx.collections.ElementObservableListDecorator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.String;
import java.sql.Date;
import static java.sql.Types.NULL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.omg.CosNaming.NamingContextPackage.NotFound;
/**
 *
 * @author USER
 */
public class ServiceVehicule {
     
  private Connection con = ConnexionBD.getinstance().getCnx();
  ServiceEquipement se = new ServiceEquipement();
   ServiceHistoriqueEquipement sh = new ServiceHistoriqueEquipement();
   Calendar c1 = Calendar.getInstance();
       Date d = new Date (c1.getTimeInMillis());
  
  public void ajouterVehicule (Vehicule v) {  
     
             HistoriqueEquipement h=   new HistoriqueEquipement (d, HistoriqueEquipement.operation.achat, HistoriqueEquipement.equipement.vehicule, 1,v.getPrix()); 
      try {
          
             Statement st = con.createStatement();
             String req = "insert into `vehicule` values(" +v.getId()+ ",'"+v.getNom()+"','"+v.getMatricule()+"','"+v.getCouleur()+"','"+v.getUtilisation()+"','"+v.getKM()+"','"+v.getEtat()+"')";
             st.executeUpdate(req);
             Vehicule v0 = chercheParMatricule(v.getMatricule());
             h.setId_v(v0.getId());
             h.setId_m(0);         
             sh.ajouterHistoriqueEquipemnt(h);           
      } catch (SQLException ex) {
          Logger.getLogger(ServiceVehicule.class.getName()).log(Level.SEVERE, null, ex);
      } }
  

  public ObservableList <Vehicule> afficherVehicule () {
     ObservableList obv = FXCollections.observableArrayList();
        try {
            PreparedStatement pt =con.prepareStatement("select * from vehicule");
            
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
                Vehicule vh = new Vehicule("", etat.NULL);
                            
                vh.setId(rs.getInt(1));
              vh.setNom(rs.getString(2));
             vh.setMatricule(rs.getString(3));
             vh.setCouleur(rs.getString(4));
              vh.setUtilisation(stringToUtilisation(rs.getString(5)));
              vh.setKM(rs.getInt(6));
               
              vh.setEtat(se.stringToEtat(rs.getString(7)));
              if(vh.getId()!=0)
              obv.add(vh);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obv;
    }
  public boolean checkMatricule (String matricule) {
      Vehicule vh =new Vehicule("NULL",etat.NULL);
      
 try {    Statement st = con.createStatement();
          PreparedStatement pt =con.prepareStatement("select * from vehicule where matricule = ? ");  
          pt.setString(1, matricule);
          ResultSet rest = pt.executeQuery();
           while (rest.next()) {
              vh.setId(rest.getInt("id"));
              vh.setNom(rest.getString("nom"));
              vh.setMatricule(rest.getString("matricule"));
              vh.setCouleur(rest.getString("couleur"));
              vh.setUtilisation(stringToUtilisation(rest.getString("utilisation")));
              vh.setKM(rest.getInt("km"));
              vh.setEtat(se.stringToEtat(rest.getString("etat")));
           }
          if (!vh.getNom().equals("NULL")) {
              return true;
          }
      } catch (SQLException ex) {
          Logger.getLogger(ServiceVehicule.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      return false ; 
  }
   
  public Vehicule chercheParMatricule(String matricule) {
         
 
    Vehicule vh =new Vehicule("NULL",etat.NULL);
      
 try {    Statement st = con.createStatement();
          PreparedStatement pt =con.prepareStatement("select * from vehicule where matricule = ? ");  
          pt.setString(1, matricule);
          ResultSet rest = pt.executeQuery();
           while (rest.next()) {
              vh.setId(rest.getInt("id"));
              vh.setNom(rest.getString("nom"));
              vh.setMatricule(rest.getString("matricule"));
              vh.setCouleur(rest.getString("couleur"));
              vh.setUtilisation(stringToUtilisation(rest.getString("utilisation")));
              vh.setKM(rest.getInt("km"));
              vh.setEtat(se.stringToEtat(rest.getString("etat")));
           }
      } catch (SQLException ex) {
          Logger.getLogger(ServiceVehicule.class.getName()).log(Level.SEVERE, null, ex);
      }
//      
        return vh;
    }
  public Vehicule chercheParId(int id) {
         
 
    Vehicule vh =new Vehicule("NULL",etat.NULL);
      
 try {    Statement st = con.createStatement();
          PreparedStatement pt =con.prepareStatement("select * from vehicule where id = ? ");  
          pt.setInt(1, id);
          ResultSet rest = pt.executeQuery();
           while (rest.next()) {
              vh.setId(rest.getInt("id"));
              vh.setNom(rest.getString("nom"));
              vh.setMatricule(rest.getString("matricule"));
              vh.setCouleur(rest.getString("couleur"));
              vh.setUtilisation(stringToUtilisation(rest.getString("utilisation")));
              vh.setKM(rest.getInt("km"));
              vh.setEtat(se.stringToEtat(rest.getString("etat")));
           }
      } catch (SQLException ex) {
          Logger.getLogger(ServiceVehicule.class.getName()).log(Level.SEVERE, null, ex);
      }
//      
        return vh;
    }
  
  public Vehicule.utilisation stringToUtilisation (String u) {
      if (u.equals("")){
          return Vehicule.utilisation.NULL;
      }
      if (u.equals("vehicule_personnel")) {
          return Vehicule.utilisation.vehicule_personnel;
      }
     return Vehicule.utilisation.vehicule_livraison;
          }
  
  
  public void modifierVehicule (int id, Vehicule v){
      
       
      HistoriqueEquipement h = new HistoriqueEquipement(d, HistoriqueEquipement.operation.achat, HistoriqueEquipement.equipement.vehicule, 1,v.getPrix());
        try {          
            PreparedStatement pt = con.prepareStatement("update vehicule set nom = ?, matricule = ?, couleur = ?, utilisation = ?, KM = ?, etat = ? where id=?");
            pt.setString(1,v.getNom());
            pt.setString(2,v.getMatricule());
            pt.setString(3,v.getCouleur());
            pt.setString(4, v.getUtilisation().toString());
            pt.setFloat(5, v.getKM());
            pt.setString(6, v.getEtat().toString());
            pt.setInt(7, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  public void changerEtat (int id , Equipement.etat e,Float p) {
      try {
          PreparedStatement pt = con.prepareStatement("update vehicule set etat = ? where id=?");
          pt.setString(1,e.toString());
          pt.setInt(2,id);
          pt.executeUpdate();
          HistoriqueEquipement h=   new HistoriqueEquipement(d, sh.etatToOperation(e), HistoriqueEquipement.equipement.vehicule, 1,p);
          h.setId_v(id);
          h.setId_m(0);
          sh.ajouterHistoriqueEquipemnt(h);
          
      } catch (SQLException ex) {
          Logger.getLogger(ServiceVehicule.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
   public void supprimerVehicule (int id) {
        sh.supprimerHV(id);
        try {
            PreparedStatement pt =con.prepareStatement("delete from vehicule where id = ?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceVehicule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  
}

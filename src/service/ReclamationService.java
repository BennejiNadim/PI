/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entite.Reclamation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ConnexionBD;

/**
 *
 * @author Rzouga
 */
public class ReclamationService {
    
    
    
        ConnexionBD db = ConnexionBD.getinstance();
        Connection cn = db.getCnx();

    public ReclamationService() {
    }
    
    
      public boolean  AjouterReclamation(Reclamation rec){
       
         int verf = 0 ;
        try{
        String req ;
        
        req="INSERT INTO `reclamation`(`nomuser`, `numero`, `date`, `image`) VALUES (?,?,?,?)";
        PreparedStatement res=cn.prepareStatement(req);
        
        res.setString(1, rec.getNom());
        res.setInt(2, rec.getNumero());
        res.setDate(3, (Date) rec.getDate());
        res.setString(4, rec.getImage());
             
        
        verf=res.executeUpdate();
         
        
        }
        catch(SQLException e ){
        Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE,null,e);
        
        }
        if (verf==0)
        {return false;}
        else {return true;}
    }
      
      
        public List<Reclamation> getAllRec(){
        
        List<Reclamation> list = new ArrayList<Reclamation>();
        int count =0;
        
        String requete="select * from reclamation ";
         try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Reclamation r = new Reclamation();
                r.setId(rs.getInt(1));
                r.setNom(rs.getString(2));
                r.setNumero(rs.getInt(3));
                r.setDate(rs.getDate(4));
                r.setImage(rs.getString(5));
                
                list.add(r);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
               return list;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
        
        
                       public void SupprimerReclamation(int a) throws  SQLException
    {
                  String req= "DELETE FROM reclamation WHERE id_rec ="+a;  
                  Statement st = cn.createStatement();
                  st.executeUpdate(req);
      
    }
      
                         
          public Reclamation findReclamationById(int id)
    {
        Reclamation r = new Reclamation();
        int count = 0;
           
        String requete="select * from reclamation where id_rec="+id;
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next())
            {  
                
               r.setId(rs.getInt(1));
               r.setNom(rs.getString(2));
               r.setDate(rs.getDate(4));
               r.setNumero(rs.getInt(3));
               r.setImage(rs.getString(5));

               count++;
            }
           if(count == 0){
                return null;
           }else{
               return r;
           }  
        }
        catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   }
          
         
        public boolean  ModifierReclamation(Reclamation a){
        int verf = 0 ;
        try{
        String req ;
        
        req="UPDATE reclamation set nomuser=?,numero=? where id_rec=?";
        PreparedStatement res=cn.prepareStatement(req);
        
        res.setString(1, a.getNom());
        res.setInt(2, a.getNumero());
         res.setInt(3, a.getId());       
        verf=res.executeUpdate();
         
        }
        catch(SQLException e ){
        Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE,null,e);
        
        }
        if (verf==0)
        {return false;}
        else {return true;}
    }
         
         
        
   
    
}

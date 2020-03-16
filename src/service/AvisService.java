/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entite.Avis;
import java.sql.Connection;
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
public class AvisService {
    
   
        ConnexionBD db = ConnexionBD.getinstance();
        Connection cn = db.getCnx();

    public AvisService() {
    }
    
    
      public boolean  AjouterAvis(Avis avis){
       
         int verf = 0 ;
        try{
        String req ;
        
        req="INSERT INTO `avis`(`choix`, `contenu`) VALUES (?,?)";
        PreparedStatement res=cn.prepareStatement(req);
        
        res.setString(1, avis.getChxAvis());
        res.setString(2, avis.getContenu());
               
        
        verf=res.executeUpdate();
         
        
        }
        catch(SQLException e ){
        Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE,null,e);
        
        }
        if (verf==0)
        {return false;}
        else {return true;}
    }
   
      
       public List<Avis> getAllAvis(){
        
        List<Avis> list = new ArrayList<Avis>();
        int count =0;
        
        String requete="select * from avis ";
         try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Avis U = new Avis();
                U.setId(rs.getInt(1));
                U.setContenu(rs.getString(3));
                U.setChxAvis(rs.getString(2));
                
                list.add(U);
                
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
   
       
                  public void SupprimerAvis(int a) throws  SQLException
    {
                  String req= "DELETE FROM avis WHERE id ="+a;  
                  Statement st = cn.createStatement();
                  st.executeUpdate(req);
      
    }
      
                  
                  
         public boolean  ModifierAvis(Avis a){
         int verf = 0 ;
        try{
        String req ;
        
        req="UPDATE avis set choix=?,contenu=? where id=?";
        PreparedStatement res=cn.prepareStatement(req);
        
        res.setString(1, a.getChxAvis());
        res.setString(2, a.getContenu());
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
         
         
         
          public Avis findAvisById(int id)
    {
        Avis u = new Avis();
        int count = 0;
           
        String requete="select * from avis where id="+id;
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next())
            {  
                
               u.setId(rs.getInt(1));
               u.setChxAvis(rs.getString(2));
               u.setContenu(rs.getString(3));

               count++;
            }
           if(count == 0){
                return null;
           }else{
               return u;
           }  
        }
        catch (SQLException ex) {
            Logger.getLogger(AvisService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Employe;
import java.math.BigDecimal;
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
public class GestionEmploye {

    private final Connection cnx;
    GestionClient gc = new GestionClient();
    public ObservableList<Employe> Elist = FXCollections.observableArrayList();

    public GestionEmploye() {
        this.cnx = ConnexionBD.getinstance().getCnx();

    }

    public void ajouterEmploye(Employe c) {
        Statement st;
        
        try {
            st = cnx.createStatement();
             String req = "insert into user values( '" + c.getLogin()+ "','" + c.getNom() + "','" + c.getPrenom() + "','"+ c.getMdp()+ "','"+ c.getEmail()+ "','"+ c.getTel()+ "',"+c.getSalaire()+",'"+c.getPoste()+"')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println("Services.GestionEmploye.ajouterEmploye() Login Dupliquee");
        }
        updateEmployeList();

    }

    public void afficherEmploye() {
        try {
            PreparedStatement pt = cnx.prepareStatement("select * from user where poste NOT LIKE 'Client'");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("Employe [ Login: " + rs.getString(1) + " Nom: " + rs.getString(2) + " Prenom: " + rs.getString(3) + " Mot de passe: " + rs.getString(4) + " Email: " + rs.getString(5) + " Numero Tel: " + rs.getString(6) + " salaire: " + rs.getDouble(7) + " poste: " + rs.getString(8) + "]");
            }
        } catch (SQLException ex) {
            System.out.println("Services.GestionEmploye.afficherEmploye() "+ex.getMessage());
        }

    }

    public void modifierEmploye(String login, Employe e) {
        PreparedStatement pt;
        gc.modifierClient(login, e);
        try {
            pt = cnx.prepareStatement("update user set salaire = ?, poste = ? where login = ?");
            pt.setBigDecimal(1, e.getSalaire());
            pt.setString(2, e.getPoste());
            pt.setString(3, login);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateEmployeList();

    }

    public void supprimerEmploye(String login) {
        gc.supprimerClient(login);
        updateEmployeList();
    }
    
    public boolean verifyEmploye(String login) {
        return gc.verifyClient(login);

    }
    
    public void updateEmployeList(){
        try {
            PreparedStatement pt = cnx.prepareStatement("SELECT * FROM `user` WHERE Poste not LIKE 'Client' ");
            ResultSet rs = pt.executeQuery();
            Elist.clear();
            while (rs.next()) {
                
                
                Employe f = new Employe(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(8),rs.getBigDecimal(7));
                
                System.out.println(f);
                
                Elist.add(f);
               
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionEmploye.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Employe getEmploye(String login){
        try {
            PreparedStatement pt = cnx.prepareStatement("select * from user where login='"+login+"'");
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                
                return new Employe(login, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(8), new BigDecimal(rs.getDouble(7)));
            }
        } catch (SQLException ex) {
            System.out.println("Services.GestionEmploye.afficherEmploye() "+ex.getMessage());
        }
        return new Employe();
    }
}

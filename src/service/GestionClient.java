/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entite.Client;
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
public class GestionClient {
    
    private final Connection cnx;
    public ObservableList<Client> Clist = FXCollections.observableArrayList();

    public GestionClient() {
        this.cnx = ConnexionBD.getinstance().getCnx();
    }

    public void ajouterClient (Client c) {
        Statement st;
        try {
            st = cnx.createStatement();
            String req = "insert into user values( '" + c.getLogin()+ "','" + c.getNom() + "','" + c.getPrenom() + "','"+ c.getMdp()+ "','"+ c.getEmail()+ "','"+ c.getTel()+ "', 0 ,'Client')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println("Services.GestionClient.ajouterClient(): Login dupliquee");
        }
        updateClientList();

    }

    public void afficherClient() {
        try {
            PreparedStatement pt = cnx.prepareStatement("select * from user where poste = 'client'");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("Client [ Login: " + rs.getString(1) + " Nom: " + rs.getString(2) + " Prenom: " + rs.getString(3) + " Mot de passe: " + rs.getString(4) + " Email: " + rs.getString(5) + " Numero Tel: " + rs.getString(6) +"]");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifierClient(String login,Client c) {
        PreparedStatement pt;
        try {
            pt = cnx.prepareStatement("update user set nom = ?, prenom = ?, mdp = ?, email = ?, tel = ?, login = ? where login = ?");
            pt.setString(1, c.getNom());
            pt.setString(2, c.getPrenom());
            pt.setString(3, c.getMdp());
            pt.setString(4, c.getEmail());
            pt.setString(5, c.getTel());
            pt.setString(6, c.getLogin());
            pt.setString(7, login);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateClientList();

    }

    public void supprimerClient(String login) {
        PreparedStatement pt;
        try {
            pt = cnx.prepareStatement("delete from user where login = ?");
            pt.setString(1, login);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateClientList();
    }
    
    public boolean verifyClient(String login) {
        try {
            PreparedStatement pt = cnx.prepareStatement("select nom from user where login = '"+login+"'");
            ResultSet rs = pt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(GestionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
    
    public void updateClientList(){
        try {
            PreparedStatement pt = cnx.prepareStatement("select * from user where Poste = 'Client'");
            ResultSet rs = pt.executeQuery();
            Clist.clear();
            while (rs.next()) {
                
                
                Client f = new Client(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                
                
                Clist.add(f);
               
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionFacture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

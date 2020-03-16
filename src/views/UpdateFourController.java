/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Fournisseur;
import entite.Marque;
import service.GestionFournisseur;
import service.GestionMarque;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static views.UpdateMarqueController.m;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UpdateFourController implements Initializable {

    @FXML
    private Label ref_produit;
    @FXML
    private Label nom_produit;
    @FXML
    private Label marque;
    @FXML
    private Label categorie;
    @FXML
    private TextField id_fournisseur_field;
    @FXML
    private TextField nom_field;
    @FXML
    private TextField numero_field;
    @FXML
    private TextField Email_field;
    @FXML
    private Button cancel;
    @FXML
    private Button update_four_button;
    public static Fournisseur f;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void cancel(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/PageAcceuil.fxml"));
        Parent root;
        try {
            root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setFields(Fournisseur m){
    id_fournisseur_field.setText(String.valueOf(m.getId_fournisseur()));
    nom_field.setText(m.getNom());
    numero_field.setText(String.valueOf(m.getNumero()));
    Email_field.setText(m.getEmail());
    
    this.f=m;
    
    
    }
     private void new_four(){
         f.setId_fournisseur(Integer.parseInt(id_fournisseur_field.getText()));
         f.setNom(nom_field.getText());
         f.setNumero(Integer.parseInt(numero_field.getText()));
         f.setEmail(Email_field.getText());
    }

    @FXML
    private void update_four_button(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/PageAcceuil.fxml"));
        TableController Tc = loader.getController();
        Parent root;
        new_four();
        GestionFournisseur Gm = new GestionFournisseur();
        Gm.modifierFournisseur(f);
        System.out.println(f);
        try {
            root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
}

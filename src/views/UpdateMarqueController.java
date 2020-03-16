/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Categorie;
import entite.Marque;
import service.GestionCategorie;
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
import static views.UpdateCatgController.c;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UpdateMarqueController implements Initializable {

    @FXML
    private Label nom_produit;
    @FXML
    private TextField id_marque_field;
    @FXML
    private TextField nom_marque_field;
    @FXML
    private Button update_marque_btn;
    @FXML
    private Button cancel;
    public static Marque m;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    }    

    @FXML
    private void update_marque_btn(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/PageAcceuil.fxml"));
        TableController Tc = loader.getController();
        Parent root;
        new_marque();
        GestionMarque Gm = new GestionMarque();
        Gm.modifierCategorie(m);
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
      public void setFields(Marque m){
    id_marque_field.setText(String.valueOf(m.getId_marque()));
    nom_marque_field.setText(m.getNom_marque());
    this.m=m;
    
    
    }

    private void new_marque(){
       m.setId_marque(Integer.parseInt(id_marque_field.getText()));
       m.setNom_marque(nom_marque_field.getText());
    
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
    
}

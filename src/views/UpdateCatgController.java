/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Categorie;
import service.GestionCategorie;
import service.GestionProduit;
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
import static views.UpdateProductController.p;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UpdateCatgController implements Initializable {

    @FXML
    private Label nom_produit;
    @FXML
    private TextField id_catg_field;
    @FXML
    private TextField lib_catg_field;
    @FXML
    private Button update_catg_btn;
    @FXML
    private Button cancel;
    public static Categorie c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void update_catg_btn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/PageAcceuil.fxml"));
        TableController Tc = loader.getController();
        Parent root;
        new_catg();
        GestionCategorie Gc = new GestionCategorie();
        Gc.modifierCategorie(c);
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
       public void setFields(Categorie c){
    id_catg_field.setText(String.valueOf(c.getId_catg()));
    lib_catg_field.setText(c.getLib_catg());
    this.c=c;
    
    
    }
    private void new_catg(){
        c.setId_catg(Integer.parseInt(id_catg_field.getText()));
        c.setLib_catg(lib_catg_field.getText());
    
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

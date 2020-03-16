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
import javafx.scene.AmbientLight;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddCategorieController implements Initializable {

    @FXML
    private Label nom_produit;
    private TextField id_marque_field;
    private TextField nom_marque_field;
    @FXML
    private Button cancel;
    @FXML
    private TextField id_catg_field;
    @FXML
    private TextField lib_catg_field;
    @FXML
    private Button add_catg_button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void add_categorie_btn(ActionEvent event) {
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"added successfully",ButtonType.OK);
        Categorie m=new Categorie(Integer.parseInt(id_catg_field.getText()),lib_catg_field.getText());
        GestionCategorie Gm=new GestionCategorie();
        Gm.ajouterCategorie(m);
        a.showAndWait();
        if(a.getResult().equals(ButtonType.OK)){
            a.close();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("../views/PageAcceuil.fxml"));
                    Scene scene = new Scene(root);
        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        } catch (IOException ex) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
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

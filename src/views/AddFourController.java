/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Categorie;
import entite.Fournisseur;
import service.GestionCategorie;
import service.GestionFournisseur;
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
public class AddFourController implements Initializable {

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
    private Button add_four_button;
    @FXML
    private Button cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void add_four_button(ActionEvent event) {
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"added successfully",ButtonType.OK);
         Fournisseur m=new Fournisseur(Integer.parseInt(id_fournisseur_field.getText()),nom_field.getText(),Integer.parseInt(numero_field.getText()), Email_field.getText());
        GestionFournisseur Gf=new GestionFournisseur();
        Gf.ajouterFournisseur(m);
        a.showAndWait();
        if(a.getResult().equals(ButtonType.OK))
        {
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

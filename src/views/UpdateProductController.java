/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Produit;
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

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UpdateProductController implements Initializable {

    @FXML
    private Label nom_produit;
    @FXML
    private Label marque;
    @FXML
    private Label categorie;
    @FXML
    private Label quantité_magasin;
    @FXML
    private Label quantité_stock;
    @FXML
    private Label prix_vente;
    @FXML
    private Label prix_achat;
    @FXML
    private TextField nom_produit_field;
    @FXML
    private TextField marque_field;
    @FXML
    private TextField categorie_field;
    @FXML
    private TextField quantité_stock_field;
    @FXML
    private TextField quantité_magasin_field;
    @FXML
    private TextField prix_vente_field;
    @FXML
    private TextField prix_achat_field;
    @FXML
    private Button update_product_button;
    public static Produit p;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void update_product_button(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/PageAcceuil.fxml"));
        TableController Tc = loader.getController();
        Parent root;
        newprod();
        GestionProduit Gp = new GestionProduit();
        Gp.modifierProduit(p);
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

    public void setFields(Produit p) {
        try{
        nom_produit_field.setText(p.getNom_produit());
        marque_field.setText(p.getMarque());
        categorie_field.setText(p.getCategorie());
        quantité_stock_field.setText(String.valueOf(p.getQuantité_stock()));
        quantité_magasin_field.setText(String.valueOf(p.getQuantité_magasin()));
        prix_vente_field.setText(String.valueOf(p.getPrix_vente()));
        prix_achat_field.setText(String.valueOf(p.getPrix_achat()));

        this.p = p;
        }catch(NullPointerException e){};
    }

    private void newprod() {
        p.setNom_produit(nom_produit_field.getText());
        p.setCategorie(categorie_field.getText());
        p.setMarque(marque_field.getText());
        p.setQuantité_stock(Integer.parseInt(quantité_stock_field.getText()));
        p.setQuantité_magasin(Integer.parseInt(quantité_magasin_field.getText()));
        p.setPrix_achat(Float.parseFloat(prix_achat_field.getText()));
        p.setPrix_vente(Float.parseFloat(prix_vente_field.getText()));

    }
        public void cancel(ActionEvent event){
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
